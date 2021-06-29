import React from 'react'
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as Yup from "yup";

function Model({ hide }) {
    const validationSchema = Yup.object().shape({
        fullname: Yup.string()
          .required('Fullname is required')
          .min(6, 'Your Full name must be atleast six characters')
        .max(40, 'Full name must not exceed 40 characters'),
        username: Yup.string().required('Username is required')
          .min(6, 'Username must be atleast six characters')
          .max(20, 'Username must not exceed 20 characters'),
        phoneNumber: Yup.number()
          .required('Phone number is required')
          .typeError("That doesn't look like a phone number")
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
    
      const onSubmit = data => {
        console.log(JSON.stringify(data, null, 2));
    };
    
    let modelStyle = {
        display: "block",
        backgroundColor: 'rgba(0,0,0,0.8)',
    }
    return (
        <div className="modal show fade" style={modelStyle} >
            <div className="modal-dialog">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title"> User Registration </h5>
                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" onClick={hide}></button>
                    </div>
                    <div className="modal-body">
                    <div className="register-form">
          <form onSubmit={handleSubmit(onSubmit)}>
            <div className="form-group">
              <label>Full Name</label>
              <input type="text"
                name="fullname"
                {...register("fullname")}
                className={`form-control ${errors.fullname ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.fullname?.message}</div>
            </div>
            <div className="form-group">
              <label>Username</label>
              <input type="text"
                name="username"
                {...register("username")}
                className={`form-control ${errors.username ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.username?.message}</div>
            </div>
            <div className="form-group">
              <div className="form-group">
                <label>Phone number</label>
                <input name="phoneNumber"
                  type="text" name="Phone Number" pattern="[0]{1}[0-9]{9}"
                  title="Phone number with 7-9 and remaing 9 digit with 0-9"
            
                  {...register("phoneNumber")}
                  className={`form-control ${errors.phoneNumber ? "is-invalid" : ""}`}
                />
                <div className="invalid-feedback">{errors.phoneNumber?.message}</div>
              </div>
              <label>Password</label>
              <input name="password"
                type="password"
                pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*_=+-]).{8,12}$"
                title="You password must contain combination of ^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*_=+-]).{8,12}$"
                {...register("password")}
                className={`form-control ${errors.password ? "is-invalid" : ""}`}
              />
              <div className="invalid-feedback">{errors.password?.message}</div>
            </div>
            <div className="form-group">
              <label>Confirm Password</label>
              <input name="confirmPassword"
                type="password"
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
              </label>
              <div className="invalid-feedback">{errors.acceptTerms?.message}</div>
            </div>
            <div className="form-group">
              <button type="submit" className="btn btn-primary">
                Register
              </button>
              <button
                className="btn btn-warning float-right"
                onClick={reset}
                type="button">
                Cancel
              </button>
              <div className="invalid-feedback">{errors.confirmPassword?.message}</div>
            </div>
          </form>
        </div>
                    </div>
                    {/* <div className="modal-footer">
                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" className="btn btn-primary">Save changes</button>
                    </div> */}
                </div>
            </div>
        </div>
    );
}

export default Model
