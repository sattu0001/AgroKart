import { useState } from 'react'
import axios from 'axios';
import React, { useEffect } from 'react'
import { toast } from 'react-toastify';
import { report, URL } from "../../config";
import { useNavigate, useRoutes } from 'react-router'
import "./index.css"


const ProfilePage = () => {


    const { id } = sessionStorage;
   
    const [user, setUser] = useState([]);
    const [userName, setUserName] = useState()
    const [userContactNo, setUserContactNo] = useState()
    const navigate = useNavigate()

    const profile = () => {

        const url = `${URL}/users/getuser?userId=${id}`
        axios.get(url).then((response) => {
            const result = response.data
            console.log(result);

            if (result['status'] == 'success') {

                setUser(result['data'])
                
            }
            else {
                toast.warning(result['error'])
            }


        })
    }
   console.log(user);

   let name= user.userName
   let email=user.userEmail
   let contactno=user.userContactNo
    
{/*
    const uploadImage = () => {
        const url = `${URL}/upload/file/user/${id}`

        const body = {
            
        }
     const   headers = {'Accept': 'application/json', 'content-Type':'multipart/form-data'}

        axios.post(url, formData,headers).then((response) => {
            const result = response.data
           
           // window.alert("beware")
           // toast.success("Now click on save changes to save Changes")
            if (result['status'] == 'success') {
                setprofileImg(result['data'])
                console.log(result['data'])
                
               
            } else {
                toast.error("image unavailable")
            }
        })
        window.location.reload()
    }

*/}

    const updateprofile = () => {
        console.log("im inside update")

        const body = {
            userName:name,
            userContactNo:contactno,
            userEmail:email,
            role:user.role,
            userAadharNo:user.userAadharNo,
            userPassword:user.userPassword,
            userRole:user.userRole,
            userId:user.userId
           }

        // url to call the api
        const url = `${URL}/users/updateprofile?userId=${id}`

        // http method: post
        // body: contains the data to be sent to the API
        axios.put(url, body).then((response) => {
            // get the data from the response
            const result = response.data
            console.log(result)
            if (result['status'] == 'success') {
                toast.success('Successfully updated up new user')

               window.location.reload()
            } else {
                toast.error(result['error'])
            }
        })

    }
    

    const uploadedproductbyuser = () => {
        navigate("/uploadedproductbyuser")
    }


    const companydetails = () => {

        navigate("/addcompany")

    }


    //console.log(allorder)
    const addAddressByid = () => {

        navigate("/addshowaddress")

    }

    const naviagettoaddproduct = () => {
        navigate("/addproduct")
    }

    const showallorders = () => {
        navigate("/allorder")
    }

    const showUsers = () => {

        navigate("/users")

    }

    
    const addcatsubcat=() =>
    {
      navigate("/addcatesubcate")
    }

    const OrderReport= () => {
        console.log("im inside Order Report")

        // url to call the api
        const url = `${URL}/users/generate-orders-report`
        axios.get(url).then((response) => {
            // get the data from the response
            const result = response.data
            console.log(result)
            if (result['status'] == 'success') {
                toast.success('Order Report Downloaded')
                window.location.reload()
            } else {
                toast.error(result['error'])
            }
        })

    }
    

//profile function will be get executed when the component is get loaded
    useEffect(() => {
        console.log("calling method  profile")
        profile();

    }, [])




    return (

        <div>


            <hr />
            <h1 className='text-center mt-4 pt-4' id='allproduct'>{user.userName}'s Profile</h1>
            <hr />

            <div className="row">
                <div className="col">  
               {/*} 
                <img src={user.profileImg} alt="Profile-Pic" width="200px" height="200px" style={{ marginLeft: "210px", marginTop: "30px" }} />
                    <div class="form-group">
                        <form >
                            <input type="file" class="form-control-file" id="File1" onChange={formUpload} />
                            <div>
                            <button  classname="btn btn-warning"style={{marginTop:"10px"}} onClick={uploadImage}>Update Profile Image</button>
                         </div>

                        </form>
            </div>
    */}
        
                    {/*<div><h6>(Please click on Save changes button after uploading image)</h6></div>*/}
                    <div className="row">
                        <div className="col">
                            {user.userRole == "2" &&
                                <div>
                                    <div className="row">
                                        <button type="button" id='xbtn' class="btn btn-dark" onClick={naviagettoaddproduct}>Add Product</button>
                                    </div>
                                   
                                    <div className='row'>
                                        <button type="button" id='xbtn' class="btn btn-dark" onClick={uploadedproductbyuser}>Uploaded Products </button>
                                    </div>
                                </div>

                            }
                            {user.userRole == "3" &&
                                <div class="d-grid gap-2 col-6 mx-auto" id='xbtn'>
                                    <button class="btn btn-warning" type="button" onClick={addAddressByid}>Add/Show Address</button>
                                    <button class="btn btn-warning" type="button" onClick={showallorders}>Show all orders</button>
                                </div>


                            }
                            {user.userRole == "1" &&
                                <div class="d-grid gap-2 col-6 mx-auto" id='xbtn'>
                                <button class="btn btn-warning" type="button" onClick={addcatsubcat}>Add Category</button>
                                    <button class="btn btn-warning" type="button" onClick={showUsers}>Show Users</button>
                                    <button class="btn btn-warning" type="button" onClick={OrderReport}>Generate All Order Report</button>
                                
                                </div>


                            }


                        </div>
                    </div>
                </div>
                <div className="col">

                    <div className="form" >



                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                UserId
                            </label>

                            <input

                                className="form-control"
                                value={user.userId}
                            />
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    User Name
                                </label>

                                <input
                                    onChange={(e) => {
                                        name=e.target.value
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='User Name'
                                    defaultValue={user.userName}
                                />
                            </div>
                            {/*<div class="col">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Last Name
                                </label>

                                <input
                                    onChange={(e) => {
                                        setLastName(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='Last Name'
                                    defaultValue={user.lastName}
                                />
                                </div>*/}
                        </div>

                      {/*  <div className="mb-3">
                             <label htmlFor="" className="label-control mb-2 fw-bold">
                                Profileimage Url
                            </label> */}
{/*
                            < input  hidden={true}
                                onChange={(e) => {
                                    setprofileImg(e.target.value)
                                }}
                                type="url"
                                className="form-control"
                                placeholder='upload your photo on imgBB and paste url here'
                                defaultValue={user.profileImg}
                            />
                        </div>

                            */}


                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                Email Address
                            </label>

                            <input
                            onChange={(e) => {
                                email=e.target.value
                            }}
                                type="email"
                                className="form-control"
                                placeholder='Your Email'
                                value={user.userEmail}

                            />
                        </div>



                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                userContactNo
                            </label>

                            <input
                                onChange={(e) => {
                                    contactno=e.target.value
                                }}
                                type="text"
                                className="form-control"
                                placeholder='Your Mobile'
                                defaultValue={user.userContactNo}

                            />
                        </div>

{/*
                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                Wallet Balance
                            </label>

                            <input
                                onChange={(e) => {
                                    setwalletBalance(e.target.value)
                                }}
                                type="text"
                                className="form-control"
                                placeholder='Topup wallet balance'
                                defaultValue={user.walletBalance}
                            />
                        </div>

                            

                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                Role
                            </label>

                            <input
                                type="text"
                                className="form-control"
                                placeholder='Role'
                                value={user.role}
                            />
                        </div>*/}

                        <div className="mb-3 text-center mt-2">

                            <button onClick={updateprofile} className="btn btn-md btn-block btn-success fw-bold">
                                Save changes
                            </button>

                        </div>

                    </div>


                </div>
            </div>




        </div>




    );



}

export default ProfilePage