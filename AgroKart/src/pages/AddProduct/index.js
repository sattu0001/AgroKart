import React from 'react'
import { useState,useEffect } from 'react'
import { toast } from 'react-toastify'
import { URL } from "../../config";
import { useNavigate } from 'react-router'
import axios from 'axios'
const AddProduct = () => {
    const { id } = sessionStorage;
    const [userId, setUserId] = useState(id);
    const navigate = useNavigate()
    const [categoryId, setcategoryId] = useState('');
    const [subCategoryName, setsubCategoryName] = useState('');
    const [productName, setproductName] = useState('');
    const [productImage, setproductImage] = useState('');
    const [productDescription, setproductDescription] = useState('');
    const [productQty, setproductQty] = useState('');
    const [productPrice, setproductPrice] = useState('');
    const [discount, setDiscount] = useState('');
    const [finalPrice, setFinalPrice] = useState('');
    const [categoryName, setCategoryName] = useState('');
    const [category, setCategory] = useState([]); 

    //const [flag,setFlag] =useState(true)

    const Allcategory = () => {

        const url = `${URL}/categories`
        axios.get(url).then((response) => {
            
          const result = response.data
             
          console.log(result)
          setCategory(result['data'])
          console.log("Categories"+category);
           {/* if (result['status'] == 'success') {
                           SetRoles(result['data'])
                //setFlag(false)
               
            }
            else {
                toast.warning(result['error'])
            }
          */}

        })
    
    }
    useEffect(() => {
        console.log("calling method all Categories")
        Allcategory();
         }, [])








    //add category
    const addProduct = () => {
        if (productName.length == 0) {
            toast.warning('Please enter product Name')
            //   } else if (productImage.length == 0) {
            //     toast.warning('Please enter productImage')
        } else if (productDescription.length == 0) {
            toast.warning('Please enter productDescription')
        } else if (productQty.length == 0) {
            toast.warning('Please enter productQuantity')
        } else if (productPrice.length == 0) {
            toast.warning('Please enter  productPrice')
        } else if (discount.length == 0) {
            toast.warning('Please enter productDiscount ')
        } else {
            const body = {
                productName,
                // productImage,
                productDescription,
                productQty,
                productPrice,
                discount,
                finalPrice,
                userId,
                //user: { userId },
                //subCategory: { subCategoryName },
               categoryId

            }

            console.log(categoryName)
            //console.log(subCategoryName)

            const url = `${URL}/products/addproduct`


            axios.post(url, body).then((response) => {
                // get the data from the response
                const result = response.data
                console.log(result)
                if (result['status'] == 'success') {
                    toast.success('Successfully added the Product')
                    console.log(result['data']);

                    sessionStorage['productId'] = result['data'].productId

                    const formData = new FormData();

                    formData.append('file', productImage)

                    const productId = sessionStorage['productid']
                    console.log(productId)
                    const url = `${URL}/upload/file/product/${productId}`


                    const headers = { 'Accept': 'application/json', 'content-Type': 'multipart/form-data' }

                    axios.post(url, formData, headers).then((response) => {
                        const result = response.data

                        // window.alert("beware")
                        // toast.success("Now click on save changes to save Changes")

                        setproductImage(result['data'])
                        console.log(result['data'])

                    })
                    window.location.reload(false)

                }

                document.getElementById("button2").hidden = false;
              // navigate("/homepage")


            })
        }
    }







    return (<div className="container-xxl p-0">

        <div class="container">
            <h3 className="label-control text-center m-3 text-danger fw-bold">Add Products</h3>
            <div class="row" id='container'>



                <div class="col-sm-8 mt-3" id="addProduct">
                    <div className="form">

                        <div className="row" mb-3 >
                            <div className="col">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                select category <i className='text-danger'>*</i>
                            </label>

                                <select class="form-select"
                                    onChange={
                                        (e) => {
                                            setcategoryId(e.target.value)
                                        }} aria-label="Default select example">
                                    <option>select option below</option>
                                    {category.map(({categoryName,categoryId })=>(
                                         <option key={categoryName} value={categoryId}>    
                                            {categoryName}
                                         </option>
                                       ))}
  
                                </select>

                            </div>
                            
                        </div>


                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                Product Name <i className='text-danger'>*</i>
                            </label>


                            <input
                                onChange={(e) => {
                                    setproductName(e.target.value)
                                }}
                                type="text"
                                className="form-control"
                                placeholder='Product Name'
                            />
                        </div>

                        {/* <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                ProductImage <i className='text-danger'>*</i>
                            </label>


                            <input
                                onChange={(e) => {
                                    setproductImage(e.target.value)
                                }}
                                type="text"
                                className="form-control"
                                placeholder='Product Image'
                            />
                        </div> */}



                        <div className="mb-3">
                            <label htmlFor="" className="label-control mb-2 fw-bold">
                                ProductDescription <i className='text-danger'>*</i>
                            </label>
                            <input
                                onChange={(e) => {
                                    setproductDescription(e.target.value)
                                }}
                                type="text"
                                className="form-control"
                                placeholder='Product Description'
                            />


                        </div>



                        <div className="row" mb-3 >
                            <div className="col">

                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Product Quantity <i className='text-danger'>*</i>
                                </label>
                                <input
                                    onChange={(e) => {
                                        setproductQty(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='Product Quantity'
                                />

                            </div>




                            <div className="col">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    product Price <i className='text-danger'>*</i>
                                </label>
                                <input
                                    onChange={(e) => {
                                        setproductPrice(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='Product Price '
                                />


                            </div>

                            <div className="col">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Product Discount <i className='text-danger'>*</i>
                                </label>
                                <input
                                    onChange={(e) => {
                                        setDiscount(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='Product Discount '
                                />

                            </div>
                        </div>

                        <div class="form-group">
                    <form >
                        <input type="file" class="form-control-file" id="File1" onChange={(e) => setproductImage(e.target.files[0])} />
                    </form>
                </div>

                        <div className="mb-3 text-center mt-3 pt-3">
                            <button  id='button2'onClick={addProduct} className="btn btn-lg btn-block btn-success fw-bold">
                                Add Product
                            </button>

                        </div>


                        
                        <div></div>
                    </div>
                </div>


              


            </div>
        </div>
    </div>
    );

}

export default AddProduct