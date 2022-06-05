import React, {useState} from 'react';
import { RootState,AppDispatch } from '../../Store';
import { ICity } from '../../Interface/ICity';

//import { setDefaultResultOrder } from 'dns';

//import "./LoginForm.css";
//import { loginUser,toggleError, createUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";
//import { BookSlice, createBook } from '../../Slices/BookSlice';

//import { IUser } from '../../Interface/IUser';



export const SelectCity:React.FC<ICity> = (pla:ICity) => {

    //const user = useSelector((state:RootState) => state.user);
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
    return(
        <option value={pla.city}>{pla.city}</option>
    )
}