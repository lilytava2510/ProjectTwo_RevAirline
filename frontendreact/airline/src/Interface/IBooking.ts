import { ICity } from "./ICity";
import { IUser} from "./IUser";


export interface IBooking {
    bookingid?: number,
    date: string,
    price: number,
    destination: ICity,
    origin: ICity,
    userId?: number

}