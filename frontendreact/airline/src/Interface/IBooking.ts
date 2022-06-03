import { IUser} from "./IUser";


export interface IBooking {
    bookingId?: number,
    date: String,
    price: number,
    destination: number,
    origin: number,
    userId?: number

}