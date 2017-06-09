var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

app.use(bodyParser.json());

var Ride = require('./models/ride');
var User = require('./models/user');

//connect to mongoose
// mongoose.connect('mongodb://localhost/rideWithMe');
mongoose.connect('mongodb://admin:123admin@ds151661.mlab.com:51661/ride-with-me');
var db = mongoose.connection;


//------------------- API -----------------------
// API for root path
app.get('/', function (req, res) {
    res.send('Please use --> /api/v1/... !');
});

//-----------------  Users  ---------------------
// API to get all users
app.get('/api/v1/user', function (req, res) {
    User.getAllUsers(function (err, users) {
        if (err) {
            throw err;
        }
        res.json(users);
        console.log(users);
    })
});

// API to get an User by email
app.get('/api/v1/user/:email', function (req, res) {
    User.getUserByEmail(req.params.email, function (err, user) {
        if (err) {
            throw err;
        }
        res.json(user);
        console.log(user);
    })
});

// API for User login
app.post('/api/v1/user/login', function (req, res) {
    console.log(req.body);
    User.login(req.body.email, req.body.password, function (err, user) {
            if (err) {
                throw err;
            }
            res.json(user);
            console.log(user);
        })
});

// API to create an User
app.post('/api/v1/user', function (req, res) {
    console.log(req.body);
    User.createUser({
            firstName: req.body.firstName,
            lastName: req.body.lastName,
            email: req.body.email,
            password: req.body.password
        }
        , function (err, user) {
            if (err) {
                throw err;
            }
            if(user){
                res.json(user);
                console.log(user);
            }
        })
});

//-----------------  Rides  ---------------------
// API to get all Rides
app.get('/api/v1/ride', function (req, res) {
    Ride.getAllRides(function (err, rides) {
        if (err) {
            throw err;
        }
        res.json(rides);
        console.log(rides);
    })
});

// API to get a rides by destination
app.get('/api/v1/ride/:destination', function (req, res) {
    Ride.getRideByDestination(req.params.destination, function (err, ride) {
        if (err) {
            throw err;
        }
        res.json(ride);
        console.log(ride);
    })
});

// API to create a Ride
app.post('/api/v1/ride', function (req, res) {
    Ride.createRide({
        from: req.body.from,
        to: req.body.to,
        price: req.body.price,
        date: req.body.date,
        time: req.body.time
    }, function (err, ride) {
        if (err) {
            throw err;
        }
        res.json(ride);
        console.log(ride);
    })
});




//---------- Server session start ----------
app.listen(3000);
console.log('Server is running on port 3000');