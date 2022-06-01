import React, { useEffect, useInsertionEffect} from "react";
import { Navbar } from "../../Components/Navbar/Navbar";
import {useSelector, useDispatch} from 'react-redux';
import { AppDispatch, RootState } from "../../Store";
import { useNavigate } from "react-router-dom";
import { searchBooking } from "../../Slices/BookSlice";
export const SearchPage: React.FC = () => {
    
    const HomePage = useSelector((state:RootState)=>state.book);
    const dispatch: AppDispatch = useDispatch();
    const navigator = useNavigate();
   // useEffect(() => {
    //   if(!userInfo.user){
    //      navigator("/login");
    //   }
    // })
    return(
        <>
         <Navbar/>
          <h1>Welcome to Revature Airline</h1>
          <h2>Below are the flights available</h2>
            
        </>
    )
}
