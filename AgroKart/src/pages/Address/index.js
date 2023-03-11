import React from 'react'
import { useState } from 'react'
import { toast } from 'react-toastify'
import { URL } from "../../config";
import { useNavigate } from 'react-router'
import axios from 'axios'
import reportWebVitals from './../../reportWebVitals';

const AddAddress = () => {

    const userId = sessionStorage['id']
    const [fullAddress, setFullAddress] = useState('');
    //const [addressLine2, setAddressLine2] = useState('');
    const [city, setCity] = useState('');
    const [district, setDistrict] = useState('');
    const [state, setState] = useState('');
    const [country, setCountry] = useState('');
    //const [houseNo, setHouseNo] = useState('');
    const [userPincode, setUserPincode] = useState('');


    const navigate = useNavigate()

    const Back = () => {
        navigate("/order")
    }

    const AddAddress = () => {
        console.log(userId)
        if   (fullAddress.length == 0) {
        toast.warning('Please enter Full Address')
        } 
        else if (city.length == 0) {
        toast.warning('Please enter city')
        } else if (district.length == 0) {
            toast.warning('Please enter state')}
        else if (state.length == 0) {
        toast.warning('Please enter state')
        } else if (userPincode.length == 0) {
        toast.warning('Please enter pincode ')
        } else {

        const body = {
            
            fullAddress,
            city,
            district,
            state,
            country,
            userPincode,
            userId
        }

        const url = `${URL}/address/saveaddress`

        axios.post(url, body).then((response) => {
            const result = response.data;
            if(result['status'] == 'success'){
                navigate("/order")
            } else {
                toast.error(result['error'])
            }
        })
        }
    }

    return (
    <div>
        <div className="row">
            <div className="col-sm-2"></div>
            <div className="col">
            <div className="container-xxl p-0">
            <div>
                <h3 className="label-control text-center m-3 text-info fw-bold">
                    Add Address 
                </h3>
            </div>
{/*
            <div className="mb-3" style={{width : '250px'}}>
                <label className="label-control mb-2 fw-bold">
                    House No. <i className='text-danger'>*</i>
                </label>
                <input
                    onChange={(e) => {
                        setHouseNo(e.target.value)
                    }}
                    type="text"
                    className="form-control"
                    placeholder='HouseNo'
                />
                </div>*/}

            <div className="mb-3">
                <label className="label-control mb-2 fw-bold">
                    Full Adress <i className='text-danger'>*</i>
                </label>
                <input
                    onChange={(e) => {
                        setFullAddress(e.target.value)
                    }}
                    type="text"
                    className="form-control"
                    placeholder='Full Address'
                />
            </div>

            
            
            <div className="row">

                <div className="col">
                    <div className="mb-3">
                        <label className="label-control mb-2 fw-bold">
                            City <i className='text-danger'>*</i>
                        </label>
                        <input
                            onChange={(e) => {
                                setCity(e.target.value)
                            }}
                            type="text"
                            className="form-control"
                            placeholder='City'
                        />
                    </div>
                </div>

                             
                <div className="mb-3">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    District <i className='text-danger'>*</i>
                                </label>


                                <input
                                    onChange={(e) => {
                                        setDistrict(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='District'
                                />
                                </div>        


                <div className="col">
                    <div className="col">
                        <div className="mb-3">
                            <label className="label-control mb-2 fw-bold">
                                State <i className='text-danger'>*</i>
                            </label>
                            <input
                                onChange={(e) => {
                                    setState(e.target.value)
                                }}
                                type="text"
                                className="form-control"
                                placeholder='State'
                            />
                        </div>
                    </div>
                </div>

                <div className="mb-3">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Country <i className='text-danger'>*</i>
                                </label>


                                <input
                                    onChange={(e) => {
                                        setCountry(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='Country'
                                />
                                </div>  





                <div className="col">
                    <div className="col">
                        <div className="mb-3">
                            <label className="label-control mb-2 fw-bold">
                                Pincode <i className='text-danger'>*</i>
                            </label>
                            <input
                                onChange={(e) => {
                                    setUserPincode(e.target.value)
                                }}
                                type="text"
                                className="form-control"
                                placeholder='Pincode'
                            />
                        </div>
                    </div>
                </div>
                
                <div className="row">
                    <div className="col">
                        <div className="mb-3 text-center mt-3 pt-3">
                            <button className="btn  btn-block btn-primary float-start"
                                    onClick={Back}>
                                Back
                            </button>
                        </div>
                    </div>
                    <div className="col">
                        <div className="mb-3 text-center mt-3 pt-3">
                            <button className="btn  btn-block btn-primary float-end"
                                    onClick={AddAddress}>
                                Add Address
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
            </div>
            <div className="col-sm-2"></div>
        </div>
    </div>
    );

}

export default AddAddress;
