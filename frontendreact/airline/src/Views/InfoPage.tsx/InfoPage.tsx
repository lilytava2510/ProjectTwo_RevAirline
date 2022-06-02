import React, { useEffect } from "react";
import { Navbar } from "../../Components/Navbar/Navbar";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../Store";
import { useNavigate } from "react-router-dom";
import { IUser } from "../../Interface/IUser";
import { updateUser } from "../../Slices/UserSlice";
import { Info } from "../../Components/UserInfor/UserInfor";
import './InfoPage.css';

export const InfoPage: React.FC = () => {

const userInfo = useSelector((state:RootState) => state.user);
const dispatch:AppDispatch = useDispatch();
const navigator = useNavigate();
useEffect(()=> {
   if(userInfo.error && userInfo.user){
      navigator('/info');
  }
},[userInfo.user]);
return(
    <>
    <Navbar/>

    <h1 className="login"> {userInfo.user?.firstName}Profile Page</h1>
    <h2 className="login">Your Information Below</h2>
    <table className="login" >

                

               <th>
                <td>UserID: {userInfo.user?.userId}</td>
                <td>First Name: {userInfo.user?.firstName}</td>
                <td>LastName: {userInfo.user?.lastName}</td>
                <td>Email: {userInfo.user?.email}</td>
                <td>Password: {userInfo.user?.password}</td>
                <td>Credit Card: {userInfo.user?.ccn}</td>
                <td>Passport: {userInfo.user?.ppn}</td>
            </th>
            {userInfo.passenger?
            userInfo.passenger.map((post:IUser) => {
                return <Info {...post} key={post.userId}/>
            }): <Info/>
            
        }
        </table>
  </>
)

}