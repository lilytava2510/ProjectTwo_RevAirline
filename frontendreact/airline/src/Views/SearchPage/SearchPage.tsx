
import React, { useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { RootState, AppDispatch } from "../../Store";
//import { Booking } from "../../Components/Booking/Booking";
//import { BookingPage } from "../../Components/Booking/BookingPage"
import { HomePage } from "../../Components/HomePage/HomePage";
import { Navbar } from "../../Components/Navbar/Navbar";
import { getCity } from "../../Slices/CitySlice";
import { createCity } from "../../Slices/CitySlice";
import { CreateCity } from "../../Components/CityCreate/City";
import { CityCreate } from "../../Components/CityCreate/CityCreate";
import { ICity } from "../../Interface/ICity";
import { BuyNow } from "../../Components/Booking/BuyNow";
import {Link} from 'react-router-dom';
import '../LoginPage/LoginPage.css';
export const SearchPage: React.FC = () => {
    
  
    const available = useSelector((state:RootState)=>state.book);
    const city = useSelector((state:RootState)=>state.city);
    const userState = useSelector ((state:RootState) => state.user);
    const dispatch:AppDispatch = useDispatch();
//const navigator = useNavigate();
    useEffect(()=> {
      if(!city.cities){
         dispatch(getCity());
     }
   },[city.cities, available, userState])
    return(
        <>
          <Navbar/>
          <h1 className="login">Welcome to Revature Airline</h1>
          {!userState.user?
          <li className="nav-item">
                    <Link to={"/create"} className="nav-link">Create an account</Link>
                </li> : <></> }
          <h2 className="login">Below are the flights available</h2>
          <HomePage/>
          {userState.user?.role == 3?
          <CityCreate />: <></>}
          <table className="login">
        <thead>
        <tr>
             <th>CityId</th>
             <th>Name</th>
             <th>Position</th>
          
         </tr>
         </thead>
        {city.cities?
          city.cities.map((post:ICity) => {
                return (
                  <CreateCity {...post} key={post.cityId}/>
            )
            }):
            <tr>
                <td>no posts to display</td>
            </tr>
            }
    
           </table>
           <table>
             
           {available.current_booking?
          <BuyNow {...available.current_booking}/>
          :<></>
           }
           
           </table>

         
        </>
    )
        }
