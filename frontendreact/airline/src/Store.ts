import { configureStore } from "@reduxjs/toolkit";

import userReducer from "./Slices/UserSlice"
import bookReducer from "./Slices/BookSlice"

export const store = configureStore({
    reducer: {
      user: userReducer,
      book: bookReducer

    }
});


export type RootState = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;