import React, { useState, useEffect, useRef } from 'react';
import { useForm } from "react-hook-form";
import { Link } from 'react-router-dom';
import { yupResolver } from "@hookform/resolvers/yup";
import CheckButton from "react-validation/build/button";
import * as Yup from "yup";
import AuthService from '../service/AuthService';

function Register(props) {
  const validationSchema = Yup.object().shape({
    firstName: Yup.string()
      .required('FirstName is required')
      .min(3, 'Your first name must be atleast six characters')
    .max(40, 'First name must not exceed 40 characters'),
    lastName: Yup.string()
      .required('LastName is required')
      .min(3, 'Your last name must be atleast three characters')
    .max(40, 'Last name must not exceed 40 characters'),
    regNo: Yup.string()
      .required('Registration No. is required')
      .min(8, 'Your Registration No. must be atleast eight characters')
    .max(12, 'Registration No. must not exceed 12 characters'),
    username: Yup.string().required('Username is required')
      .min(3, 'Username must be atleast three characters')
      .max(20, 'Username must not exceed 20 characters'),
    phoneNumber: Yup.number()
      .required('Phone number is required')
      .typeError("Invalid phone number")
      .positive("A phone number can't start with a minus")
      .integer('A phone number cannot include a decimal point'),
      
    email: Yup.string()
      .required('Email is required')
      .email('Email is invalid'),
    password: Yup.string('')
      .required('Password is required')
      .min(6, 'Password must be atleast 6 characters')
      .max(20, 'Password must not exceed 20 characters'),
    confirmPassword: Yup.string()
      .required('Please confirm your paasword')
      .oneOf([Yup.ref('password'), null], 'Password does not match'),
    acceptTerms: Yup.bool().oneOf([true], 'Accept Terms is required')
  });

  //pass the rules to react useForm function using yupResolver function from react-hook-form/resolver library
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors }
  } = useForm({
    resolver: yupResolver(validationSchema)
  });

  // const onSubmit = data => {
  //   console.log(JSON.stringify(data, null, 2));
  // };
  const form = useRef();
  // const checkBtn = useRef();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [username, setUsername] = useState("");
  const [regNo, setRegNo] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeFirstName = (e) => {
    const firstName = e.target.value;
    setFirstName(firstName);
  };
  const onChangeLastName = (e) => {
    const lastName = e.target.value;
    setLastName(lastName);
  };
  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };
  const onChangeRegNo = (e) => {
    const regNo = e.target.value;
    setRegNo(regNo);
  };
  const onChangeEmail = (e) => {
    const email = e.target.value;
    setEmail(email);
  };
  const onChangePhoneNumber = (e) => {
    const phoneNumber = e.target.value;
    setPhoneNumber(phoneNumber);
  };
  const onChangePassword = (e) => {
    const password = e.target.value;
    setPassword(password);
  };

  const handleRegister = (e) => {
    // e.preventDefault();

    setMessage("");
    setSuccessful(false);

    // form.current.validateAll();

    // if (checkBtn.current.context._errors.length === 0) {
      AuthService.register(firstName, lastName, username,regNo, email, phoneNumber, password
      ).then(
        (response) => {
          setMessage(response.data.message);
          setSuccessful(true);
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          setMessage(resMessage);
          setSuccessful(false);
        }
      )
    // }

  }
  return (
    <section className="my-5 home">
      <div className="container">
      <h3 class="red text-center ">Registration Form</h3>
    <div className="row">
      <div className="card 3 reg overflow-hidden h-100 shadow   col-sm-6 offset-sm-3">
        <div className="register-form">
          <form onSubmit={handleSubmit(handleRegister)} ref={form}>
            <div className="form-group mb-3">
              <label>First Name</label>
                  <input
                    type="text"
                    name="firstName"
                    onChange={onChangeFirstName}
                {...register("firstName")}
                className={`form-control ${errors.firstname ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.firstName?.message}</div>
                </div>
                
            <div className="form-group">
              <label>Last Name</label>
                  <input
                    type="text"
                    name="lastName"
                    onChange={onChangeLastName}
                {...register("lastName")}
                className={`form-control ${errors.lastNname ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.lastName?.message}</div>
            </div>
            <div className="form-group">
              <label>Username</label>
                  <input
                    type="text"
                    name="username"
                  onChange={onChangeUsername}
                {...register("username")}
                className={`form-control ${errors.username ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.username?.message}</div>
                </div>
                
            <div className="form-group">
              <label>Registration No.</label>
                  <input
                    type="text"
                    name="regNo"
                    onChange={onChangeRegNo}
                {...register("regNo")}
                className={`form-control ${errors.regNo ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.regNo?.message}</div>
            </div>
            <div className="form-group">
              <label>Email</label>
                  <input
                    type="email"
                    onChange={onChangeEmail}
                    name="email"
                {...register("email")}
                className={`form-control ${errors.email ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.email?.message}</div>
            </div>
            <div className="form-group">
              <div className="form-group">
                <label>Phone number</label>
                    <input
                      name="phoneNumber"
                      type="text"
                       pattern="[0]{1}[0-9]{9}"
                      title="Phone number with 7-9 and remaing 9 digit with 0-9"
                      onChange={onChangePhoneNumber}
                  {...register("phoneNumber")}
                  className={`form-control ${errors.phoneNumber ? "is-invalid" : ""}`}
                />
                <div className="invalid-feedback">{errors.phoneNumber?.message}</div>
              </div>
              <label>Password</label>
                  <input
                    name="password"
                type="password"
                // pattern="(?=.*[A-Z])(?=.*[0-9])"
                title="You password must contain combination of ^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*_=+-]).{8,12}$"
                {...register("password")}
                className={`form-control ${errors.password ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.password?.message}</div>
            </div>
            <div className="form-group">
              <label>Confirm Password</label>
                  <input
                    name="confirmPassword"
                    type="password"
                    onChange={onChangePassword}
                {...register("confirmPassword")}
                className={`form-control ${errors.confirmPassword ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.confirmPassword?.message}</div>
            </div>
            <div className="form-group form-check">
              <input name="acceptTerms"
                type="checkbox"
                {...register("acceptTerms")}
                className={`form-check-input ${errors.acceptTerms ? "is-invalid" : ""}`}
              />
              <label htmlFor="acceptTerms" className="form-check-label">
                    I have read Terms and Conditions.
                    <a href="#">Click here to read terms and conditions</a>
              </label>
              <div className="invalid-feedback">{errors.acceptTerms?.message}</div>
            </div>
            <div className="form-group mb-3">
              <button type="submit" className="btn login cu">
                Register
              </button>
                  <Link
                    to="/home"
                className="btn btn-warning float-right"
                
                type="button">
                Cancel
              </Link>
                </div>
                {message && (
                  <div className="form-group">
                    <div
                      className={
                        successful ? "alert alert-success" : "alert alert-danger"
                    }
                      role="alert"
                    >
                      {message}
                    </div>
                  </div>
                )}
                {/* <CheckButton style={{display: "none"}} ref={checkBtn}/> */}
          </form>
        </div>
      </div>
        </div>
        </div>
    </section>
  );
}

export default Register;
