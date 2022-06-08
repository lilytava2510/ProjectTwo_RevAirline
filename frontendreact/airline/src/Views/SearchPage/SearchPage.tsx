<<<<<<< HEAD

=======
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
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
<<<<<<< HEAD
import {Link} from 'react-router-dom';
import "./SearchPage.css";
import '../LoginPage/LoginPage.css';
=======

import "./SearchPage.css";

>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
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
<<<<<<< HEAD
=======
   console.log(userState.user?.role);
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
    return(
        <>
          <Navbar/>
          
         
        
          <HomePage/>
<<<<<<< HEAD
          
          {userState.user?.role == 3?
          <CityCreate />: <></>}

          <div className="add" >
          {!userState.user?
          <li className="nav-item">
                    <Link to={"/create"} className="nav-link">Create an account</Link>
                </li> : <></> }

          </div>
          <h2 className="s">Below are the flights available</h2>
          <table>
    <thead className = "aviableflight">
      <tr>
        <td>Position</td>
        <td>CityId</td>
        <td>Name</td>
      </tr>
    </thead>
    <tbody className = "aviableflight">
      {city.cities?
=======
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
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
          city.cities.map((post:ICity) => {
                return (
                  <CreateCity {...post} key={post.cityId}/>
            )
            }):
            <tr>
                <td>no posts to display</td>
            </tr>
            }
<<<<<<< HEAD
    </tbody>
</table>
           <table className = "aviableflight">
=======
    
           </table>
           <table>
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
             
           {available.current_booking?
          <BuyNow {...available.current_booking}/>
          :<></>
           }
           
           </table>

         
        </>
    )
        }