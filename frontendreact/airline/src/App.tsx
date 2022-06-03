import React from 'react';

import './App.css';
import { LoginPage } from './Views/LoginPage/LoginPage';

import {BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import {InfoPage} from './Views/ProfilePage/ProfilePage';
<<<<<<< HEAD
=======
import { UserCreate } from './Components/UserCreate/UserCreate';
>>>>>>> 0c43c131695e5e3ed6c3a0d861944606a86bafe2
//import {SearchPage} from './Views/SearchPage/SearchPage';
import { UserCreate } from './Components/UserCreate/UserCreate';


function App() {
  return (
    <BrowserRouter>
    <Routes>
    <Route path="*" element={<Navigate to="/login" replace />} />
    <Route path="/login" element={<LoginPage />}/>
<<<<<<< HEAD
    

=======
    <Route path="/info" element={<InfoPage />}/>
    <Route path="/create" element={<UserCreate />}/>
<<<<<<< HEAD
    
    
=======
>>>>>>> 0c43c131695e5e3ed6c3a0d861944606a86bafe2
>>>>>>> master
    </Routes>
    </BrowserRouter>
   // stuff

  );
}

export default App;


