import { toast } from "react-toastify"

const Address = (props) => {

    const { add } = props
    console.log(add)

    const selectedAddress = () => {
        sessionStorage ['selectedAddressId'] = add.addressId
        console.log(add.addressId)
        toast.success("Address Selected Successfully !")
    }
    return (
        <div >
            <div className="form-check">
            <input className="form-check-input" 
                   type="radio" 
                   name="flexRadioDefault" 
                   id="flexRadioDefault2" 
                   onClick={selectedAddress}
                    />
                    <label className="form-check-label" for="flexRadioDefault2">
                        <div>
                            <div className="row ">
                                <h6>{add.fullAddress}, {' '}
                                                    {add.city}, {' '}
                                                    {add.district}, {' '}
                                                    {add.state},{' '}
                                                    {add.country} -
                                                    {add.userPincode}</h6>
                            </div>
                        </div>
                    </label>
            </div>
        </div>
    )
}

export default Address;