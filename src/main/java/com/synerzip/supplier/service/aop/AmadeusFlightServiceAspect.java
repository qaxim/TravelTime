package com.synerzip.supplier.service.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.synerzip.supplier.amadeus.model.flights.AffiliateFlightSearchRS;
import com.synerzip.supplier.amadeus.model.flights.AffiliateSearchResult;
import com.synerzip.supplier.amadeus.model.flights.Flight;
import com.synerzip.supplier.amadeus.model.flights.Itinerary;
import com.synerzip.supplier.amadeus.model.flights.LowFareFlightSearchRS;
import com.synerzip.supplier.amadeus.model.visitors.BoundElement;
import com.synerzip.supplier.amadeus.model.visitors.BoundVisitor;
import com.synerzip.supplier.amadeus.model.visitors.FlightVisitor;
import com.synerzip.supplier.service.TimeService;
import com.synerzip.utils.TimeUtils.TimeUtilities;

@Component
@Aspect
public class AmadeusFlightServiceAspect {
	private static final Logger logger = LoggerFactory.getLogger(AmadeusFlightServiceAspect.class);

	@Autowired
	private TimeService timeService;

	@Autowired
	private ThreadPoolTaskExecutor executor;

	@AfterReturning(pointcut = "execution(* com.synerzip.supplier.service.AmadeusFlightService.fetchLowFareFlights(*))", returning = "lowFareFlightSearchRS")
	public void updateDuration(LowFareFlightSearchRS lowFareFlightSearchRS) {
		logger.debug("After-returning advice for return type LowFareFlightSearchRS");
		lowFareFlightSearchRS.getResults().stream().forEach(result -> {
			result.getItineraries().stream().forEach(itinerary -> update(itinerary));
		});
	}

	@AfterReturning(pointcut = "execution(* com.synerzip.supplier.service.AmadeusFlightService.fetchLowFareFlights(*))", returning = "affiliateFlightSearchRS")
	public void updateDuration(AffiliateFlightSearchRS affiliateFlightSearchRS) {
		logger.debug("After-returning advice for return type AffiliateFlightSearchRS");
		affiliateFlightSearchRS.getResults().stream()
				.forEach(affiliateFlightSearchResult -> update(affiliateFlightSearchResult));
	}

	private void update(AffiliateSearchResult affiliateSearchResult) {
		update(affiliateSearchResult.getOutbound());
		// in-bound needs to be checked for null-values since one-way flights
		// don't have in-bound itinerary
		if (affiliateSearchResult.getInbound() != null) {
			update(affiliateSearchResult.getInbound());
		}
	}
	private void update(Itinerary itinerary) {
		update(itinerary.getOutbound());
		// in-bound needs to be checked for null-values since one-way flights
		// don't have in-bound itinerary
		if (itinerary.getInbound() != null) {
			update(itinerary.getInbound());
		}
	}
	
	private void update(BoundElement bound) {
		BoundVisitor visitor = new BoundVisitor() {
			Period totalFlightPeriod = Period.ZERO;

			@Override
			public void visit(BoundElement bound) {
				Map<String, String> layovers = new HashMap<>();

				FlightVisitor flightVisitor = new FlightVisitor() {
					Flight prevFlight;
					Period prevFlightPeriod;

					@Override
					public void visit(Flight flight) {
						Duration blkTime = timeService.getBlkTime(flight.getOrigin().getAirport(),
								flight.getDepartsAt(), flight.getDestination().getAirport(), flight.getArrivesAt());

						flight.getAdditionalProperties().put("duration",
								TimeUtilities.periodToString(blkTime.toPeriod()));

						if (prevFlightPeriod != null && prevFlight != null) {
							if (prevFlight.getDestination().getAirport().equals(flight.getOrigin().getAirport())) {
								String airportCode = flight.getOrigin().getAirport();
								Period layover = timeService
										.getLayOverTime(airportCode, prevFlight.getArrivesAt(), flight.getDepartsAt())
										.toPeriod();
								layovers.put(airportCode, TimeUtilities.periodToString(layover));
								totalFlightPeriod = totalFlightPeriod.plus(layover);
							}
						}

						totalFlightPeriod = totalFlightPeriod.plus(blkTime.toPeriod());
						prevFlightPeriod = blkTime.toPeriod();
						prevFlight = flight;
					}
				};

				bound.getFlights().stream().forEach(flight -> {
					flight.accept(flightVisitor);
				});

				bound.setDuration(TimeUtilities.periodToString(totalFlightPeriod));

				if (!layovers.isEmpty()) {
					bound.setAdditionalProperty("layovers", layovers);
				}
			}
		};
		bound.accept(visitor);
	}
}