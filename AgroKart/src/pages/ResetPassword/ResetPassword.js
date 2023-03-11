import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import { URL } from "../../config";
import { toast } from "react-toastify";


const ResetPassword = () => {
  const [userEmail, setEmail] = useState("");
  const [otp, setOtp] = useState();
  const [password, setPassword] = useState("");
  //   const [passwordCard, setPasswordCard] = useState();
  const [otpCard, setOtpCard] = useState();
  const navigate = useNavigate();

  const emailSend = () => {};

  const renderOtp = () => {
    // console.log("render called");
    const url = `${URL}/users/user/forgot-password?userEmail=${userEmail}`;

    const body = {
      userEmail,
    };
    axios.get(url, body).then((response) => {
      // get the server result
      const result = response.data;
      // console.log(result);
      if (result["status"] == "success") {
        toast.success("OTP has been sent to your registered email id");
      } else {
        toast.error("Invalid email");
      }
    });

    setOtpCard(
      <>
        <input
          type="number"
          placeholder="otp"
          className="form-control"
          // value={password}
          onChange={(e) => {
            setOtp(e.target.value);
          }}
        />
        <input
          type="text"
          placeholder="new password"
          className="form-control"
          // value={password}
          onChange={(e) => {
            setPassword(e.target.value);
          }}
        />

        {/* <input
          type="submit"
          className="btn btn-primary button form-control"
          value="send"
        /> */}

        <input
          type="submit"
          className="btn btn-primary button form-control"
          value=" reset password"
        />

        {/* <button className="btn btn-success button" onClick={pass}>
          reset password
        </button> */}
        {/* {passwordCard} */}
      </>
    );
  };

  const validate = (event) => {
    event.preventDefault();

    // console.log(email);

    // console.log(otp);

    if (userEmail.length == 0) {
      toast.warning("please enter email");
    } else if (otp.length == 0) {
      toast.warning("please enter password");
    } else {
      const body = {
        userEmail,
        otp,
        password,
      };

      // url to make signin api call
      const url = `${URL}/users/user/reset-password?userEmail=${userEmail}&otp=${otp}&password=${password}`;

      // make api call using axios
      axios.get(url, body).then((response) => {
        // get the server result
        const result = response.data;
        // console.log(result);
        if (result["status"] == "success") {
          toast.success("Your password has been reset sucessfully ");

          navigate("/signin");
        } else {
          toast.error("Invalid OTP");
        }
      });
    }
  };

  return (
    <div className="body">
      <div className="row justify-content-center">
        <div className="col-md-4 " id="lf">
          <h2 style={{ marginBottom: "25px" }}>RESET PASSWORD</h2>
          <form onSubmit={validate}>
            <p>
              <i className="text-danger">*</i> Please enter your email address.
              You will receive an otp to create a new password via email.
            </p>

            <input
              type="email"
              placeholder="Email"
              className="form-control"
              // value={email}
              onChange={(e) => {
                setEmail(e.target.value);
              }}
            />
            

            <button className="btn btn-success button" onClick={renderOtp}>
              submit
            </button>
            {otpCard}

            <hr />
          </form>
        </div>
      </div>
      
    </div>
  );
};

export default ResetPassword;
