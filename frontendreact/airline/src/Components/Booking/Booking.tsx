
import { setDefaultResultOrder } from 'dns';
import React, {useState} from 'react';
//import "./LoginForm.css";
import { loginUser,toggleError, createUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";
import { BookSlice, createBook } from '../../Slices/BookSlice';
import { RootState,AppDispatch } from '../../Store';
import { IUser } from '../../Interface/IUser';
import { IBooking } from '../../Interface/IBooking';

export const BookingPage: React.FC <IBooking>= (book:IBooking) => {

    const user = useSelector((state:RootState) => state.user);
    

     const dispatch:AppDispatch = useDispatch();

 
    const [date, setDate] = useState<any>();
    const [price, setPrice] = useState<any>();
    const [destination, setDestination] = useState<any>();
    const [origin, setOrigin] = useState<any>();
 
    //const [sick, setSick] = useState<any>();
 const sick = true;
 //const role = false;
 const userId = user.user?.userId;
 console.log(book.bookingId);
    return(
        <>
        <table>
         <th>

             <td>BookingId</td>
             <td>Date</td>
             <td>Origin</td>
             <td>Destination</td>
             <td>Price</td>
             <td>PassengerId</td>

         </th>
            <tr>
                <td>{book.bookingId}</td>
                <td>{book.date}</td>
                <td>{book.origin}</td>
                <td>{book.destination}</td>
                <td>{book.price}</td>
                <td>{book.userId}</td>
            </tr>
        </table>
           
        </>
    )
}