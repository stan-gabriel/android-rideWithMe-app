/**
 * Created by Gabriel on 25.05.2017.
 */
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

app.use(bodyParser.json());

var Ride = require('./models/ride');
var User = require('./models/user');

//connect to mongoose
mongoose.connect('mongodb://localhost/rideWithMe');
var db = mongoose.connection;

app.get('/', function (req, res) {
    res.send('Please use --> /api/v1/... !');
});


//====================  Rides  ====================
app.get('/api/v1/ride', function (req, res) {
    Ride.getAllRides(function (err, rides) {
        if (err) {
            throw err;
        }
        res.json(rides);
    })
});

app.get('/api/v1/ride/:destination', function (req, res) {
    Ride.getRideByDestination(req.params.destination, function (err, ride) {
        if (err) {
            throw err;
        }
        res.json(ride);
    })
});

app.post('/api/v1/ride', function (req, res) {
    Ride.createRide({
        from: req.body.from,
        to: req.body.to,
        date: req.body.date
    }, function (err, ride) {
        if (err) {
            throw err;
        }
        res.json(ride);
    })
});



//====================  Users  ====================
app.get('/api/v1/user', function (req, res) {
    User.getAllUsers(function (err, users) {
        if (err) {
            throw err;
        }
        res.json(users);
    })
});

app.get('/api/v1/user/:email', function (req, res) {
    User.getUserByEmail(req.params.email, function (err, user) {
        if (err) {
            throw err;
        }
        res.json(user);
    })
});

app.post('/api/v1/user', function (req, res) {
    User.createUser({
            name: req.body.name,
            email: req.body.email,
            password: req.body.password
        }
        , function (err, user) {
            if (err) {
                throw err;
            }
            res.json(user);
        })
});


app.listen(3000);
console.log('Server is running on port 3000');