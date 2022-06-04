import { setDefaultResultOrder } from 'dns';
import React, {useState} from 'react';
//import "./LoginForm.css";
import { loginUser,toggleError, createUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";
import { BookSlice, createBook } from '../../Slices/BookSlice';
import { RootState,AppDispatch } from '../../Store';
import { IUser } from '../../Interface/IUser';
import { IBooking } from '../../Interface/IBooking';
import { ICity } from '../../Interface/ICity';
import { createCity } from '../../Slices/CitySlice';

export const CityCreate: React.FC = () => {


     
     const user = useSelector((state:RootState) => state.user);
     const book = useSelector((state:RootState) => state.book);
     const cities = useSelector((state:RootState) => state.city);


     const dispatch:AppDispatch = useDispatch();

     
    const [city, setCity] = useState<any>();
    const [position, setPosition] = useState<any>();
   
 
    //const [sick, setSick] = useState<any>();
 //const sick = true;
 //const role = false;
 const userId = user.user?.userId;
    //const [privilege, setPrivilege] = useState<boolean>(user.user?.privilege?);
   
 

    const handleInput = (event:React.ChangeEvent<HTMLInputElement>) => {
        if(event.target.name === "city"){
            setCity(event.target.value);
        }
       
        else if(event.target.name === "position"){
            setPosition(event.target.value);  
        }  
  
  
}

 
        
    const handleUpdate = (event:React.MouseEvent<HTMLButtonElement>) => {
        let place = {
            city,
            position
         
            
           
           
            

        };


      dispatch(createCity(place));
        
        
    }
    return(
        <div className="login">
            <div className="text-container">
                <h1 className="login-header"></h1>
                <h2 className="login-header">City.</h2>
            </div>
            <form className="login-form">
            
            {/*<div className="input-div">
                  <h4 className="input-h4">Price:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="lastname" placeholder="lastname" onChange={handleInput}/>
                </div>*/}
                 <div className="input-div">
                  <h4 className="input-h4">Enter :</h4>
                    <input autoComplete="off" className="login-input" type="text" name="city" placeholder="city" onChange={handleInput}/>
                </div>
                <div className="input-div">
                  <h4 className="input-h4">Enter :</h4>
                    <input autoComplete="off" className="login-input" type="text" name="position" placeholder="position" onChange={handleInput}/>
                </div>
            </form>
                <button className="login-button" onClick={handleUpdate}>Create City</button>
        </div>
    )
}