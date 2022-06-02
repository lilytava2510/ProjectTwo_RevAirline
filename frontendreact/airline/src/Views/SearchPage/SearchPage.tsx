
import React from "react";

import {useSelector} from 'react-redux';
<<<<<<< HEAD
import { Booking } from "../../Components/Booking/Booking";
import { BookingPage } from "../../Components/Booking/BookingPage";
=======
//import { Booking } from "../../Components/Booking/Booking";
//import { BookingPage } from "../../Components/Booking/BookingPage"
>>>>>>> lilianne
import { HomePage } from "../../Components/HomePage/HomePage";
import { Navbar } from "../../Components/Navbar/Navbar";
import { RootState } from "../../Store";


export const SearchPage: React.FC = () => {
    
    const available = useSelector((state:RootState)=>state.book);

    return(
        <>
          <Navbar/>
          <h1>Welcome to Revature Airline</h1>
          <h2>Below are the flights available</h2>
          <HomePage/>
<<<<<<< HEAD
          <Booking/>
          <BookingPage/>
=======
         {/* // <Booking/>
          //<BookingPage/> */}
>>>>>>> lilianne
        </>
    )
}
