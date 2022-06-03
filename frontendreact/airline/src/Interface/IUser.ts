import {IBooking} from "./IBooking";


export interface IUser {
    userId: number,
    email: string,
    password: string,
    points: number,
    role: boolean,
    firstName: string,
    lastName: string,
    ccn: number,
    sick: boolean,
    ppn: number,
    bookingList?: IBooking []
}