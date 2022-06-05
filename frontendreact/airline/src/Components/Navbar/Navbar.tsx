import React from 'react';

import {Link} from 'react-router-dom';

import { useSelector, useDispatch } from 'react-redux';
import { logout } from '../../Slices/UserSlice';
import { AppDispatch } from '../../Store';


//import defaultImage from '../../deafultpic.jpg';

import './Navbar.css';
import { RootState } from '../../Store';
import { clearCities } from '../../Slices/CitySlice';
import { clearBooking } from '../../Slices/BookSlice';

export const Navbar: React.FC = () => {

    const dispatch:AppDispatch = useDispatch();

    const handleLogout = () => {
        dispatch(logout());
        dispatch(clearCities());
        dispatch(clearBooking());
        
    }

    const user = useSelector((state:RootState) => state.user.user);

    return(
        
        <nav className="navbar">
            
            <ul className='nav-menu'>
                <li className="nav-item">
                    <Link to={`/info`} className="nav-link">Profile</Link>
                </li>
                <li className="nav-item">
                    <Link to={"/search"} className="nav-link">Search</Link>
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