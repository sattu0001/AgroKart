import React from 'react'
import { useState } from 'react'
import { toast } from 'react-toastify'
import { Link } from 'react-router-dom'
import { URL } from "../../config";
//import {URL} from "config"
import { validEmail, validPassword } from '../../RegEx';

import { useNavigate } from 'react-router'
import 'bootstrap/dist/css/bootstrap.min.css';

import axios from 'axios'
import {useEffect} from 'react';


const Signup = () => {

  
  const [roles, setRoles] = useState([]); 
    //const [flag,setFlag] =useState(true)

    const AllRoles = () => {

        const url = `${URL}/roles`
        axios.get(url).then((response) => {
            
          const result = response.data
             
          console.log(result)
          setRoles(result)
          console.log("Printing roles"+roles);
           {/* if (result['status'] == 'success') {
                           SetRoles(result['data'])
                //setFlag(false)
               
            }
            else {
                toast.warning(result['error'])
            }
          */}

        })
      
     
    

       

    }
    useEffect(() => {
        console.log("calling method all Roles")
        AllRoles();
         }, [])
  

    
   const [userName, setName] = useState('')
  //const [lastName, setLastName] = useState('')
  const [userEmail, setEmail] = useState('')
  const [userPassword, setPassword] = useState('')
  const [confirmPassword, setConfirmPassword] = useState('')
  const [userContactNo, setcontactno] = useState('')
  const [userRole, setRole] = useState('')
  const [userAadharNo,setAadharno]=useState('')
  
  const [profileImg, setprofileImg] = useState('')
  // used to navigate from one component to another

  const navigate = useNavigate()

  const signupUser = () => {
  
      if (!validEmail.test(userEmail)) {
         setEmail(true);
      } 
      else toast.error("Please Enter Email Properly")
       if (!validPassword.test(userPassword)) {
         setPassword(true);
      }
      else toast.error("Please Enter Password Properly")
   
    console.log(`email = ${userEmail}`)
    console.log(`password = ${userPassword}`)
    console.log(`contactno = ${userContactNo}`)
    console.log(`Name = ${userName}`)
    console.log(`Aadharno = ${userAadharNo}`)
    console.log(`role = ${userRole}`)

    if (userName.length == 0) {
      toast.warning('Please enter full name') 
    } else if (userEmail.length == 0 ) {
      toast.warning('Please enter email')
    } else if (userContactNo.length == 0 ) {
      toast.warning('Please enter contact Number')
    } 
    else if (userPassword.length == 0) {
      toast.warning('Please enter password')
    } else if (confirmPassword.length == 0) {
      toast.warning('Please confirm your password')
    } else if (userPassword != confirmPassword) {
      toast.warning('Password does not match')
    } else {
      const body = {
        userName,
        userEmail,
        userPassword,
        userRole,
       profileImg,
        userContactNo,
        userAadharNo
      }

      // url to call the api
      const url = `${URL}/users/sign-up`

      // http method: post
      // body: contains the data to be sent to the API
      axios.post(url, body).then((response) => {
        // get the data from the response
        const result = response.data
        console.log(result)
        if (result['status'] == 'success') {
          toast.success('User added successfully')
          console.log(result['data']);

          sessionStorage['userId'] = result['data'].userId

          const formData = new FormData();

          formData.append('file', profileImg)

          const userId = sessionStorage['userId']
          console.log(userId)
          const url = `${URL}/upload/file/user/${userId}`


          const headers = { 'Accept': 'application/json', 'content-Type': 'multipart/form-data' }

          axios.post(url,formData,headers).then((response) => {
              const result = response.data

              // window.alert("beware")
              // toast.success("Now click on save changes to save Changes")

              setprofileImg(result['data'])
              console.log(result['data'])

          })
          window.location.reload(false)
          navigate('/signin')

      }
      else{
        toast.error("User not found")
      }
      })
    }
   
  }

  return (
    <div class="container-xxl p-0" >



      <div class="row pb-5 pt-5">
        <div class="container">
          <div class="bg-image" id="bg-image-signup">
            <div class="row" id='container'>
              <div class="col-sm-2" >
                {/* <Multiimage></Multiimage> */}
              </div>

              <div class="col-sm-8 mt-3" id="signupForm">

                <div className="form" >
                  <h3 className="label-control text-center m-3 text-danger fw-bold">
                    Create Account
                  </h3>

                  <div class="row mb-3">
                    <div class="col">
                      <label htmlFor="" className="label-control mb-2 fw-bold">
                        Full Name <i className='text-danger'>*</i>
                      </label>

                      <input
                        onChange={(e) => {
                          setName(e.target.value)
                        }}
                        type="text"
                        className="form-control"
                        placeholder='Enter full Name'
                      />
                    </div>
                    
                  </div>

                <div class="form-group">
                    <form >
                        <input type="file" class="form-control-file" id="File1" onChange={(e) => setprofileImg(e.target.files[0])} />
                    </form>
                    </div>

                  




                  <div className="mb-3">
                    <label htmlFor="" className="label-control mb-2 fw-bold">
                      Email Address <i className='text-danger'>*</i>
                    </label>

                    <input
                      onChange={(e) => {
                        setEmail(e.target.value)
                      }}
                      type="email"
                      className="form-control"
                      placeholder='Your Email'

                      
                    />
                  </div>


                  <div className="mb-3">
                    <label htmlFor="" className="label-control mb-2 fw-bold">
                      Contact Number <i className='text-danger'>*</i>
                      
                    </label>


                    <input
                      onChange={(e) => {
                        setcontactno(e.target.value)
                      }}
                      type="text"
                      className="form-control"
                      placeholder='Enter Contact Number'
                    />

                  </div>

                  <div className="mb-3">
                    <label htmlFor="" className="label-control mb-2 fw-bold">
                      Aadhar Number <i className='text-danger'>*</i>
                      
                    </label>


                    <input
                      onChange={(e) => {
                        setAadharno(e.target.value)
                      }}
                      type="text"
                      className="form-control"
                      placeholder='Enter 12 digit Aadhar Number'
                    />

                  </div>

                  <div className="mb-3">
                    <label htmlFor="" className="label-control mb-2 fw-bold">
                      Password <i className='text-danger'>*</i>
                      <small id="passwordHelpInline" class="text-muted pl-2">
                        ( Must be 8-20 characters long)
                      </small>
                    </label>


                    <input
                      onChange={(e) => {
                        setPassword(e.target.value)
                      }}
                      type="password"
                      className="form-control"
                      placeholder='Select a Secure Password'
                    />

                  </div>

                  <div className="mb-3">
                    <label htmlFor="" className="label-control mb-2 fw-bold">
                      Confirm Password <i className='text-danger'>*</i>
                    </label>

                    <input
                      onChange={(e) => {
                        setConfirmPassword(e.target.value)
                      }}
                      type="password"
                      className="form-control"
                      placeholder='Re Enter your Password'
                    />
                  </div>

                  <div class="col-md-4 mb-4" id="exampleInput">
                    <label for="inputState" class="form-label mb-2 fw-bold">
                      Role <i className='text-danger'>*</i>
                    </label>

                    {/*<select id="inputState" onChange={(e) => {
                      setRole(e.target.value)
                    }} class="form-select">
                      <option></option>
                      <option>2</option>
                      <option>3</option>
                  </select>*/}
  
                    <select  id="inputState" onChange={(e) => {
                      setRole(e.target.value)
                    }} class="form-select"><option>select option below</option>
  {roles.map(({ role, roleId }) => (
    <option key={role} value={roleId}>
      {role}
    </option>
  ))}
  </select> 
                  
                  </div>

                  <div className="mb-3 text-center mt-2">

                    <button onClick={signupUser} className="btn btn-md btn-block btn-success fw-bold">
                      Create a new account
                    </button>
                    <div className='mt-1'>
                      Already have an account? <Link to="/signin" className='text-reset alert-link'>Sign In Here</Link>
                    </div>

                  </div>

                </div>
              </div>
            </div>

            <div class="col-sm-2" >

            </div>
          </div>
        </div>

      </div>

    </div>
  )
}

export default Signup