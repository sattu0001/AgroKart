import axios from "axios"
import { useEffect, useState } from "react"
import { toast } from "react-toastify"
import "./item.css"
import { URL } from "../../config"

const Item = (props) => {
    const { product } = props
    console.log("product :")
    console.log(product)
    console.log(product.cartId)
    const [productQty, setQty] = useState(product.qty)
    const [quantity, setQuantity] = useState(1)
    const [productStockQty, setproductStockQty] = useState(product.product.productQty)
    const productorigionalqty = product.product.productQty
    console.log(product)
    console.log(product.product.productQty)

    const body = {
       productQty
    }

    //Increament Qty
    const incrementCount = () => {
        const productId = product.productId
        const userId = sessionStorage['id']
        const url = `${URL}/cart/add-qty?cartId=${product.cartId}`

        axios.put(url, body).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {

                setQty(productQty + 1)
                // setIncrement(result['data'])
                console.log(result['data'])
                // window.location.reload(false)


            }
            else {
                toast.error("cannot be incremented")
            }
        })

    }

    //decreament quantity
    const decrementCount = () => {

        const productId = product.product.productId
           const userId = sessionStorage['id']

        const url = `${URL}/cart/remove-qty?cartId=${product.cartId}`

        axios.put(url, body).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                setQty(productQty - 1)
                console.log(result['data'])


            } else {
                toast.error("cannot be decremented")
            }
        })
    }

    console.log(product.id)


    //remove product from cart
    const deleteProduct = () => {
        const userId = sessionStorage['id']
       const url = `${URL}/cart/delete-cart-item?cartId=${product.cartId}`
     
        axios.delete(url).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                toast.success("Product deleted successfully")
                window.location.reload(false)
            } else {
                toast.error("Not able to delete product")
            }
        })
    }



    return <div>

        <div className="row justify-content-between align-items-center pt-lg-4 pt-2 pb-3 border-bottom mobile">
            <div className="row align-items-center">
                <div className="col-4">
                    <img src={product.product.productImage} width="auto" height="200" alt="" id="image" />
                </div>
                <div className="col-8 pl-md-3 pl-1">
                    <div className="row">


                        <div id="ItemProductDetailsCol" className="col-8 mt-2">
                            <h5>{product.productName}</h5>
                            <span className="pl-1" >Description:  {product.product.productDescription}</span>
                        </div>
                        <div className="col-1">

                        </div>
                        <div className="col-3 text-center mt-4">

                            <button class="btn btn-outline-success btn-sm" type="button" onClick={decrementCount} disabled={productQty == 1}>-</button>
                            <span className="fa fa-minus-square text-secondary"></span><span className="px-md-3 px-1" >{productQty}</span><span className="fa fa-plus-square text-secondary"></span>
                            <button class="btn btn-outline-danger btn-sm" type="button" onClick={incrementCount} disabled={productQty > (productorigionalqty - 1)}  >+</button>
                            <hr />
                            <div className="btn btn-danger align-center btn-md active" onClick={deleteProduct}>Delete</div>
                        </div>
                    </div>

                    <div className="row pt-5">
                        <div className="col-6">
                            <div id="ItemFinalPriceCol" className="col "> Price :  Rs.{product.product.finalPrice}</div>
                        </div>
                        <div className="col-2">

                        </div>
                        <div className="col-4">
                            <div id="ItemSubTotalCol" className="text-right">Sub Total : Rs {productQty * product.product.finalPrice} </div>
                        </div>

                    </div>
                </div>
            </div>

        </div>

    </div>

}

export default Item