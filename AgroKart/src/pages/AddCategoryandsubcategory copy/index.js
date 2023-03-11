import React from 'react'
import { useState } from 'react'
import { toast } from 'react-toastify'
import { URL } from "../../config";
import axios from 'axios'
import { useNavigate } from 'react-router'
const AddCategoryandsubCategory = () => {
    const { id } = sessionStorage;
    const [userId, setUserId] = useState(id);
    const navigate = useNavigate()
    const [categoryName, setcategoryName] = useState('');
    const [categoryDescription, setcategoryDescription] = useState('');
    
   

    const addCategory = () => {
        if (categoryName.length == 0) {
            toast.warning('Please enter categoryName')
          } //else if (categoryDescription.length == 0) {
            //toast.warning('Please enter categoryDescription')
         // }  
         else {
            const body = {
                categoryName,
                categoryDescription

            }



            const url = `${URL}/categories/addcategory`


            axios.post(url, body).then((response) => {
                // get the data from the response
                const result = response.data
                console.log(result)
                if (result['status'] == 'success') {
                    toast.success('Successfully added category')
                    
                    // navigate to the signin page
                    
                } else {
                    toast.error(result['error'])
                }
            })
        }
    }


   





    return (<div className="container-xxl p-0">


        <div class="container">
            <h3 className="label-control text-center m-3 text-danger fw-bold">Add Category </h3>
            <div class="row" id='container'>
                <div class="col-sm-2" >
                </div>

                <div class="col-sm-8 mt-3" id="addProduct">
                    <div className="form">

                        <div className="row" mb-3 >
                            <div className="col">
                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Category Name  <i className='text-danger'>*</i>
                                </label>
                               {/*} <select class="form-select"
                                    onChange={
                                        (e) => {
                                            setcategoryName(e.target.value)
                                        }} aria-label="Default select example">
                                    <option>select Category</option>
                                    <option value="eEquipments">Equipments</option>
                                    <option value="Seeds">Seeds</option>
                                    <option value="Fertilisers">Fertilisers</option>
                                    <option value="Pesticides">Pesticides</option>
                                    <option value="Insecticides">Insecticides</option>
                                    <option value="Mulching sheets ">Mulching sheets</option>
                                    </select>*/}
                                       <input
                                    onChange={(e) => {
                                       setcategoryName(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='Category Name'
                                />

                            </div>
                            <div className="col">

                                <label htmlFor="" className="label-control mb-2 fw-bold">
                                    Category Description  <i className='text-danger'>*</i>
                                </label>


                                <input
                                    onChange={(e) => {
                                       setcategoryDescription(e.target.value)
                                    }}
                                    type="text"
                                    className="form-control"
                                    placeholder='Category Description'
                                />

                            </div>
                        </div>
                        <div className="mb-3 text-center mt-3 pt-3">
                            <button onClick={addCategory} className="btn btn-lg btn-block btn-success fw-bold">
                                Add Category
                            </button>
                        </div>

                        
                                
                    </div>
                </div>
            </div>
        </div>
    </div>
    );
}

export default AddCategoryandsubCategory