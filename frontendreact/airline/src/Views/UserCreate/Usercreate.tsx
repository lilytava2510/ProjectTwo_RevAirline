import React, { useEffect } from "react";
import { Navbar } from "../../Components/Navbar/Navbar";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../Store";
import { useNavigate } from "react-router-dom";
import { IUser } from "../../Interface/IUser";
import { createUser } from "../../Slices/UserSlice";
import { Info } from "../../Components/UserInfor/UserInfor";
import { Create } from "../../Components/UserCreate/UserCreate";
import '../LoginPage/LoginPage.css';

export const UserCreate: React.FC = () => {

const userInfo = useSelector((state:RootState) => state.user);
const dispatch:AppDispatch = useDispatch();
const navigator = useNavigate();
 useEffect(()=> {
    if(!userInfo.error && userInfo.user){
       navigator('/info');
  }
 },[userInfo.user]);
return(
    <>
    <Navbar/>
    <h2 className="create-header">Create Your New Account:</h2>
    <div className="login" >
            <Create/>
        </div>
  </>
)
    }