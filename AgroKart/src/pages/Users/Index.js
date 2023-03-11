import axios from 'axios';
import React, { useEffect } from 'react'
import Users from '../../component/Users/users';
import { useState } from 'react'
import { toast } from 'react-toastify';
import { URL } from "../../config";

import UserDetails from '../../component/UserDetails/userDetails';

const UsersPage = () => {

    const [user, setUser] = useState([]);
    const userId =sessionStorage['userId']
    console.log(userId)
    const allUsers = () => {

        const url = `${URL}/users`
        axios.get(url).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {

                setUser(result['data'])
                console.log(result)
            }
            else {
                toast.warning(result['error'])
            }
        })
    }

    useEffect(() => {
        console.log("calling method all products")
        allUsers();

    }, [])

   

    return (

        <div class="container-xxl">
            <div className="row"></div>
            <div class="col-container">

            </div>


            <h2 className='text-center mt-3 pt-2 pb-2' id='allproduct'>All Users</h2>
            <hr />

            <div className="container-lg">
                <div className="row">

                    {Object.values(user).map((item) => {
                        return <UserDetails user1={item} />
                        
                    }
                    )}
             
                </div>
            </div>
        </div>);


}
export default UsersPage