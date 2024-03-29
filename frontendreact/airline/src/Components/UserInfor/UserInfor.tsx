
import { setDefaultResultOrder } from 'dns';
import React, {useState} from 'react';
//import "./LoginForm.css";
import { loginUser,toggleError,updateUser } from '../../Slices/UserSlice';
import {useDispatch, useSelector} from "react-redux";

import { RootState,AppDispatch } from '../../Store';
import { IUser } from '../../Interface/IUser';

import "./UserInfor.css";

export const Info: React.FC = () => {
    
    const dispatch:AppDispatch = useDispatch();

     
     const user = useSelector((state:RootState) => state.user);



    // const [userId, setUserId] = useState<number>(0);
    const [firstName, setFirstName] = useState<string>("");
    const [lastName, setlastName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    // const [points, setPoints] = useState<any>();
 const [ccn, setCreditcard_number] = useState<any>();
    const [ppn, setPassport_number] = useState<any>();
    //const [sick, setSick] = useState<any>();
 const sick = true;
//    const role = false;
 //  const id = user.user?.userId;
    //const [privilege, setPrivilege] = useState<boolean>(user.user?.privilege?);
    const userId = user.user?.userId;
   const points = user.user?.points;
   const role = user.user?.role;
 

    const handleInput = (event:React.ChangeEvent<HTMLInputElement>) => {
        if(event.target.name === "email"){
            setEmail(event.target.value);
        }
        else if(event.target.name === "firstname"){
            setFirstName(event.target.value);  
        }
        else if(event.target.name === "lastname"){
            setlastName(event.target.value);  
        }  
    else if(event.target.name === "password"){
        setPassword(event.target.value);
    } 
    else if(event.target.name === "ccn"){
        setCreditcard_number(event.target.value);
    }
    else if(event.target.name === "ppn"){
        setPassport_number(event.target.value);
    }
    // else if(event.target.name === "rememberMe"){
    //     setSick(event.target.checked);
    // }


 }
        
    const handleUpdate = (event:React.MouseEvent<HTMLButtonElement>) => {
        let change = {
            userId, 
            email,
            password,
            points,
            role,
            firstName,
            lastName,
            ccn,
            sick,
            ppn
           
            

        };


      dispatch(updateUser(change));
        
        
    }
    return(
        <div className="login">
            <div className="text-container">
                <h1 className="login-header"></h1>
                <h2 className="login-header">Below please update your Information.</h2>
            </div>
            <form className="login-form">
            <div className="input-div">
                  <h4 className="input-h4">Enter New Firstname:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="firstname" placeholder="firstname" onChange={handleInput}/>
                </div>
            <div className="input-div">
                  <h4 className="input-h4">Enter New Lastname:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="lastname" placeholder="lastname" onChange={handleInput}/>
                 </div>
               {/* <div className="input-div">
                  <h4 className="input-h4">Enter New Username:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="username" placeholder="username" onChange={handleInput}/>
                </div> */}
                <div className="input-div">
                  <h4 className="input-h4">Enter New Email:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="email" placeholder="email" onChange={handleInput}/>
                </div>
                <div className="input-div">
                    <h4 className="input-h4">Enter New Password:</h4>
                    <input className="login-input" type="password" name="password" placeholder="password" onChange={handleInput}/>
                </div>
                <div className="input-div">
                <div className="input-div">
                  <h4 className="input-h4">Enter New Credit Card Number:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="ccn" placeholder="creditcard" onChange={handleInput}/>
                </div><div className="input-div">
                  <h4 className="input-h4">Enter New Passport Number:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="ppn" placeholder="passport" onChange={handleInput}/>
                </div>
                {/* <label>
          <input name="rememberMe" checked={true} onChange={handleInput} type="checkbox" /> Tested
                 </label> */}
                </div>
            </form>
                <button className="login-button" onClick={handleUpdate}>Update</button>
        </div>
    )
}