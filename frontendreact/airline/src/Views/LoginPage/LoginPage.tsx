import React, { useEffect } from 'react';
import {useNavigate } from 'react-router-dom';
import { useSelector }  from 'react-redux';
import { RootState } from '../../Store';
import { Login } from '../../Components/LoginForm/LoginForm';
import "./LoginPage.css";
import { Navbar } from '../../Components/Navbar/Navbar';
<<<<<<< HEAD

=======
import { Info } from '../../Components/UserInfor/UserInfor';
>>>>>>> master
export const LoginPage: React.FC = () => {

    const userState = useSelector ((state:RootState) => state.user);

    const navigator = useNavigate();
     useEffect(()=> {
          if(!userState.error && userState.user){
              navigator('/info');
          }
     }, [navigator, userState]);

    return(
        <>
        <Navbar />
        <div className="login-page">
<<<<<<< HEAD
            {userState.error ? <h2 className="login-error">Hey your email or password is invalid! Please try again.</h2> : <></>}
            <Login />
=======
            {userState.error ? <h2 className="login-error" >Hey your email or password is invalid! Please try again.</h2> : <></>}
          <Login />
        
>>>>>>> master
        </div>
        </>
    )

}
