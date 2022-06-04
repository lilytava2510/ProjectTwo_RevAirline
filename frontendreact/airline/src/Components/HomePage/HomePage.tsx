import React, {useState} from 'react';
 import { ICity } from '../../Interface/ICity';
 import { IBooking } from '../../Interface/IBooking';
 import {AppDispatch , RootState} from  '../../Store';
 import { useDispatch, useSelector } from 'react-redux';
 import { clearBooking, searchBooking } from '../../Slices/BookSlice';
 import { SelectCity } from './SelectCity';
 export const HomePage: React.FC = () =>{
    
    const routes = useSelector((state:RootState)=>state.city);
    const ticket = useSelector((state:RootState)=>state.book);
    const [destination, setDestination] = useState<any>("");
    const [origin, setOrigin] = useState<any>("");
    const [date, setDate] = useState<any>("");
   
      const user = useSelector((state:RootState) =>state.user)
    const dispatch: AppDispatch = useDispatch();
 
     const handleInput = (event:React.ChangeEvent<HTMLInputElement>) => {
        //  if(event.target.name === "to"){
        //      setDestination(event.target.value);
        //  }
 
        //  else if(event.target.name === "from"){
        //      setOrigin(event.target.value);
        //      }else{
                 setDate(event.target.value);
           //  }
 
             
         }
         const handleSelect = (event:React.ChangeEvent<HTMLSelectElement>) =>{
            if(event.target.id === "to"){
                console.log(event.target.value);
                setDestination(event.target.value);
            }
    
            else if(event.target.id === "from"){
                console.log(event.target.value);
                setOrigin(event.target.value);
                }
         }
         
 
     
     const handleSearch = (event:React.MouseEvent<HTMLButtonElement>) => {
         let tis = {
             date,
             origin,
             destination
             
         };
            dispatch(clearBooking());
      dispatch(searchBooking(tis));
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
                     <input autoComplete="off" className="login-input" type="text" name="to" placeholder="to" onChange={handleInput}/>
                 </div>
                 <div className="input-div">
                     <h4 className="input-h4">From:</h4>
                     <select name="type" id="from" onChange={handleSelect}>
                     <option></option>
              {routes.cities?
              routes.cities.map((element:ICity) => (
              <option  key={element.cityId} value={element.city} >{element.city}</option>
              )): <option></option>}</select>
              <select name="type" id="to" onChange={handleSelect}>
              <option></option>
              {routes.cities?
              routes.cities.map((element:ICity) => (
              <option  key={element.cityId} value={element.city} >{element.city}</option>
              )): <option></option>}</select>
                        
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