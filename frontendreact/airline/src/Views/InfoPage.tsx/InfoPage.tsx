import React from "react";
// import { useSelector, useDispatch} from 'react-redux';
// import { RootState, AppDispatch}  from '../../Store';
// import {useNavigate} from "react-router-dom";
// import { IUser } from "../../Interface/IUser";
// import './InfoPage.css';
// import { UserInfo } from "../../Components/UserInfor/UserInfor";
// import { getUserInfo } from "../../Slices/UserSlice";
// export const InfoPage: React.FC = () => {

//     const userInfo = useSelector((state:RootState) => state.user);
   
//     const dispatch:AppDispatch = useDispatch();
//     const navigator = useNavigate();
//         useEffect(() => {
//         if(!userInfo.user){
//           navigator("/login");

//         }else if(userInfo.user && userInfo.user.role && !userInfo.){
//             dispatch(getUserInfo());
//         }

//     },[userInfo]);
//     console.log(userInfo.user?.role)
//     return(
//         <>

           
//            <h1 className="page"> Welcome {userInfo.user?.firstName}</h1>
//            <h2 className="page"></h2>
//            <table className="info">
//                <tr>
//                    <th>User ID #</th>
//                    <th>First Name</th>
//                    <th>Last Name</th>
//                    <th>Email</th>
//                    <th>Username</th>
              
                   
//                </tr>
//                     {userInfo.passenger?
//                      userInfo.passenger.map((post:IUser)=> {
//                         return <Userinfo {...post} key={post.userId}/>
//                     }): 
//                 }
//          </table>
//         </>
//     )

//     }