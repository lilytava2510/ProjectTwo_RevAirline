
export const BookingPage: React.FC <IBooking>= (book:IBooking) => {

export const BookingPage: React.FC = () => {
    return(
        <>
        <table>
         <th>

             <td>BookingId</td>
             <td>Date</td>
             <td>Origin</td>
             <td>Destination</td>
             <td>Price</td>
             <td>PassengerId</td>

         </th>
            <tr>
                <td>{book.bookingId}</td>
                <td>{book.date}</td>
                <td>{book.origin}</td>
                <td>{book.destination}</td>
                <td>{book.price}</td>
                <td>{book.userId}</td>
            </tr>
        </table>
           
        </>
    )
}