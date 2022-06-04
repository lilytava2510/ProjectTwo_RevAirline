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
   console.log(userState.user?.role);
    return(
        <>
          <Navbar/>
          <h1>Welcome to Revature Airline</h1>
          <h2>Below are the flights available</h2>
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
