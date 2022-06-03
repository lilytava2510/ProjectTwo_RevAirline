import { configureStore } from "@reduxjs/toolkit";
import userReducer from "./Slices/UserSlice";
import bookReducer from "./Slices/BookSlice";
import cityReducer from "./Slices/CitySlice";


export const store = configureStore({
    reducer: {
      user: userReducer,
      book: bookReducer,
      city: cityReducer,
    }
});


export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;