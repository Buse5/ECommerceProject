import {BooksInOrders} from './BooksInOrdes';

export class Books {
    bookId: string;
    bookName: string;
    bookPrice: number;
    bookStock: number;
    bookDescription: string;
    bookIcon: string;
    bookStatus: number; // 0: onsale 1: offsale
    categoryType: number;
    createTime: string;
    updateTime: string;


    constructor(booksInOrders?: BooksInOrders) {
        if (booksInOrders) {
            this.bookId = booksInOrders.bookInOrderId;
            this.bookName = booksInOrders.bookName;
            this.bookPrice = booksInOrders.bookPrice;
            this.bookStock = booksInOrders.bookStock;
            this.bookDescription = booksInOrders.bookDescription;
            this.bookIcon = booksInOrders.bookIcon;
            this.categoryType = booksInOrders.bookCategoryType;
            this.bookStatus = 0;
        } else {
            this.bookId = '';
            this.bookName = '';
            this.bookPrice = 20;
            this.bookStock = 100;
            this.bookDescription = '';
            this.bookIcon = '';
            this.categoryType = 0;
            this.bookStatus = 0;
        }
    }
}