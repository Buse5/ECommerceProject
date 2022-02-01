import axios from 'axios';
import React from 'react';

const config = axios.create({
    baseURL:'https://www.jsonbulut.com/json/',
    timeout: 15000
})


// All Product
export const allProduct = ( start: number ) => {
    const params = {
        ref: 'c7c2de28d81d3da4a386fc8444d574f2',
        start: start
    }
    return config.get("product.php", { params: params })
}