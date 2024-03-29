import React from "react";
import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";
import {IBooking} from "../Interface/IBooking";
import { ICity } from "../Interface/ICity";

interface BookSliceState {
    loading: boolean,
    error: boolean,
    booking?: IBooking[],
    current_booking?: IBooking
}

const initialBookState: BookSliceState = {
    loading: false,
    error: false
};

type book = {
    //bookingId: number,
    date: any,
    //price: any,
     origin: any,
    destination: any,
   
    userId: any
}
type tansfer = {
    date:any,
    destination: ICity,
    origin: ICity,
    price: number
}
type ing = { 
    date: any,
    destination: any,
    origin: any
}

export const createBook = createAsyncThunk(
    'book/create',
    async(book:book, thunkAPI) => {
        try{
            
            const res = await axios.post('http://localhost:8000/booking/',book);
            return res.data;
              
          } catch(e){
            return thunkAPI.rejectWithValue('wrong');
        }
    }
)

export const pointsBook = createAsyncThunk(
    'book/points',
    async(book:book, thunkAPI) => {
        try{
            const res = await axios.post('http://localhost:8000/booking/points',book);
            return res.data;
              
          } catch(e){
            return thunkAPI.rejectWithValue('wrong');
        }
    }
)

export const searchBooking = createAsyncThunk(
    "booking/get",
    async (tis:ing, thunkAPI) => {
        try{
            console.log(tis)
              //axios.defaults.withCredentials = true;
            const res = await axios.post(`http://localhost:8000/booking/price`, tis);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );

  export const userBooking = createAsyncThunk(
    "booking/getBId",
    async (tis:ing, thunkAPI) => {
        try{
              //axios.defaults.withCredentials = true;
            const res = await axios.post(`http://localhost:8000/booking/get`, tis);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );
  export const getBooks = createAsyncThunk(
    "books/get",
    async (userId:number, thunkAPI) => {
        try{
            console.log(userId);
            const res = await axios.get(`http://localhost:8000/booking/get/${userId}`);
  
            return res.data;
        } catch (e){
            console.log(e);
        }
        
    }  
  );



export const BookSlice = createSlice({
    name: "book",
    initialState: initialBookState,
    reducers: {
        toggleError : (state) => {
            state.error = !state.error;
        },
        clearBooking : (state) =>{
            state.current_booking = undefined;
<<<<<<< HEAD
            state.booking = undefined;
=======
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
        }
        
    
    },
    extraReducers: (builder) => {
        builder.addCase(createBook.pending, (state, action)=> {
            state.loading = true;
        });
        builder.addCase(createBook.fulfilled, (state, action) => {
<<<<<<< HEAD
            state.current_booking = undefined;
            state.booking = undefined;
=======
            state.current_booking = action.payload;
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
            state.error = false;
            state.loading = false;
        });
        builder.addCase(createBook.rejected, (state, action)=> {
            state.error = true;
            state.loading = false;
        });
        builder.addCase(searchBooking.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(searchBooking.fulfilled, (state, action) => {
            state.loading =false;
<<<<<<< HEAD
            state.error = false;
=======
            console.log(action.payload)
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
            state.current_booking= action.payload;
        });
        builder.addCase(searchBooking.rejected, (state, action)=> {
            state.error = true;
            state.loading = false;
        });
        builder.addCase(getBooks.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(getBooks.fulfilled, (state, action)=> {
            state.booking = action.payload;
            state.error = false;
            state.loading = false;
        })
<<<<<<< HEAD
        builder.addCase(getBooks.rejected, (state, action)=> {
            state.error = true;
            state.loading = false;
        });
=======
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
        builder.addCase(pointsBook.pending, (state, action)=> {
            state.loading = true;
        });
        builder.addCase(pointsBook.fulfilled, (state, action) => {
<<<<<<< HEAD
            state.current_booking = undefined;
            state.booking = undefined;
=======
            state.current_booking = action.payload;
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
            state.error = false;
            state.loading = false;
        });
        builder.addCase(pointsBook.rejected, (state, action)=> {
            state.error = true;
            state.loading = false;
        });
    }
})
    



 export const {toggleError, clearBooking} = BookSlice.actions;
export default BookSlice.reducer;