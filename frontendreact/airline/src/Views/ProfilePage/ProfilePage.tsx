import React, { useEffect } from "react";
import { Navbar } from "../../Components/Navbar/Navbar";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../Store";
import { useNavigate } from "react-router-dom";
import { IUser } from "../../Interface/IUser";
import { updateUser } from "../../Slices/UserSlice";
import { Info } from "../../Components/UserInfor/UserInfor";
import './InfoPage.css';
import { IBooking } from "../../Interface/IBooking";
import { BookingPage } from "../../Components/Booking/Booking";
import { getBooks } from "../../Slices/BookSlice";

export const InfoPage: React.FC = () => {

const userInfo = useSelector((state:RootState) => state.user);
const bookInfo = useSelector((state:RootState) => state.book);
const dispatch:AppDispatch = useDispatch();
const navigator = useNavigate();
let check = true;
useEffect(()=> {
   if(!userInfo.user){
      navigator('/login');
  }else if(!bookInfo.booking && userInfo.user){;
     dispatch (getBooks(userInfo.user.userId));}
},[userInfo.user, bookInfo.booking]);
return(
    <>
    <Navbar/>

    <h1 className="login"> Welcome  {userInfo.user?.firstName}</h1>
    <h2 className="login">Your Information Below</h2>

    <table className="user-info" >
        <thead>
               <tr>
                <td>Points: {userInfo.user?.points}</td>
                <td>First Name: {userInfo.user?.firstName}</td>
                <td>LastName: {userInfo.user?.lastName}</td>
                <td>Email: {userInfo.user?.email}</td>
                <td>Password: {userInfo.user?.password}</td>
                <td>Credit Card: {userInfo.user?.ccn}</td>
                <td>Passport: {userInfo.user?.ppn}</td>
              </tr>
        </thead>
            {/* {userInfo.passenger?
            userInfo.passenger.map((post:IUser) => {
                return <Info {...post} key={post.userId}/>
            }): <Info/>
            
        } */}
    </table>
        
        
    <table className="login">
        <thead>
          <tr>
             <th>BookingId</th>
             <th>Date</th>
             <th>Origin</th>
             <th>Destination</th>
             <th>Price</th>
             <th>PassengerId</th>
          </tr>
         </thead>
        {bookInfo.booking?
          bookInfo.booking.map((post:IBooking) => {
                return (
                <BookingPage {...post} key={post.bookingid}/>
            )
            }):
            <tr>
                <td>no tickets to display</td>
            </tr>}
    </table>

    </>      
)

}
