
package com.synerzip.supplier.sabre.model.flights.instaflight_gen;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.synerzip.supplier.sabre.model.flights.visitors.ItinTotalFareElement;
import com.synerzip.supplier.sabre.model.flights.visitors.ItinTotalFareVisitor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "FareConstruction",
    "TotalFare",
    "Taxes",
    "BaseFare",
    "EquivFare"
})
public class ItinTotalFare implements ItinTotalFareElement {

    @JsonProperty("FareConstruction")
    private FareConstruction_ fareConstruction;
    @JsonProperty("TotalFare")
    private TotalFare_ TotalFare;
    @JsonProperty("Taxes")
    private Taxes_ Taxes;
    @JsonProperty("BaseFare")
    private BaseFare_ BaseFare;
    @JsonProperty("EquivFare")
    private EquivFare_ EquivFare;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The FareConstruction
     */
    @JsonProperty("FareConstruction")
    public FareConstruction_ getFareConstruction() {
        return fareConstruction;
    }

    /**
     * 
     * @param fareConstruction
     *     The FareConstruction
     */
    @JsonProperty("FareConstruction")
    public void setFareConstruction(FareConstruction_ fareConstruction) {
        this.fareConstruction = fareConstruction;
    }

    /**
     * 
     * @return
     *     The TotalFare
     */
    @JsonProperty("TotalFare")
    public TotalFare_ getTotalFare() {
        return TotalFare;
    }

    /**
     * 
     * @param TotalFare
     *     The TotalFare
     */
    @JsonProperty("TotalFare")
    public void setTotalFare(TotalFare_ TotalFare) {
        this.TotalFare = TotalFare;
    }

    /**
     * 
     * @return
     *     The Taxes
     */
    @JsonProperty("Taxes")
    public Taxes_ getTaxes() {
        return Taxes;
    }

    /**
     * 
     * @param Taxes
     *     The Taxes
     */
    @JsonProperty("Taxes")
    public void setTaxes(Taxes_ Taxes) {
        this.Taxes = Taxes;
    }

    /**
     * 
     * @return
     *     The BaseFare
     */
    @JsonProperty("BaseFare")
    public BaseFare_ getBaseFare() {
        return BaseFare;
    }

    /**
     * 
     * @param BaseFare
     *     The BaseFare
     */
    @JsonProperty("BaseFare")
    public void setBaseFare(BaseFare_ BaseFare) {
        this.BaseFare = BaseFare;
    }

    /**
     * 
     * @return
     *     The EquivFare
     */
    @JsonProperty("EquivFare")
    public EquivFare_ getEquivFare() {
        return EquivFare;
    }

    /**
     * 
     * @param EquivFare
     *     The EquivFare
     */
    @JsonProperty("EquivFare")
    public void setEquivFare(EquivFare_ EquivFare) {
        this.EquivFare = EquivFare;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public void accept(ItinTotalFareVisitor visitor) {
		visitor.visit(this);
		fareConstruction.accept(visitor);
	}

}
