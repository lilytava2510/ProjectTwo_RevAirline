import React from 'react';

import './App.css';
import { LoginPage } from './Views/LoginPage/LoginPage';
import {BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import {InfoPage} from './Views/ProfilePage/ProfilePage';
import { UserCreate } from './Components/UserCreate/UserCreate';
//import {SearchPage} from './Views/SearchPage/SearchPage';


function App() {
  return (
    <BrowserRouter>
    <Routes>
    <Route path="*" element={<Navigate to="/login" replace />} />
    <Route path="/login" element={<LoginPage />}/>
    <Route path="/info" element={<InfoPage />}/>
    <Route path="/create" element={<UserCreate />}/>
    </Routes>
    </BrowserRouter>
   // stuff

  );
}

export default App;


