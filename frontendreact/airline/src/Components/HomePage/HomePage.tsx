import React, {useState} from 'react';
 import { ICity } from '../../Interface/ICity';
 import { IBooking } from '../../Interface/IBooking';
 import {AppDispatch , RootState} from  '../../Store';
 import { useDispatch, useSelector } from 'react-redux';
<<<<<<< HEAD
 import { clearBooking, searchBooking } from '../../Slices/BookSlice';
 import { SelectCity } from './SelectCity';
 import "./HomePage.css";
=======
 import { searchBooking } from '../../Slices/BookSlice';

 import "./HomePage.css";

>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
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
            //dispatch(clearBooking());
      dispatch(searchBooking(tis));
     }

     return(
     <>
         
         <div className="login">
             <div className="text-container">
             <h1 className="heading">Welcome to Revature Airline</h1>
                
 
             
             </div>
             <form className="login-form">
                 <div className="input-div">
                   <h4 className="input-h4">From:</h4>
                   <select className='from-input' name="from" id="from" onChange={handleSelect}>
                     <option className='from-input'></option>
              {routes.cities?
              routes.cities.map((element:ICity) => (
              <option  key={element.cityId} value={element.city} >{element.city}</option>
              )): <option></option>}</select>
                 </div>
                 <div className="input-div">
                     <h4 className="input-h4">To:</h4>
              <select className='from-input' name="to" id="to" onChange={handleSelect}>
              <option></option>
              {routes.cities?
              routes.cities.map((element:ICity) => (
              <option  key={element.cityId} value={element.city} >{element.city}</option>
              )): <option></option>}</select>   
                 </div>
                 <div className="input-div">
                     <h4 className="input-h4">Date:</h4>
                     <input className="from-input" type="text" name="date" placeholder="yyyy-mm-dd" onChange={handleInput}/>
                 </div>
 
                 <div className="input-div">
 
                 </div>
             </form>
                 <button className="login-button" onClick={handleSearch}>Search</button>
         </div>
     </>
     )
     }