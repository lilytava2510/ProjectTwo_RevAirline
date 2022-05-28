import {IBooking} from "./IBooking";


export interface IUser {
    userId: number,
    email: string,
    password: string,
    points: number,
    role: number,
    first_name: string,
    last_name: string,
    creditcard_number: number,
    vaccination_status: boolean,
    passport_number: number
}