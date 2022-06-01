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


export const createBook = createAsyncThunk(
    'reimburse/create',
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