import React from 'react'
import { useState } from 'react'
import { toast } from 'react-toastify'
import { Link } from 'react-router-dom'
import { URL } from "../../config";
import { useNavigate } from 'react-router'
import Header from '../../component/Header/Header'
import Multiimage from '../../component/Multiimage'
import signin from '../../assets/signin.jpg'
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios'
//import Footer from '../../components/Footer/Footer'
const Signin = () => {
  const [userEmail, setEmail] = useState('')
  const [userPassword, setPassword] = useState('')

  const navigate = useNavigate()

  const signinUser = () => {
    console.log(`email = ${userEmail}`)
    console.log(`password = ${userPassword}`)
    if (userEmail.length == 0) {
      toast.warning('please enter email')
    } else if (userPassword.length == 0) {
      toast.warning('please enter password')
    } else {
      const body = {
        userEmail,
        userPassword,
      }

      // url to make signin api call
      const url = `${URL}/users/sign-in`

      // make api call using axios
      axios.post(url, body).then((response) => {
        // get the server result
        const result = response.data
        console.log(result)
        if (result['status'] == 'success') {
          toast.success('Welcome to the application')

          // get the data sent by server
          const { userId,userName} = result['data']

          // persist the logged in user's information for future use
          
          sessionStorage['id'] = userId
          sessionStorage['userName'] = userName
          sessionStorage['roleId'] = result.data.role.roleId
          sessionStorage['role'] = result.data.role.role
          // navigate to home component
          console.log(result.data.role.roleId)
          navigate('/homepage')
        } else {
          toast.error('Invalid user name or password')
        }
      })
    }
  }


  return (
    <div id ="MainDiv" className="container-xxl ">
<div className="row pb-5 pt-5 ">
        <div id ="MainContainer"  className="container">
          <div className="row" id="row">
            <div className="col-sm pb-5 pt-4">

            <div id="s1" ><img  src={signin} alt="logo"  width="550" height="400"/></div>
  </div>

        
            <div className="col pt-4">
            <div class="card">
              <div class="card-body p-5">
              <div className="form">
                <div className="mb-3">
                  <label htmlFor="" className="label-control mb-2 fw-bold text-black ">
                    Email address  <i className='text-danger'>*</i>
                  </label>
                  <input
                    onChange={(e) => {
                      setEmail(e.target.value)
                    }}
                    type="text"
                    placeholder=' Your Email'
                    className="form-control"
                  />
                </div>

                <div className="mb-3">
                  <label htmlFor="" className="label-control mb-2 fw-bold text-black">
                    Password <i className='text-danger'>*</i>
                  </label>
                  <input
                    onChange={(e) => {
                      setPassword(e.target.value)
                    }}
                    type="password"
                    placeholder=' Your Password'
                    className="form-control"

                  />
                </div>

                <div className="mb-3 text-center mt-2" id='BtnSignIn'>
                 
                  <button onClick={signinUser} className="btn btn-success btn-md btn-block pl-6 pr-6 mt-4" >
                    Signin
                  </button>
                  <div className='mt-1 text-black' >
                    No account yet ? <Link to="/signup" className='text-reset alert-link'>Sign Up Here</Link>
                  </div>
                  <div className='mt-1 text-black'>
                  <Link to="/resetpassword" className='text-reset alert-link'>Forgot Password</Link>
                  </div>
                </div>
              </div>
              </div>
              </div>
            </div>

          </div>
          <div className="col"></div>
        </div>
      </div>
    </div>
  )
}

export default Signin