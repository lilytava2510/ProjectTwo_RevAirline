import React from 'react';

import './App.css';
import { LoginPage } from './Views/LoginPage/LoginPage';

import {BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';


function App() {
  return (
    <BrowserRouter>
    <Routes>
    <Route path="*" element={<Navigate to="/login" replace />} />
    <Route path="/login" element={<LoginPage />}/>
    

    </Routes>
    </BrowserRouter>
   
  
  );
}

export default App;
