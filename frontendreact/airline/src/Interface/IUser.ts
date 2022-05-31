import {IBooking} from "./IBooking";


export interface IUser {
    userId: number,
    email: string,
    password: string,
    points: number,
    role: number,
    firstName: string,
    lastName: string,
    creditcard_number: number,
    vaccination_status: boolean,
    passport_number: number,
    booking: IBooking []
}