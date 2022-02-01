import {BooksInOrders} from './BooksInOrdes';


export interface Cart {
    cartId: number;
    books: BooksInOrders[];
}