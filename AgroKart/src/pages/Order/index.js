import axios from "axios"
import { toast } from "react-toastify"
import { useEffect, useState } from "react"
import { URL } from "../../config"
import AddToOrder from '../../component/Order/AddToOrder';
import { useNavigate } from 'react-router';
import Address from "../../component/Address/Address";
import "../Order/index.css"
import {collapse} from 'react-collapse';
const Order = () => {

    const userId = sessionStorage['id']
    const [itemOrder, setItemOrder] = useState([])
    const [totalCost, setTotalCost] = useState(0)
    const [address, setAddress] = useState([])
    const [totalProductQty, setTotalProductQty] = useState(0)
    const [selectedAddress, setSelectedAddress] = useState([])
   console.log(userId)
    const navigator = useNavigate();
   
    
  

    const DisplayAddress = () => {
        const addressId = sessionStorage['selectedAddressId']
        console.log(addressId)
        const url = `${URL}/address/getaddressById?addressId=${addressId}`

        axios.get(url).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                setSelectedAddress(result['data'])
                console.log(result['data'])
                console.log(selectedAddress)
            } else {
                toast.error(result['error'])
            }
        })

    }

    const AddAddress = () => {
        navigator("/addaddress");
    }
 

    


    const AddressList = () => {
        const url = `${URL}/address/getaddress?userId=${userId}`

        axios.get(url).then((response) => {
            const result = response.data;
            if (result['status'] == 'success') {
                setAddress(result['data'])
                console.log(result['data'])
            } else {
                toast.error(result['error'])
            }
        })
    }
 

    const TotalProductInCart = () => {
        const url = `${URL}/cart/totalproducts?userId=${userId}`

        axios.get(url).then((response) => {
            const result = response.data;
            if (result['status'] == 'success') {
                setTotalProductQty(result['data'])
            } else {
                toast.error(result['error'])
            }
        })
    }

    const TotalPrice = () => {
        const url = `${URL}/cart/totalprice?userId=${userId}`

        axios.get(url).then((response) => {
            const result = response.data;
            if (result['status'] == 'success') {
                setTotalCost(result['data'])
            } else {
                toast.error(result['error'])
            }
        })
        
    }
   




    

    const OderItemDetails = () => {

        const url = `${URL}/cart/user?userId=${userId}`

        axios.get(url).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                setItemOrder(result['data'])
                console.log(result['data'])
                console.log("printing From order index.js")
                console.log(itemOrder)
               
                setTotalCost(result['data'].totalCost)
            }
            else {
                toast.error(result['error'])
            }
        })

    }

    useEffect(() => {
       OderItemDetails();
        //userWalleteBalance();
        TotalPrice();
       TotalProductInCart();
        DisplayAddress();
        AddressList();
    }, [])

    sessionStorage['totalCost'] = totalCost
   

    const checkout = () => {
    const cartIds =  itemOrder.map ((item) => item.cartId)
           
   
        const body ={
            totalCost,
            paymentModeId:1,
            userId,
            cartIds,
               }
        console.log(body)
       console.log(cartIds)
        const url = `${URL}/orders/placeorder`

        axios.post(url,body).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                setSelectedAddress(result['data'])
                console.log(result['data'])
                console.log(selectedAddress)
            } else {
                toast.error(result['error'])
            }
        })

        navigator("/ordersummary");
    }
    
    
   
    return (
        <div>


            <div>
                <div>
                    <div className="row">
                        <div className="row">
                            <h3 className="label-control text-center m-3 text-success fw-bold">
                                Order Summary
                            </h3>
                        </div>



                        <div className="row">
                            <div className="col-7">
                                <div className="container rounded-top " id="OrderHeaderDiv2">

                                    <div className="row justify-content-center">
                                        <div className="col-lg-10 col-12 pt-3" >

                                            <div className="pt-2" >
                                                <h2 className="text-center">My Orders</h2>
                                            </div>

                                            <div className="row mt-3" id="OrderDiv2">
                                                <div className="col-5 text-left">
                                                    Image
                                                </div>
                                                <div className="col-4 ">
                                                    Name
                                                </div>
                                                <div className="col-1 text-center">
                                                    Price
                                                </div>
                                                <div className="col-2 text-center">
                                                    Quantity
                                                </div>
                                            </div>

                                            <div className="container">
                                                {Object.values(itemOrder).map((item) => {
                                                    console.log("Index.js in Order ")
                                                    console.log(item)
                                                   return <AddToOrder order={item} />
                                                })}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div className="col-5">
                                <div className="d-grid gap-2">
                                    <button class="btn btn-primary"
                                        type="button" data-bs-toggle="collapse"
                                        data-bs-target="#collapseExample2"
                                        aria-expanded="true" aria-controls="collapseExample"
                                    >
                                        Delivey Address 
                                    </button>
                                    <div class="collapse" id="collapseExample2">
                                        <div class="card card-body" >
                                            <ul>
                                                {
                                                    Object.values(address).map((item) => {
                                                        console.log(item)
                                                        return <Address add={item} />
                                                    })}
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <div className="d-grid gap-2" style={{ paddingTop: '10px' }}>
                                    <button class="btn btn-primary"
                                        type="button"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#collapseExample3"
                                        aria-expanded="false"
                                        aria-controls="collapseExample"
                                        onClick={AddAddress}>
                                         Add Address
                                    </button>
                                </div>

                                <div className="d-grid gap-2" style={{ paddingTop: '10px' }}>
                                    <button class="btn btn-primary"
                                        type="button"
                                        data-bs-toggle="collapse"
                                        data-bs-target="#collapseExample4"
                                        aria-expanded="false" aria-controls="collapseExample"
                                        onClick={DisplayAddress}>
                                        Selected Address
                                    </button>
                                    <div className="collapse" id="collapseExample4">
                                        <div className="card card-body">
                                            <div className="row ">
                                                <h6>{selectedAddress.fullAddress}, {' '}
                                                    {selectedAddress.city}, {' '}
                                                    {selectedAddress.district}, {' '}
                                                    {selectedAddress.state},{' '}
                                                    {selectedAddress.country} -
                                                    {selectedAddress.userPincode}</h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div className="d-grid gap-2" style={{ paddingTop: '10px' }}>
                                    <h6 className="label-control text-secondary">
                                        Total Product (
                                        <span >
                                            {totalProductQty}
                                        </span>)
                                    </h6>
                                </div>

                            </div>
                        </div>

                        <div className="row p-0">
                            <div className="container shadow-m bg-body rounded ml-2 pl-3"   >
                                <div className="row align-items-center m-3 p-2" id="OrderPageFooterDiv" >
                                    <div className="col p-1 align-items-right" >
                                        
                                            <b >
                                                Total Amount to be Paid = Rs.
                                                <span >
                                                   
                                                   {'  '} {totalCost}
                                                  
                                                </span>
                                            </b>
                                       
                                    </div>

                                    <div className="col p-1 align-items-right" >
                                        <div>
                                            <button className="btn btn-md text-white float-end "
                                                style={{ background: 'green' }}
                                                onClick={checkout}
                                            >
                                                Confirm
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Order