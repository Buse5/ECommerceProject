import React from 'react';
import { Bilgiler } from './models/IUserLogin';

export const control = () => {

    // remember control
    const rememberSt = localStorage.getItem("user")
    if ( rememberSt ) {
        sessionStorage.setItem("user", rememberSt)
    }

    const st = sessionStorage.getItem("user")
    if ( !st ) {
        return false
    }

    try {
        const stData = b64_to_utf8(b64_to_utf8(b64_to_utf8(st)))
        const bilgi: Bilgiler = JSON.parse(stData)
        // bilgi.userId // service control yapılmalı.
        return bilgi
    } catch (error) {
        return false
    }
    
}


 export function utf8_to_b64( str: string ) {
    return window.btoa(unescape(encodeURIComponent( str )));
  }
  
 export function b64_to_utf8( str: string ) {
    return decodeURIComponent(escape(window.atob( str )));
  }