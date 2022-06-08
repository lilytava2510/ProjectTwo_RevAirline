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
<<<<<<< HEAD
const bookInfo = useSelector((state:RootState) => state.book);
=======
//const book = useSelector((state:RootState) => state.book);
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
const dispatch:AppDispatch = useDispatch();
const navigator = useNavigate();
let check = true;
useEffect(()=> {
<<<<<<< HEAD
   if(!userInfo.user){
      navigator('/login');
  }else if(!bookInfo.booking && userInfo.user){;
     dispatch (getBooks(userInfo.user.userId));}
},[userInfo.user, bookInfo.booking]);
=======
   if(userInfo.error && !userInfo.user){
      navigator('/login');
  }
 // else if (!userInfo.user.role)
 
      
  
//   else if(!book.booking && userInfo.user){
//     console.log(userInfo.user.userId);
//     dispatch (getBooks(userInfo.user.userId));}
},[userInfo.user]);
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
return(
    <>
    <Navbar/>

<<<<<<< HEAD
    <h1 className="login"> Welcome  {userInfo.user?.firstName}</h1>
    <h2 className="login">Your Information Below</h2>

    <table className="user-info" >
        <thead>
               <tr>
                <td>Points: {userInfo.user?.points}</td>
=======
    <h1 className="login"> {userInfo.user?.firstName} Profile Page</h1>
    <h2 className="login">Your Information Below</h2>
    <table className="login" >
        <thead>
               <tr>
                <td>UserID: {userInfo.user?.userId}</td>
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
                <td>First Name: {userInfo.user?.firstName}</td>
                <td>LastName: {userInfo.user?.lastName}</td>
                <td>Email: {userInfo.user?.email}</td>
                <td>Password: {userInfo.user?.password}</td>
                <td>Credit Card: {userInfo.user?.ccn}</td>
                <td>Passport: {userInfo.user?.ppn}</td>
<<<<<<< HEAD
              </tr>
        </thead>
=======
            </tr>
            </thead>
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
            {userInfo.passenger?
            userInfo.passenger.map((post:IUser) => {
                return <Info {...post} key={post.userId}/>
            }): <Info/>
            
        }
<<<<<<< HEAD
    </table>
        
        
    <table className="login">
        <thead>
          <tr>
=======
        </table>
        <table className="login">
        <thead>
        <tr>
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
             <th>BookingId</th>
             <th>Date</th>
             <th>Origin</th>
             <th>Destination</th>
             <th>Price</th>
             <th>PassengerId</th>
<<<<<<< HEAD
          </tr>
         </thead>
        {bookInfo.booking?
          bookInfo.booking.map((post:IBooking) => {
=======
         </tr>
         </thead>
        {userInfo.user?.bookingList?
          userInfo.user.bookingList.map((post:IBooking) => {
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
                return (
                <BookingPage {...post} key={post.bookingid}/>
            )
            }):
            <tr>
                <td>no tickets to display</td>
            </tr>}
<<<<<<< HEAD
    </table>
=======
           </table>
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6

    </>      
)

}