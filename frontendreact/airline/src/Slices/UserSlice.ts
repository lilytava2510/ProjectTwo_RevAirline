import {createSlice, createAsyncThunk} from "@reduxjs/toolkit";
import axios from "axios";
import {IUser} from "../Interface/IUser";

//Figure out our default state for this slice

interface UserSliceState {
    loading: boolean,
    error: boolean,
    user?: IUser,
    currentProfile?: IUser,
    passenger?: IUser[]
}

const initialUserState: UserSliceState = {
    loading: false,
    error: false
}

type Login = {
    email: string,
    password: string

}
type info = {
    userId: number,
    email: string,
    password: string,
    points: any,
    role: any,
    firstName: string,
    lastName: string,
    ccn: any,
    sick: boolean,
    ppn: any,
   
}

    type change = {
        userId:any,
        email: any,
        password: any,
        points: any,
        role: any,
        firstName: any,
        lastName: any,
        ccn: any,
        sick: any,
        ppn: any,
       

    }

export const loginUser = createAsyncThunk(
    'user/login',
    async (credentials:Login, thunkAPI) => {
        try{
            const res = await axios.post('http://localhost:8000/user/login', credentials);
            console.log("loginrequest");
            console.log(res);
            return {
                userId: res.data.userId,
                email: res.data.email,
                password: res.data.password,
                points: res.data.points,
                role: res.data.role,
                firstName: res.data.firstName,
                lastName: res.data.lastName,
                ccn: res.data.ccn,
                sick: res.data.sick,
                ppn: res.data.ppn
            }
        } catch(e){
            return thunkAPI.rejectWithValue('wrong');
        }
    }
)

export const getUserInfo = createAsyncThunk(
    "user/info",
    async (userId:number, thunkAPI) => {
        try{
              axios.defaults.withCredentials = true;
            const res = await axios.get(`http://localhost:8080/users/info${userId}`);
  
            return res.data;
        } catch (e){
            console.log(e);
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
);
export const updateUser = createAsyncThunk(
    "user/update",
    async (change:change, thunkAPI) => {
        try{
              //axios.defaults.withCredentials = true;
              console.log(change);
            const res = await axios.put(`http://localhost:8000/user/update`, change);
              
            return res.data;
        } catch (e){
            console.log(e);
        }
    }  
  );




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
            state.user = action.payload;
            state.error = false;
            state.loading = false;
        });
        builder.addCase(loginUser.rejected, (state, action)=> {
            state.error = true;
            state.loading = false;
        });
        builder.addCase(updateUser.pending, (state, action) => {
            state.loading = true;
        });
        builder.addCase(updateUser.fulfilled, (state, action) => {
            state.user = action.payload;
            state.error = false;
            state.loading = false;
                });

        builder.addCase(updateUser.rejected, (state, action) => {
                state.error = true;
                state.loading = false;
                });

}
})

   export const {toggleError} = UserSlice.actions;

export default UserSlice.reducer;