import { useState } from 'react'
import React, { useEffect } from 'react'
import "./index.css"
import { useNavigate } from 'react-router'
const Product = (props) => {
    const navigate = useNavigate()
    const { product } = props
    console.log(product.productImage);



    const forward = () => {
        sessionStorage['productId'] = product.productId
        sessionStorage['productName'] = product.productName
        sessionStorage['productDescription'] = product.productDescription
        sessionStorage['productImage'] = product.productImage
        sessionStorage['categoryId'] = product.categoryId
        sessionStorage['productPrice'] = product.productPrice
        sessionStorage['productQty'] = product.productQty
        sessionStorage['discount'] = product.discount
        sessionStorage['finalPrice'] = product.finalPrice
        sessionStorage['userId'] = product.userId
       
        navigate('/productdetails')
        
    }



    return (<div className='col-11 col-md-6 col-lg-3 mx-o mb-4' >
    <div id="container" className="card p-0 overflow-hidden h-100 shadow">



        <img id="pimg" src={product.productImage} className="card-img-top img-fluid" />

        <div className="card-body text-center" > 

        </div>
        <div >
            <h5 className="card-title" id='details'>{product.productName}</h5>

            <div class="item-detail" style={{ minHeight: "50px", maxheight: "50px",backgroundColor:"azure" }}>



                <div class="item-price" >
                    <div align="center">                    <span class="new-price" style={{ fontWeight: "bold", fontSize: "18px",paddingLeft:"18px" }}>QTY:{product.productQty}</span></div>
                    <span class="new-price" style={{ fontWeight: "bold", fontSize: "18px",paddingLeft:"18px" }}>₹{product.finalPrice}</span>
                    <span class="old-price" style={{ textDecoration: "line-through", paddingLeft: "50px",paddingRight:"10px", color: "grey", fontWeight: "bold", fontSize: "16px" }}>₹{product.productPrice}</span>
                    <div class="_3Ay6Sb" style={{ fontWeight: "bold", fontSize: "18px", fontWeight: "bold", textAlign: "center", color: "green" }}><span>{product.discount}% off</span></div>

                </div>
                
            </div>
        </div>
        <button type="button" onClick={forward} className="btn btn-warning float-right" >Show Details</button>
    </div>
</div>

)



}

export default Product