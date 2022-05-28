import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";
import {IUser} from "../Interface/IUser";

//Figure out our default state for this slice

interface UserSliceState {
    loading: boolean,
    error: boolean,
    user?: IUser,
    currentProfile?: IUser
}

const initialUserState: UserSliceState = {
    loading: false,
    error: false
}

type Login = {
    email: string,
    password: string
}

export const loginUser = createAsyncThunk(
    'user/login',
    async (credentials:Login, thunkAPI) => {
        try{
            const res = await axios.post('http://localhost:8000/user/login', credentials);

            return {
                userId: res.data.userId,
                email: res.data.email,
                first_name: res.data.first_name,


            }
        } catch(e){
            return thunkAPI.rejectWithValue('something went wrong');
        }
    }
)

export const getUserDetails = createAsyncThunk(
    'users/get',
    async (id: number | string, thunkAPI) => {
        try{
            const res = await axios.get(`http://localhost:8000/users/full/${id}`);

            return {
                userId: res.data.userId,
                first_name: res.data.first_name,
                last_name: res.data.last_name,
                email: res.data.email,
            }
        } catch(error){
            console.log(error);
        }
    }
);

export const logout = createAsyncThunk(
    "user/logout",
    async (thunkAPI) => {
        try{
            axios.defaults.withCredentials = true;
            const res = axios.get("http://localhost:8000/users/logout");
        } catch(e){
            console.log(e);
        }
    }
)

//Create the slice
export const UserSlice = createSlice({
    name: "user",
    initialState: initialUserState,
    reducers: {
        toggleError : (state) => {
            state.error = !state.error;
        }
    },
    extraReducers: (builder) => {
    
        builder.addCase(loginUser.pending, (state, action)=> {
            state.loading = true;
        });
        builder.addCase(loginUser.fulfilled, (state, action) => {
            //state.user = action.payload;
            state.error = false;
            state.loading = false;
        });
        builder.addCase(loginUser.rejected, (state, action)=> {
            state.error = true;
            state.loading = false;
        });

}
})

   export const {toggleError} = UserSlice.actions;

export default UserSlice.reducer;