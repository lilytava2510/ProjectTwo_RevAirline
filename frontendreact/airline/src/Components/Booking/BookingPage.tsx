import { setDefaultResultOrder } from 'dns';
import React, {useState} from 'react';
//import "./LoginForm.css";
import { loginUser,toggleError, createUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";
import { BookSlice, createBook } from '../../Slices/BookSlice';
import { RootState,AppDispatch } from '../../Store';
import { IUser } from '../../Interface/IUser';
import { IBooking } from '../../Interface/IBooking';

export const BookCreate: React.FC = () => {


     
     const user = useSelector((state:RootState) => state.user);
     const book = useSelector((state:RootState) => state.book);

     const dispatch:AppDispatch = useDispatch();

 
    const [date, setDate] = useState<Date>();
    const [price, setPrice] = useState<any>();
    const [destination_city, setDestination_City] = useState<number>();
    const [origin_city, setOrigin_city] = useState<number>();
 
    //const [sick, setSick] = useState<any>();
 const sick = true;
 //const role = false;
 const userId = user.user?.userId;
    //const [privilege, setPrivilege] = useState<boolean>(user.user?.privilege?);
   
 

    const handleInput = (event:React.ChangeEvent<HTMLInputElement>) => {
        if(event.target.name === "date"){
            setDate(event.target.value);
        }
        else if(event.target.name === "price"){
            setPrice(event.target.value);  
        }
        else if(event.target.name === "destionation_city"){
            setDestination_City(event.target.value);  
        }  
    else if(event.target.name === "origin_city"){
        setOrigin_city(event.target.value);
    } 
  
}

 }
        
    const handleUpdate = (event:React.MouseEvent<HTMLButtonElement>) => {
        let book = {
            date,
            price,
            destination_city,
            origin_city
           
           
            

        };


      dispatch(createBook(book));
        
        
    }
    return(
        <div className="login">
            <div className="text-container">
                <h1 className="login-header"></h1>
                <h2 className="login-header">Book Your Flight Below.</h2>
            </div>
            <form className="login-form">
            <div className="input-div">
                  <h4 className="input-h4">Enter Date:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="firstname" placeholder="firstname" onChange={handleInput}/>
                </div>
            <div className="input-div">
                  <h4 className="input-h4">Price:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="lastname" placeholder="lastname" onChange={handleInput}/>
                </div>
                <div className="input-div">
                  <h4 className="input-h4">Enter Destination:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="username" placeholder="username" onChange={handleInput}/>
                </div>
                <div className="input-div">
                  <h4 className="input-h4">Enter Origin:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="email" placeholder="email" onChange={handleInput}/>
                </div>
                <div>
                <label>
          <input name="rememberMe" checked={true} onChange={handleInput} type="checkbox" /> Tested
                 </label>
                </div>
            </form>
                <button className="login-button" onClick={handleUpdate}>Create Account</button>
        </div>
    )
}
