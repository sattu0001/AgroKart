import React from 'react'
import Logo from '../../images/Logo.png'
import './Header.css'
import Cart from '../../images/Cartlogo.png'
import HEADING2 from '../../assets/HEADING2.png'
import th from '../../assets/th.jpg'
import { useState } from 'react'
import { useNavigate } from 'react-router'
import Signin from '../../pages/Signin'
import Signup from '../../pages/Signup'

const Header = () => {

  const { userName } = sessionStorage;
  console.log(userName)
  const navigate = useNavigate()

  const logoutUser = () => {
    
    sessionStorage.removeItem("id");
    sessionStorage.removeItem("userName");
  
    navigate("/homePage");
    window.location.reload(false);//if we want to refresh the page after perform specific action

  };


  const profile = () =>
  {
    navigate("/profile")
  }
  const signIn = () =>
  {
  
    navigate("/signin")
  }
  const signUp = () =>
  {
    
    navigate("/signup")
  }
  return (

    <div  className='drops' >

      <nav className="navbar navbar-expand-sm" id="Nav">

        <a id="navbar-brand" href="/" data-mdb-toggle="tooltip" title="Home" className='ml-5 pl-5'>
          <div className='row pl-2'>
            <div class='row-sm-9 row-md-9 row-lg-9'><img id="logo2" src={Logo} alt="logo" /></div>
            {/* <div class='row-sm-2 row-md-2 row-lg-2 pl-3 ml-2'><h6 className='ml-3 pl-4 text-muted'> Shop Me</h6></div> */}
          </div>
        </a>
        <div className="container p-0" >
          <div class="col-lg-6 col-sm-6 col-md-6 col-xl-6">
            
          </div>

          <div className='col-auto text-right offset-md-3'>
            <a id="ImgCart" href="/cart" data-mdb-toggle="tooltip" data-mdb-placement="bottom" title="Cart"><img id="logo1" src={Cart} height="100%" width="100%" alt="logo1" href="#" /></a><br></br>
            <i className='text-success fst-normal fs-5 ml-2'> </i>
          </div>

          <div className="btn-group col-lg-1 col-sm-1 col-md-1 col-auto" id="down" >

            <button type="button" id="Actionbutton" className="btn btn-white btn-outline-danger dropdown-toggle dropdown-toggle-split border border-dark"
              data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <a  id='BtnMenu' data-mdb-toggle="tooltip" data-mdb-placement="bottom" title="Menu" className='badge text-wrap p-0 mr-9'>Welcome {userName}</a>

  </button> 
            <i class="fas fa-cog"></i>
            {userName == undefined &&
              <ul className="dropdown-menu border border-dark" id='d1' >

                <li><a className="dropdown-item"  href="/signup">User Registration</a></li>
                <li><a className="dropdown-item" href="/signin">User Login</a></li>

                <i class="fas fa-cog"></i>
                <div class="dropdown-divider"></div>
                <li><a className="dropdown-item" href="/faq">FAQ's</a></li>
              </ul>
            }

            {userName &&
              <ul className="dropdown-menu border border-dark" id='d1'>

                <li><a className="dropdown-item"  onClick={logoutUser}>Logout</a></li>
                <li><a className="dropdown-item" onClick={profile}>Profile</a></li>
                <i className="fas fa-cog"></i>
                <div class="dropdown-divider"></div>
                <li><a className="dropdown-item" href="/faq">FAQ's</a></li>
              </ul>
            }

          
          </div>
       {/*


          <div id="s1">
          <button id="b1"class="btn btn-primary btn-sm" onClick={signUp}>user Registration</button>
          
          <div style={{display:flag?'none':'block'}}>
          <button id="b1" class="btn btn-primary btn-sm" onClick={signIn}>User Login</button>
</div>
         <div style={{display:flag?'block':'none'}}>
        
            <button id="b1" class="btn btn-primary btn-sm" onClick={logoutUser}>Logout</button>
         </div>
          </div>
          
       */} 
</div>

      </nav>

    </div>
    
  )

}

export default Header