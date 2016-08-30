import React from 'react';

var FlightsSearchForm = React.createClass({
    // get initial state event
    getInitialState: function() {
        return {
            from:'',
            to:'',
            dep:'',
            returnDate:'',
            serverMessage:'',
            Fields:[]
        };
    },

    render: function() {
        return (
            <div id="flightsearch" className="row col-sm-12 ">
                <div className="row col-sm-12">
                    <h1>Book Flight Tickets</h1>
                </div>
                <div className="row col-sm-6">
                    <label className="col-sm-6">
                        <input type="radio" name="tripType" />{'  '}ONE WAY
                    </label>
                    <label className="col-sm-6">
                        <input type="radio" name="tripType"/> {'  '}ROUND TRIP
                    </label>
                </div>
                <div className="row col-sm-12">
                    <div className="col-xs-6">
                        <label htmlFor="from">From</label>
                        <input className="form-control" id="from" type="text"/>
                    </div>
                    <div className="col-xs-6">
                        <label htmlFor="to">To</label>
                        <input className="form-control" id="to" type="text"/>
                    </div>
                </div>
                <div className="row col-sm-12">
                    <div className="col-xs-6">
                        <label htmlFor="dep">Departure</label>
                        <input type="date" className="form-control" id="dep" />
                    </div>
                    <div className="col-xs-6">
                        <label htmlFor="to">Return</label>
                        <input className="form-control" id="to" type="date"/>
                    </div>
                </div>
                <div className="row col-xs-12">
                    <div className="col-xs-3">
                        <label htmlFor="adult">
                            ADULT
                        </label>
                        <div id="adult" className="segmented_controls form-control col-xs-4">
                            <a className="col-sm-4" onclick="decrementer('adultCount', 1)" href="javascript:void(0);">
                                -
                            </a>
                            <span className="adultCount col-sm-4" >
                                1
                            </span>
                            <a className="col-sm-4" onclick="incrementer('adultCount', 14)" href="javascript:void(0);">
                                +
                            </a>
                        </div>
                    </div>
                    <div className="col-xs-3">
                        <label htmlFor="child">
                            CHILD
                        </label>
                        <div id="child" className="segmented_controls form-control col-xs-4">
                            <a className="col-sm-4" onclick="decrementer('adultCount', 1)" href="javascript:void(0);">
                                -
                            </a>
                            <span className="adultCount col-sm-4" >
                                1
                            </span>
                            <a className="col-sm-4" onclick="incrementer('adultCount', 14)" href="javascript:void(0);">
                                +
                            </a>
                        </div>
                    </div>
                    <div className="col-xs-3">
                        <label htmlFor="infant">
                            INFANT
                        </label>
                        <div id="infant" className="segmented_controls form-control col-xs-4">
                            <a className="col-sm-4" onclick="decrementer('adultCount', 1)" href="javascript:void(0);">
                                -
                            </a>
                            <span className="adultCount col-sm-4" >
                                0
                            </span>
                            <a className="col-sm-4" onclick="incrementer('adultCount', 14)" href="javascript:void(0);">
                                +
                            </a>
                        </div>
                    </div>
                </div>
                <div className="row col-xs-12">
                    <div className="col-sm-4">
                        <button type="submit" className="btn btn-primary btn-lg btn-block" id="submit">Search</button>
                    </div>
                </div>
            </div>
        );
    }
});
export default FlightsSearchForm;