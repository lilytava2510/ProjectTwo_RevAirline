import React from 'react';

import {Link} from 'react-router-dom';

import { useSelector, useDispatch } from 'react-redux';
import { logout } from '../../Slices/UserSlice';
import { AppDispatch } from '../../Store';


//import defaultImage from '../../deafultpic.jpg';

import './Navbar.css';
import { RootState } from '../../Store';

export const Navbar: React.FC = () => {

    const dispatch:AppDispatch = useDispatch();

    const handleLogout = () => {
        dispatch(logout());
        
    }

    const user = useSelector((state:RootState) => state.user.user);

    return(
        
        <nav className="navbar">
            
            <ul className='nav-menu'>
                <li className="nav-item">
                    <Link to={`/user/${user?.userId}`} className="nav-link">Profile</Link>
                </li>
                <li className="nav-item">
                    <Link to={"/home"} className="nav-link">Home</Link>
                </li>
                <li className="nav-item">
<<<<<<< HEAD
                    <Link to={"/booking"} className="nav-link">Book</Link>
=======
                    <Link to={"/bookingPage"} className="nav-link">Book</Link>
>>>>>>> master
                </li>
               
                <li className="nav-item">
                    <Link to={"/login"} className="nav-link">Login</Link>
                </li>
                
                <li className="logout">
                    <Link to={"/login"} className="nav-link">
                        <button className="logout-btn" onClick={handleLogout}>Logout</button>
                    </Link>
                </li>
            </ul>
        </nav>
    )

}