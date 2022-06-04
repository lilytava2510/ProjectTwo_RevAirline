import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";
import {ICity} from "../Interface/ICity";

interface CitySliceState {
    loading: boolean,
    error: boolean,
    city?: ICity,
    cities?: ICity[],
    options?:ICity[]
}

const initialCityState: CitySliceState = {
    loading: false,
    error: false
};

type place = {
    city: any,
    position: any,


}

export const createCity = createAsyncThunk(
    '/city/',
    async(pla:place, thunkAPI) => {
        try{
            const res = await axios.post('http://localhost:8000/city/',pla);
            return res.data;
              
          } catch(e){
            return thunkAPI.rejectWithValue('wrong');
        }
    }
)
export const getCity = createAsyncThunk(
    '/city/get',
    async(thunkAPI) => {
        try{
            const res = await axios.get('http://localhost:8000/city/get');
            return res.data;
              
          } catch (e){
            console.log(e);
        }
    }
)

export const CitySlice = createSlice({
    name: "city",
    initialState: initialCityState,
    reducers: {
        toggleError : (state) => {
            state.error = !state.error;
        },
        clearCities : (state) => {
            state.cities = undefined;
        }

    },
    extraReducers: (builder) => {
            builder.addCase(getCity.pending, (state, action)=> {
                state.loading = true;
            });
            builder.addCase(getCity.fulfilled, (state, action) => {
                //The payload in this case, is the return from our asyncThunk from above
                state.cities = action.payload;
                state.error = false;
                state.loading = false;
            });
            builder.addCase(getCity.rejected, (state, action)=> {
                state.error = true;
                state.loading = false;
            });
            // builder.addCase(createCity.pending, (state, action)=> {
            //     state.loading = true;
            // });
            builder.addCase(createCity.fulfilled, (state, action) => {
                //The payload in this case, is the return from our asyncThunk from above
                state.city = action.payload;
                state.error = false;
                state.loading = false;
                state.cities = undefined;
            });
            builder.addCase(createCity.rejected, (state, action)=> {
                state.error = true;
                state.loading = false;
            });

    }
});

   export const {toggleError, clearCities} = CitySlice.actions;

export default CitySlice.reducer;