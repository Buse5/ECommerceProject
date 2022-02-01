import {Books} from './Books';

export class BooksInOrders {
    bookInOrderId: string;
    bookName: string;
    bookPrice: number;
    bookStock: number;
    bookDescription: string;
    bookIcon: string;
    bookCategoryType: number;
    count: number;

    constructor(books: Books, quantity = 1){
        this.bookInOrderId = books.bookId;
        this.bookName = books.bookName;
        this.bookPrice = books.bookPrice;
        this.bookStock = books.bookStock;
        this.bookDescription = books.bookDescription;
        this.bookIcon = books.bookIcon;
        this.bookCategoryType = books.categoryType;
        this.count = quantity;
    }
}