import SItem from "./SItem"

const AddToSummary = (props) => {

    const { order } = props
    console.log(order)

    return (
        <div >
            { 
                Object.values(order).map((item) => {
                    return 
                })
            }
             <SItem product = { order }/>
        </div>
    )
}

export default AddToSummary;