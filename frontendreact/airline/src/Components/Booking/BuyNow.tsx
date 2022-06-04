
//import { setDefaultResultOrder } from 'dns';
import React, {useState} from 'react';
//import "./LoginForm.css";
//import { loginUser,toggleError, createUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";
import { BookSlice, createBook , pointsBook } from '../../Slices/BookSlice';
import { RootState,AppDispatch } from '../../Store';
//import { IUser } from '../../Interface/IUser';
import { IBooking } from '../../Interface/IBooking';

export const BuyNow:React.FC<IBooking> = (book:IBooking) => {

    const user = useSelector((state:RootState) => state.user);
    //const bookingid = book.bookingid;
    const date = book.date;
    const origin = book.origin.city;
    const destination = book.destination.city;
    //const price = book.price;
   const userId = user.user?.userId;

    const dispatch:AppDispatch = useDispatch();

    const handleBuy = (event:React.MouseEvent<HTMLButtonElement>) => {
        let tis = {
            date,
            origin,
            destination,
            userId
            
        };

     dispatch(createBook(tis));
    }

    const handlePoints = (event:React.MouseEvent<HTMLButtonElement>) => {
        let tis = {
            date,
            origin,
            destination,
            userId
            
        };

     dispatch(pointsBook(tis));
    }
    // const [date, setDate] = useState<any>();
    // const [price, setPrice] = useState<any>();
    // const [destination, setDestination] = useState<any>();
    // const [origin, setOrigin] = useState<any>();
 
    //const [sick, setSick] = useState<any>();
 //const sick = true;
 //const role = false;
 
    return(
        <tbody>
            <tr>
                {/*<td>{book.bookingid}</td>*/}
                <td>{book.date}</td>
                <td>{book.origin.city}</td>
                <td>{book.destination.city}</td>
                <td>{book.price}</td>
                <td>{userId}</td>
            </tr>
            <tr>
                    {(user.user?.role == 2 || user.user?.role == 1)?
                <td>
                    <button className="buy-button" onClick={handleBuy}>Use credit</button> 
                </td>: <></> }
                {(user.user?.role == 2)?
                <td>
                    <button className="points-button" onClick={handlePoints}>Use points</button>
                </td>: <></>}
            </tr>
        </tbody>
    )
}