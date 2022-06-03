import React from "react";
import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";
import {IBooking} from "../Interface/IBooking";

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

type ing = { 
    date: any,
    destination: any,
    origin: any
}

export const createBook = createAsyncThunk(
    'reimburse/create',
    async(book:book, thunkAPI) => {
        try{
            const res = await axios.post('http://localhost:8000/booking/',book);
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
              //axios.defaults.withCredentials = true;
            const res = await axios.post(`http://localhost:8000/booking/get`, tis);
  
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
        }
    },
    extraReducers: (builder) => {
        builder.addCase(createBook.pending, (state, action)=> {
            state.loading = true;
        });
        builder.addCase(createBook.fulfilled, (state, action) => {
            //The payload in this case, is the return from our asyncThunk from above
            state.current_booking = action.payload;
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
            state.current_booking= action.payload;
        });
        builder.addCase(userBooking.fulfilled, (state, action)=> {
            state.booking = action.payload;
        })
    }
})
    



 export const {toggleError} = BookSlice.actions;
export default BookSlice.reducer;