import React from 'react'
import axios from "axios"
const API_URL =  "http://localhost:8000/api/auth/";

const register = (firstName, lastName, username, regNo, email, phoneNumber, password) => {
    return axios.post(API_URL + "signup", {
        firstName,
        lastName,
        username,
        regNo,
        email,
        phoneNumber,
        password
    });
}

export default {
    register
}
