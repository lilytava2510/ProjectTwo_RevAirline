
import React, {useState} from 'react';
import { ICity } from '../../Interface/ICity';
import { IBooking } from '../../Interface/IBooking';
import {AppDispatch , RootState} from  '../../Store';
import { useDispatch, useSelector } from 'react-redux';
export const HomePage: React.FC = () =>{
    
    const [to, setTo] = useState<string>("");
    const [from, setFrom] = useState<string>("");
    const [date, setDate] = useState<string>("");
  

   const dispatch: AppDispatch = useDispatch();

    const handleInput = (event:React.ChangeEvent<HTMLInputElement>) => {
        if(event.target.name === "to"){
            setTo(event.target.value);
        }

        else if(event.target.name === "from"){
            setFrom(event.target.value);
            }else{
                setDate(event.target.value);
            }

            
        }

        

    
    const handleSearch = (event:React.MouseEvent<HTMLButtonElement>) => {
        let credentials = {
            to,
            from,
            date

        };

      dispatch(searchBooking(credentials));
        
        
    }
    return(
    <>
        
        <div className="login">
            <div className="text-container">
                <h1 className="login-header">Welcome to Revature Airlines: </h1>
               

              
            </div>
            <form className="login-form">
                <div className="input-div">
                  <h4 className="input-h4">To:</h4>
                    <input autoComplete="off" className="login-input" type="text" name="to" placeholder="email" onChange={handleInput}/>
                </div>
                <div className="input-div">
                    <h4 className="input-h4">From:</h4>
                    <input className="login-input" type="password" name="from" placeholder="from" onChange={handleInput}/>
                </div>
                <div className="input-div">
                    <h4 className="input-h4">Date:</h4>
                    <input className="login-input" type="password" name="date" placeholder="yyyy-mm-dd" onChange={handleInput}/>
                </div>

                <div className="input-div">

                </div>
            </form>
                <button className="login-button" onClick={handleSearch}>Search</button>
        </div>
    </>
    )
    }



