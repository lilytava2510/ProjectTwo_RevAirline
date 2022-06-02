import React from 'react';

import './App.css';
import { LoginPage } from './Views/LoginPage/LoginPage';
import {BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import {SearchPage} from './Views/SearchPage/SearchPage';


function App() {
  return (
    <BrowserRouter>
    <Routes>
    <Route path="*" element={<Navigate to="/login" replace />} />
    <Route path="/login" element={<LoginPage />}/>
    <Route path="/search" element={<SearchPage/>}/> {/* create booking; */}
    {/* <Route path="/profile" element={<ProfilePage/>}/> */} {/* account - create, update, ; */}
    {/* <Route path="/pay" element={<PaymentPage/>}/> */}
    </Routes>
    </BrowserRouter>
   
  
  );
}

export default App;
