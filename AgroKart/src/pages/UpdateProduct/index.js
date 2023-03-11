import React from 'react'
import { useState } from 'react'
import  { useEffect } from 'react'
import { toast } from 'react-toastify'
import { URL } from "../../config";
import { useNavigate } from 'react-router'
import axios from 'axios'

const UpdateProduct = () => {
    const { pid } = sessionStorage;
    const navigate = useNavigate()
    const[product, setProduct] = useState([]);
  
  //fetch Product
  const showproduct = () => {
    const url = `${URL}/products/productbyid?productId=${pid}`
    axios.get(url).then((response) => {
        const result = response.data
        console.log(result);
        if (result['status'] == 'success') {
            setProduct(result['data'])
           
        }
        else {
            toast.warning(result['error'])
        }
    })
}


    

useEffect(() => {
    console.log("calling method  profile")
    showproduct();

}, [])


console.log(product)
let name= product.productName
let image= product.productImage
let desc =product.productDescription
let qty = product.productQty
let price = product.productPrice
let finalprice = product.finalPrice
let dis = product.discount

console.log(name)
console.log(desc)
console.log(qty)
console.log(price)
console.log(finalprice)
console.log(dis)


    //update product
  
    const update = () => {

           
        const body = {
            productName:name,
            productImage:image,
            productDescription:desc,
            productQty:qty,
            productPrice:price,
            discount:dis,
            finalPrice:price-(price*dis/100),
            userId:product.userId,
            categoryId:product.categoryId

        }
        console.log(body)
        const url = `${URL}/products/updateproduct?productId=${pid}`

        console.log("updating products"+body)
        axios.put(url, body).then((response) => {
            // get the data from the response
            const result = response.data
            console.log(result)
            if (result['status'] == 'success') {
                toast.success('Successfully updated the Product')
                console.log(result);
                navigate("/uploadedproductbyuser")

            } else {
                toast.error(result['error'])
            }
        })
    }





    return (<div className="container-xxl p-0">

        <div class="container">
            <h3 className="label-control text-center m-3 text-danger fw-bold">Update Product</h3>
            <div class="row" id='container'>
                <div class="col-sm-2" >
                </div>

                <div class="col-sm-8 mt-3" id="addProduct">
                    <div className="form">

                        <div className="row" mb-3 >

                        </div>
                        
                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                Product Id
                            </label>


                            <input
                                type="text"
                                className="form-control"
                                placeholder='Product Id'
                                value={pid}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                Product Name
                            </label>


                            <input
                                onChange={(e)=>{name=e.target.value}
                                }
                               
                                type="text"
                                className="form-control"
                                placeholder='Product Name'
                               defaultValue={product.productName}
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                ProductImage
                            </label>


                            <input
                                onChange={(e)=>{image=e.target.value}
                                }
                                type="text"
                                className="form-control"
                                placeholder='Product Image'
                                defaultValue={product.productImage}
                            />
                        </div>



                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                ProductDescription
                            </label>
                            <input
                                onChange={(e)=>{desc=e.target.value
                                }
                                }
                                type="text"
                                className="form-control"
                                placeholder='Product Description'
                                defaultValue={product.productDescription}
                            />
                        </div>
                        <div className="row" mb-3 >
                            <div className="col">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Product Quantity
                                </label>
                                <input
                                    onChange={(e)=>{qty=e.target.value}}
                                    type="text"
                                    className="form-control"
                                    placeholder='Product Quantity'
                                defaultValue={product.productQty}
                                />

                            </div>




                            <div className="col">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    product Price
                                </label>
                                <input
                                    onChange={ (e)=>{price=e.target.value}
                                    }
                                    type="text"
                                    className="form-control"
                                    placeholder='Product Price '
                                    defaultValue={product.productPrice}
                                />


                            </div>

                            <div className="col">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Product Discount
                                </label>
                                <input
                                    onChange={(e)=>{dis=e.target.value}
                                    }
                                    type="text"
                                    className="form-control"
                                    placeholder='Product Discount '
                                    defaultValue= {product.discount}
                                />

                            </div>
                        </div>

                        <div className="mb-3 text-center mt-3 pt-3">
                            <button onClick={update} className="btn btn-lg btn-block btn-success fw-bold">
                            Update
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    );

}

export default UpdateProduct