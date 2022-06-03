
//import { setDefaultResultOrder } from 'dns';
import React, {useState} from 'react';
//import "./LoginForm.css";
//import { loginUser,toggleError, createUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";
//import { BookSlice, createBook } from '../../Slices/BookSlice';
import { RootState,AppDispatch } from '../../Store';
//import { IUser } from '../../Interface/IUser';
import { IBooking } from '../../Interface/IBooking';

export const BookingPage:React.FC<IBooking> = (book:IBooking) => {

    const user = useSelector((state:RootState) => state.user);
    

    //  const dispatch:AppDispatch = useDispatch();

 
    // const [date, setDate] = useState<any>();
    // const [price, setPrice] = useState<any>();
    // const [destination, setDestination] = useState<any>();
    // const [origin, setOrigin] = useState<any>();
 
    //const [sick, setSick] = useState<any>();
 //const sick = true;
 //const role = false;
 const userId = user.user?.userId;
 console.log(book.bookingid);
    return(
            <tr>
                <td>{book.bookingid}</td>
                <td>{book.date}</td>
                <td>{book.origin.city}</td>
                <td>{book.destination.city}</td>
                <td>{book.price}</td>
                <td>{userId}</td>
            </tr>
    )
}