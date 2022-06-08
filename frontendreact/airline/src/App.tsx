import React from 'react';

import './App.css';
import { LoginPage } from './Views/LoginPage/LoginPage';
import {BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import {InfoPage} from './Views/ProfilePage/ProfilePage';
<<<<<<< HEAD
import { UserCreate } from './Views/UserCreate/Usercreate';
=======
import { UserCreate } from './Components/UserCreate/UserCreate';
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
import {SearchPage} from './Views/SearchPage/SearchPage';



function App() {
  return (
    <BrowserRouter>
    <Routes>
    <Route path="*" element={<Navigate to="/search" replace />} />
    <Route path="/login" element={<LoginPage />}/>
    <Route path="/info" element={<InfoPage />}/>
    <Route path="/create" element={<UserCreate />}/>
    <Route path="/search" element={<SearchPage />}/>
<<<<<<< HEAD
    
=======
>>>>>>> a514cc80d03cb6380c08cbf0a9621a096ab270f6
    </Routes>
    </BrowserRouter>
   // stuff

  );
}

export default App;