import logo from './logo.svg';
import './App.css';

import { Route, Routes } from 'react-router';
import Signup from './pages/Signup';
import Signin from './pages/Signin';
import Footer from './component/Footer/Footer';
import Header from './component/Header/Header';
import HomePage from './pages/HomePage';
import FAQ from "./pages/FAQ";
import ProfilePage from "./pages/ProfilePage";
import Cart from "./pages/Cart";
import { ToastContainer } from 'react-toastify';
import Menubar from './component/Menubar';
import ResetPassword from "./pages/ResetPassword/ResetPassword";
import Users from "./pages/Users/Index";
import AddCategoryandsubCategory from "./pages/AddCategoryandsubcategory copy";
import AddProduct from "./pages/AddProduct";
import PUploadedbyUser from "./pages/PUploadedbyUser";
import UpdateProduct from "./pages/UpdateProduct";
import ProductDetails from "./pages/ProductDetails copy";
import 'react-toastify/dist/ReactToastify.css';
import AddShowAddress from "./pages/AddShowAddress/AddShowAddress";
import AddAddress from "./pages/Address";
//import ShowOrders from "./component/ShowOrders/ShowOrders";
import Order from "./pages/Order"
import OrderSummary from "./pages/OrderSummary";
import AllOrder from "./pages/AllOrder";




function App() {
  return (
    <div >
      
          <Header/>
          <Menubar/>
             <div className="container">
          <Routes>
          <Route path="/signup" element={<Signup/>}/>
            <Route path="/signin" element={<Signin/>}/>
            <Route path="/cart" element={<Cart/>}/>
            <Route path="/faq" element={<FAQ/>}/>
            <Route path="/homepage" element={<HomePage/>}/>
            <Route path="/" element={<HomePage/>}/>
            <Route path="/profile" element={<ProfilePage/>}/>
            <Route path="/resetpassword" element={<ResetPassword/>}/>
            <Route path="/users" element={<Users/>}/>
            <Route path="/addcatesubcate" element={<AddCategoryandsubCategory/>}/>
            <Route path="/addproduct" element={<AddProduct/>}/>
            <Route path="/uploadedproductbyuser" element={<PUploadedbyUser/>}/>
            <Route path="/updateproduct" element={<UpdateProduct/>}/>
            <Route path="/productdetails" element={<ProductDetails/>}/>
            <Route path="/addaddress" element={<AddAddress/>}/>
           <Route path="/addshowaddress" element={<AddShowAddress/>}/>
           <Route path="/order" element={<Order/>}/>
           <Route path="/ordersummary" element={<OrderSummary/>}/>
           <Route path="/allorder" element={<AllOrder/>}/>
           
                  </Routes>
             </div>
             <ToastContainer  theme="colored" />
         <Footer/>
       
       
      
    </div>
  );
}
export default App;
