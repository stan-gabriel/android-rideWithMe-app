/**
 * Created by Gabriel on 25.05.2017.
 */
var mongoose = require('mongoose');

// Rides Schema
var userSchema = mongoose.Schema({
    name: {
        type: String
    },
    email: {
        type: String,
        required: true,
        lowercase: true
    },
    password: {
        type: String,
        required: true
    },
    registration_date: {
        type: Date,
        default: Date.now()
    },
    gender: {
        type: String
    },
    age: {
        type: Number
    }
});

var User = module.exports = mongoose.model('User', userSchema);

// Get Rides
module.exports.getAllUsers = function (callback, limit) {
    User.find(callback).limit(limit)
};

// Create User
module.exports.createUser = function (user, callback) {
    User.create(user, callback)
};