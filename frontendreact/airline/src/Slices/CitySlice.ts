import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";
import {ICity} from "../Interface/ICity";

interface CitySliceState {
    loading: boolean,
    error: boolean,
    city?: ICity,
    cities?: ICity[]
}

const initialCityState: CitySliceState = {
    loading: false,
    error: false
}

export const CitySlice = createSlice({
    name: "city",
    initialState: initialCityState,
    reducers: {
        toggleError : (state) => {
            state.error = !state.error;
        }
    },
    extraReducers: (builder) => {}
})

   export const {toggleError} = CitySlice.actions;

export default CitySlice.reducer;