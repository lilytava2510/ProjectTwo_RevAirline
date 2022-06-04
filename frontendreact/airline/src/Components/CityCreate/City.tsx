//import { setDefaultResultOrder } from 'dns';
import React, {useEffect, useState} from 'react';
//import "./LoginForm.css";
//import { loginUser,toggleError, createUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";
//import { BookSlice, createBook } from '../../Slices/BookSlice';
import { RootState,AppDispatch } from '../../Store';
//import { IUser } from '../../Interface/IUser';
import { IBooking } from '../../Interface/IBooking';
import { ICity } from '../../Interface/ICity';
import { CityCreate } from './CityCreate';
import { createCity } from '../../Slices/CitySlice';
export const CreateCity:React.FC<ICity> = (pla:ICity) => {

    const user = useSelector((state:RootState) => state.user);
    const city = useSelector((state:RootState) => state.city);
              
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