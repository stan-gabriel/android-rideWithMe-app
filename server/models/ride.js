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
    price: {
        type: String
    },
    created_date: {
        type: Date,
        default: Date.now()
    },
    date: {
        type: String
    },
    time: {
        type: String
    }
});

var Ride = module.exports = mongoose.model('Ride', rideSchema);

// Get all Rides
module.exports.getAllRides = function (callback) {
    Ride.find(callback)
};

// Get Ride by destination
module.exports.getRideByDestination = function (destination ,callback) {
    Ride.findOne({to: {$regex: new RegExp('^'+ destination + '$', "i")}}, callback)
};

// Get Ride by Location & Destination
module.exports.getRidesByLocationAndDestination = function (from, to,callback) {
    Ride.find({from: {$regex: new RegExp('^'+ from + '$', "i")}, to: {$regex: new RegExp('^'+ to + '$', "i")}}, callback)
};

// Create Ride
module.exports.createRide = function (ride, callback) {
    Ride.create(ride, callback)  
};