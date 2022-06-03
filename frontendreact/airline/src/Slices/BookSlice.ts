import React from "react";
import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";
import {IBooking} from "../Interface/IBooking";

interface BookSliceState {
    loading: boolean,
    error: boolean,
    booking?: IBooking[]
}

const initialBookState: BookSliceState = {
    loading: false,
    error: false
};

type book = {
    bookingId: number,
    Date: Date,
    price: any,
    destination_city: any,
    origin_city: any,
    userId: any
}

type ing = { 
    date: any,
    destination_city: any,
    origin_city: any
}

export const createBook = createAsyncThunk(
    'book/create',
    async(book:book, thunkAPI) => {
        try{
            const res = await axios.post('http://localhost:8000/create/',book);
            return {

            }
        } catch(e){
            return thunkAPI.rejectWithValue('wrong');
        }
    }
)

export const searchBooking = createAsyncThunk(
    "booking/get",
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



export const BookSlice = createSlice({
    name: "book",
    initialState: initialBookState,
    reducers: {
        toggleError : (state) => {
            state.error = !state.error;
        }
    },
    extraReducers: (builder) => {
    
    }
})


 export const {toggleError} = BookSlice.actions;
export default BookSlice.reducer;