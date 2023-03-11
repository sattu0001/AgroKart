import axios from "axios"
import { useEffect, useState } from "react"
import { toast } from "react-toastify"
import { URL } from "../../config"
import ShowAddress from "../../component/ShowAddress/ShowAddress"


const AddShowAddress = () => {
    const userId = sessionStorage['id']
    //const [houseNo, setHouseNo] = useState([])
    const [fullAddress, setFullAddress] = useState([])
    //const [addressLine2, setAddressLine2] = useState([])
    const [city, setCity] = useState([])
    const [district, setDistrict] = useState([])
    const [state, setState] = useState([])
    const [country, setCountry] = useState([])
    const [userPincode, setUserPinCode] = useState([])
    const [user, setUser] = useState({
        userId: userId
    })


    const [address, setAddress] = useState([])


    const addAddress = () => {


        if (fullAddress.length == 0) {
            toast.warning('Please enter Full Address')
        } 
        else if (city.length == 0) {
            toast.warning('Please enter City')}

        else if (district.length == 0) {
            toast.warning('Please enter District')
        } else if (state.length == 0) {
            toast.warning('Please enter  State')
        } else if (country.length == 0) {
            toast.warning('Please enter  State')
        }
         else if (userPincode.length == 0) {
            toast.warning('Please enter Pincode ')
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
                const result = response.data
                if (result['status'] == "success") {
                    console.log(result['data'])
                    toast.success("Address added successfully")
                    window.location.reload(false)
                } else {

                    toast.error("No Address found")
                }
            })
        }
    }


    const showAllAddress = () => {

        const userId = sessionStorage['id']

        console.log(userId)

        const url = `${URL}/address/getaddress?userId=${userId}`

        axios.get(url).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                setAddress(result['data'])
                console.log(result['data'])
                
            }
            else {
                toast.warning("No Address to show")
                console.log(result['data'])
            }
        })




        console.log(address)


    }

    useEffect(() => {
        showAllAddress()
       
    }, [])
    return (
        <div>
            <div className="container-xxl p-0">

                <div className="container">
                    <h3 className="label-control text-center m-3 text-danger fw-bold">Add Address</h3>
                    <div className="row" id='container'>
                        <div className="col-sm-2" >
                        </div>

                        <div className="col-sm-8 mt-3" id="addProduct">
                            <div className="form">
                            </div>


                            <div className="mb-3">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Full Address<i className='text-danger'>*</i>
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


 {/*

                            <div className="mb-3">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    AddressLine 2 <i className='text-danger'>*</i>
                                </label>
                                <input
                                    onChange={(e) => {
                                        setAddressLine2(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='AddressLine 2 '
                                />


                            </div>*/}



                            <div className="row" mb-3 >
                                <div className="col">

                                    <label htmlFor="" className="label-control mb-2 fw-bold">
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



                                <div className="row" mb-3 >
                                    <div className="col">

                                        <label for="inputState" className="label-control mb-2 fw-bold">State<i className='text-danger'>*</i></label>
                                        <select id="inputState" onChange={(e) => {
                                            setState(e.target.value)
                                        }} className="form-select">
                                            <option selected>Choose...</option>
                                            <option>Andhra Pradesh</option><option>Arunachal Pradesh</option><option>Assam</option><option>Bihar</option>
                                            <option>Chandigarh (UT)</option><option>Chhattisgarh</option><option>Delhi</option><option>Goa</option>
                                            <option>Gujarat</option><option>Haryana</option><option>Himachal Pradesh</option><option>Jammu and Kashmir</option>
                                            <option>Jharkhand</option><option>Karnataka</option><option>Kerala</option><option>Madhya Pradesh</option>
                                            <option>Maharashtra</option><option>Orissa</option><option>Punjab</option><option>Rajasthan</option>
                                            <option>Tamil Nadu</option><option>Telangana</option><option>Uttar Pradesh</option><option>Uttarakhand</option>
                                            <option>West Bengal</option>
                                        </select>


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
                                    <label htmlFor="" className="label-control mb-2 fw-bold">
                                        Pincode <i className='text-danger'>*</i>
                                    </label>
                                    <input
                                        onChange={(e) => {
                                            setUserPinCode(e.target.value)
                                        }}
                                        type="text"
                                        className="form-control"
                                        placeholder=' Pincode '
                                    />


                                </div>


                            </div>

                            <div className="mb-3 text-center mt-3 pt-3">
                                <button onClick={addAddress} className="btn btn-lg btn-block btn-success fw-bold">
                                    Add Address
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <hr></hr>
            <div>
                {address.map((item) => {
                    return <ShowAddress show={item} />
                })}
            </div>
        </div>
    )
}

export default AddShowAddress