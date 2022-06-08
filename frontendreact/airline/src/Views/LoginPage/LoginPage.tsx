import React, { useEffect } from 'react';
import {useNavigate } from 'react-router-dom';
import { useSelector }  from 'react-redux';
import { RootState } from '../../Store';
import { Login } from '../../Components/LoginForm/LoginForm';
import "./LoginPage.css";
import { Navbar } from '../../Components/Navbar/Navbar';
import { Info } from '../../Components/UserInfor/UserInfor';
export const LoginPage: React.FC = () => {

    const userState = useSelector ((state:RootState) => state.user);

    const navigator = useNavigate();
     useEffect(()=> {
          if(!userState.error && userState.user){
              navigator('/info');
          }
     }, [userState]);

    return(
        <>
        <Navbar />
        <div className="login-page">
            {userState.error ? <h2 className="login-error" >Hey your email or password is invalid! Please try again.</h2> : <></>}
          <Login />
        
        </div>
        </>
    )

}