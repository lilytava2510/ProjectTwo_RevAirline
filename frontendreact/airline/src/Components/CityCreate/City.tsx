//import { setDefaultResultOrder } from 'dns';
<<<<<<< HEAD
import React, {useState} from 'react';
=======
import React, {useEffect, useState} from 'react';
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
//import "./LoginForm.css";
//import { loginUser,toggleError, createUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";
//import { BookSlice, createBook } from '../../Slices/BookSlice';
import { RootState,AppDispatch } from '../../Store';
//import { IUser } from '../../Interface/IUser';
import { IBooking } from '../../Interface/IBooking';
import { ICity } from '../../Interface/ICity';
import { CityCreate } from './CityCreate';
<<<<<<< HEAD
=======
import { createCity } from '../../Slices/CitySlice';
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
export const CreateCity:React.FC<ICity> = (pla:ICity) => {

    const user = useSelector((state:RootState) => state.user);
    const city = useSelector((state:RootState) => state.city);
<<<<<<< HEAD


=======
              
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
    //  const dispatch:AppDispatch = useDispatch();

 
    // const [date, setDate] = useState<any>();
    // const [price, setPrice] = useState<any>();
    // const [destination, setDestination] = useState<any>();
    // const [origin, setOrigin] = useState<any>();
 
    //const [sick, setSick] = useState<any>();
 //const sick = true;
 //const role = false;
 //const userId = user.user?.userId;
 console.log(city.city?.cityId);
    return(
            <tr>
                <td>{pla.position}</td>
                <td>{pla.cityId}</td>
                <td>{pla.city}</td>
            </tr>
    )
}