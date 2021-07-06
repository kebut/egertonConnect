import React, { useState, useRef } from "react";
import Form from "react-validation/build/form";
import Input from "react-validation/build/input";
import CheckButton from "react-validation/build/button";
import { isEmail } from "validator";
import { Link } from "react-router-dom";

import AuthService from "../service/AuthService";

const required = (value) => {
  if (!value) {
    return (
      <div className="alert alert-danger" role="alert">
        This field is required!
      </div>
    );
  }
};

const validEmail = (value) => {
  if (!isEmail(value)) {
    return (
      <div className="alert alert-danger" role="alert">
        This is not a valid email.
      </div>
    );
  }
};

const vusername = (value) => {
  if (value.length < 3 || value.length > 20) {
    return (
      <div className="alert alert-danger" role="alert">
        The username must be between 3 and 20 characters.
      </div>
    );
  }
};

const vpassword = (value) => {
  if (value.length < 6 || value.length > 40) {
    return (
      <div className="alert alert-danger" role="alert">
        The password must be between 6 and 40 characters.
      </div>
    );
  }
};

const Register = (props) => {
  const form = useRef();
  const checkBtn = useRef();

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [regNo, setRegNo] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");
  const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    const username = e.target.value;
    setUsername(username);
  };

  const onChangeEmail = (e) => {
    const email = e.target.value;
    setEmail(email);
  };
  const onChangeFirstName = (e) => {
    const firstName = e.target.value;
    setFirstName(firstName);
  };

  const onChangeLastName = (e) => {
    const lastName = e.target.value;
    setLastName(lastName);
  };
  const onChangeRegNo = (e) => {
    const regNo = e.target.value;
    setRegNo(regNo);
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
    e.preventDefault();

    setMessage("");
    setSuccessful(false);

    form.current.validateAll();

    if (checkBtn.current.context._errors.length === 0) {
      AuthService.register(firstName, lastName, username, regNo, email, phoneNumber, password).then(
        (response) => {
          setMessage(response.data.message);
          setSuccessful(true);
          // props.history.push("/home");
          // window.location.reload();
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message
            ||
            error.toString();

          setMessage(resMessage);
          setSuccessful(false);
        }
      );
    }
  };

  return (
    <section className="regist my-4 ">
      <div className="">
        <div className="container">
          <div className="row">
            <div className="card  reg overflow-hidden h-100 shadow   col-sm-6 offset-sm-3">
              <div className="register-form">
                {/* <img
          src="//ssl.gstatic.com/accounts/ui/avatar_2x.png"
          alt="profile-img"
          className="profile-img-card"
        /> */}

                <Form  onSubmit={handleRegister} ref={form}>
                  <h4 class="ca text-center mt-3 ">REGISTRATION FORM</h4>
                  {!successful && (
                  
                    <div>
                    
                      <div className="form-group">
                        <label htmlFor="firstName">FirstName</label>
                        <Input
                          type="text"
                          className="form-control"
                          name="firstName"
                          value={firstName}
                          onChange={onChangeFirstName}
                          validations={[required]}
                        />
                      </div>

                      <div className="form-group">
                        <label htmlFor="lastName">LastName</label>
                        <Input
                          type="text"
                          className="form-control"
                          name="lastName"
                          value={lastName}
                          onChange={onChangeLastName}
                          validations={[required]}
                        />
                      </div>
                      <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <Input
                          type="text"
                          className="form-control"
                          name="username"
                          value={username}
                          onChange={onChangeUsername}
                          validations={[required, vusername]}
                        />
                      </div>

                      <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <Input
                          type="text"
                          className="form-control"
                          name="email"
                          value={email}
                          onChange={onChangeEmail}
                          validations={[required, validEmail]}
                        />
                      </div>
             
                      <div className="form-group">
                        <label htmlFor="phoneNumber">PhoneNumber</label>
                        <Input
                          type="tel"
                          className="form-control"
                          name="phoneNumber"
                          value={phoneNumber}
                          onChange={onChangePhoneNumber}
                          validations={[required]}
                        />
                      </div>

                      <div className="form-group">
                        <label htmlFor="regNo">Reg No</label>
                        <Input
                          type="text"
                          className="form-control"
                          name="regNo"
                          value={regNo}
                          onChange={onChangeRegNo}
                          validations={[required]}
                        />
                      </div>

                      <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <Input
                          type="password"
                          className="form-control"
                          name="password"
                          value={password}
                          onChange={onChangePassword}
                          validations={[required, vpassword]}
                        />
                      </div>

                      <div className="form-group">
                        <button className="btn sign btn-block">Sign Up</button>
                      </div>
                    </div>
                  )}

                  {message &&
                    (
                      <div className="form-group">
                        <div
                          className={
                            successful ? "alert alert-success" : "alert alert-danger"
                          }
                          role="alert"
                        >
                        Thanks for registering. You are now a member of egerton connect.
                        <Link to="/home">back</Link>
                        </div>
                      </div>
                    )}
                  <CheckButton style={{ display: "none" }} ref={checkBtn} />
                </Form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Register;