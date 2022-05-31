import React from "react";

import {useSelector} from 'react-redux';
import { RootState } from "../../Store";

export const SearchPage: React.FC = () => {
    
    const available = useSelector((state:RootState)=>state.book);

    return(
        <>
          <h1>Welcome to Revature Airline</h1>
          <h2>Below are the flights available</h2>
        </>
    )
}
