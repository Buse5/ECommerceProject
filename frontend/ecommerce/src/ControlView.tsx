import React from 'react';
import { Navigate } from 'react-router-dom';
import NavMenu from './components/NavMenu';
import { control } from './Util';

interface IControl {
    view: JSX.Element
}

export default function ControlView( props: IControl ) {
  return (
    <>
        { control() ? <> <NavMenu /> {props.view} </> : <Navigate to='/' /> }
    </>
  )
}