import React, { useEffect } from "react";
import { Navbar } from "../../Components/Navbar/Navbar";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../Store";
import { useNavigate } from "react-router-dom";
import { IUser } from "../../Interface/IUser";
import { createUser } from "../../Slices/UserSlice";
import { Info } from "../../Components/UserInfor/UserInfor";
import './InfoPage.css';

export const UserCreate: React.FC = () => {

const userInfo = useSelector((state:RootState) => state.user);
const dispatch:AppDispatch = useDispatch();
const navigator = useNavigate();
useEffect(()=> {
   if(userInfo.error && userInfo.user){
      navigator('/create');
  }
},[userInfo.user]);
return(
    <>
    <Navbar/>

    <h1 className="login"> Below You Can Create Your New Account:</h1>
    <h2 className="login">Fill out the information below:</h2>
    <table className="login" >

                

               
            {userInfo.passenger?
            userInfo.passenger.map((post:IUser) => {
                return <UserCreate {...post} key={post.userId}/>
            }): <UserCreate/>
            
        }
        </table>
  </>
)
    }