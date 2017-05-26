/**
 * Created by Gabriel on 25.05.2017.
 */
var mongoose = require('mongoose');

// Ride Schema
var rideSchema = mongoose.Schema({
    from: {
        type: String,
        required: true,
        lowercase: true
    },
    to: {
        type: String,
        required: true,
        lowercase: true
    },
    created_date: {
        type: Date,
        default: Date.now()
    },
    date: {
        type: Date,
        // required: true
    }
});

var Ride = module.exports = mongoose.model('Ride', rideSchema);

// Get all Rides
module.exports.getAllRides = function (callback) {
    Ride.find(callback)
};

// Get Ride by id
module.exports.getRideByDestination = function (destination ,callback) {
    Ride.findOne({to: {$regex: new RegExp('^'+ destination + '$', "i")}}, callback)
};

// Create Ride
module.exports.createRide = function (ride, callback) {
    Ride.create(ride, callback)  
};