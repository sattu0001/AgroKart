import Item from '../Item/Item'
import { useState } from 'react'
const AddToCart = (props) => {

    const { cart } = props
    sessionStorage['total'] = cart
    console.log("printing from Add to cart")
    console.log(cart)
    return (
        <div >
           {/*{Object.values(cart).map((item) => {
                //console.log("Printing from add to cart"+item)
              

            })}*/}

            { console.log(cart)
               }
            <Item product={cart} />
        </div>
    )
}

export default AddToCart;