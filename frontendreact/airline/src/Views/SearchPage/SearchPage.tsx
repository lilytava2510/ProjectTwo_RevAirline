
import React, {useEffect} from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../Store";
//import { Booking } from "../../Components/Booking/Booking";
//import { BookingPage } from "../../Components/Booking/BookingPage"
import { HomePage } from "../../Components/HomePage/HomePage";
import { Navbar } from "../../Components/Navbar/Navbar";



export const SearchPage: React.FC = () => {
    
  const dispatch:AppDispatch = useDispatch();
    const available = useSelector((state:RootState)=>state.book);
    const routes = useSelector((state:RootState)=>state.city);
    useEffect(()=> {
      if(!routes.cities){
         dispatch();
     }

   },[routes.cities]);
    return(
        <>
          <Navbar/>
          <h1>Welcome to Revature Airline</h1>
          <h2>Below are the flights available</h2>
          <HomePage/>
         {/* // <Booking/>
          //<BookingPage/> */}
        </>
    )
        }
