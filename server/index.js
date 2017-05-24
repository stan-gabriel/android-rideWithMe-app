/**
 * Created by Gabriel on 25.05.2017.
 */
var express = require('express');
var app = express();
var boddyParser = require('body-parser');
var mongoose =  require('mongoose');

var Ride = require('./models/ride');
var User = require('./models/user');

//connect to mongoose
mongoose.connect('mongodb://localhost/rideWithMe');
var db = mongoose.connection;
    
app.get('/', function (req, res) {
    res.send('Please use /api/... !');
});

app.get('/api/v1/rides', function (req, res) {
    Ride.getAllRides(function (err, rides) {
        if(err) {
            throw err;
        }
        res.json(rides);
    })
});

app.get('/api/v1/rides/:destination', function (req, res) {
    Ride.getRideByDestination(req.params.destination, function (err, ride) {
        if(err) {
            throw err;
        }
        res.json(ride);
    })
});

app.get('/api/v1/users', function (req, res) {
    User.getAllRides(function (err, rides) {
        if(err) {
            throw err;
        }
        res.json(rides);
    })
});

app.listen(3000);
console.log('Server is running on port 3000');