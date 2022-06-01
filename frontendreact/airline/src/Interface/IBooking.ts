import { IUser} from "./IUser";


export interface IBooking {
    bookingId: number,
    date: Date,
    price: number,
    destination_city: number,
    origin_city: number

}