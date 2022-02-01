import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Homepage from './Homepage'
import Register from './Register'

const router=
<Router>
  <Routes>
    <Route path="" element={<App/>}/>
    <Route path="/homepage" element={<Homepage/>}/>
    <Route path="/register" element={<Register/>}/>
  </Routes>
</Router>

ReactDOM.render(router, document.getElementById('root')
);
reportWebVitals();
