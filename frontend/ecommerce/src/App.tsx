import React, { useEffect, useRef, useState } from 'react';
import axios from 'axios';
import { ToastContainer, toast } from 'react-toastify';
import { Navigate, NavLink } from 'react-router-dom';
import { control, utf8_to_b64 } from './Util';

function App() {


  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [remember, setRemember] = useState(false);





  return (
    <>
      <ToastContainer />
      <div className='row'>
        <div className='col-sm-4'></div>
        <div className='col-sm-4'>
          <h1>User Login</h1>
          <form  >
            <div className="mb-3">
              <label htmlFor="exampleInputEmail1" className="form-label">Email address</label>
              <input  onChange={(e) => setEmail(e.target.value)} type="email" className="form-control" id="exampleInputEmail1"  />
            </div>
            <div className="mb-3">
              <label htmlFor="exampleInputPassword1" className="form-label">Password</label>
              <input  onChange={(e) => setPassword(e.target.value)} type="password" className="form-control" id="exampleInputPassword1"  />
            </div>
            <div className="mb-3 form-check">
              <input onClick={(e) => setRemember( !remember )  } type="checkbox" className="form-check-input" id="exampleCheck1" />
              <label className="form-check-label" htmlFor="exampleCheck1">Remember Me</label>
            </div>
            
              <button type="submit" className="btn btn-primary">Login</button>
              <NavLink to="/register" className="btn btn-success" style={{ float:'right' }}>Register</NavLink>
            
          </form>
        </div>
        <div className='col-sm-4'></div>
      </div>
    </>
  );
}

export default App;
