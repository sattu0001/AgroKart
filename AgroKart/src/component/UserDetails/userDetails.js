import { useState } from 'react'
import React, { useEffect } from 'react'
import "./userDetails.css"
import { useNavigate } from 'react-router'
import axios from 'axios';
import { toast } from 'react-toastify';

const UserDetails = (props) => {
 
    const navigate = useNavigate()
    const { user1 } = props
    console.log(user1.userName)
    console.log(user1.role.role)
    const [pdfResponse, setPdfResponse] = useState();
    const deleteuser = () => {

        const url = `http://localhost:8080/agri-mart/users/deleteuser?userId=${user1.userId}`

        axios.delete(url).then((response) => {
            const result = response.data
            console.log(result)
            if (result['status'] == 'success') {
                toast.success("User Deleted Successfully")
                
            }
            else {
                toast.warning(result['error'])
            }
            navigate("/users")
        })
    }
    const buyerOrderReport= () => {
        console.log("im inside Order Report")

        // url to call the api
        const url = `${URL}/users/buyer/generate-report?userId=${user1.userId}`
        axios.get(url,{responseType: 'blob'}).then((response) => {
            const result = response.data
            console.log(result)
            if (result['status'] == 'success') {
                toast.success('Successfully Downloaded Report')
               setPdfResponse(result['data'])
            } else {
                toast.error(result['error'])
            }
            
        })
        

    }

    return (
        <div className='col-4 col-md-6 mt-2 p-2' >
            <div id="container" className="card p-2 h-100 shadow">

                <div className='row'>
                    <div className='col-6 text-left' id='UserDetailsBalance'> USER ID: {user1.userId}</div>
                    <div className='col-6 right-align' id='UserDetailsRole'>User Role :{user1.role.role}</div>
                  
                </div>

                <div className="card-body text-center" >
                    <img id="UserDetailsImg" src={user1.profileImg} className="card-img-top img-fluid p-3" />
                </div>

                <div >
                    <p className="card-title" id='UserDetailsBlock'>{user1.userName} </p>

                    <div class="item-detail text-center" id="UserDetailsBottomDiv">
                        {user1.gender}<br />
                        Email : {user1.userEmail} Mobile :+91 {user1.userContactNo}
                    </div>
               
                <div className="card-body text-center">
                <button class="btn btn-warning" type="button" onClick={deleteuser} disabled={user1.role.role=="Admin"} >Delete User</button></div>
                </div>
                {user1.role.role=='Buyer'&&
                     <div className="card-body text-center">
                   
                        <button  className="btn btn-primary border border-dark text-white px-lg-5 px-1" type="button" onClick={buyerOrderReport}>Generate Order Report According Buyer</button>
                     
                      </div> 
                      }
                     </div >
                {/*<button type="button"  className="btn btn-warning float-right" ></button> */}
                      
        </div>


    )


}

export default UserDetails