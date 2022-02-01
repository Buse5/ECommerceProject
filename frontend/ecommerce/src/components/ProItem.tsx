import React, { useState } from 'react';
import { ProBilgiler } from '../models/IProduct';

interface propsModel {
    item: ProBilgiler
}

export default function ProItem( props:propsModel ) {

    const [isModal, setIsModal] = useState(false);

     

  return <>

    { isModal === true &&  
        <div className="modal fade" id={'proModal'+props.item.productId} tabIndex={-1} aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div className="modal-dialog">
                <div className="modal-content">
                <div className="modal-header">
                    <h5 className="modal-title" id="exampleModalLabel"> { props.item.productName } </h5>
                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div className="modal-body">
                    <img src={props.item.images[0].normal} className="img-fluid" />

                </div>
                <div className="modal-footer">
                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" className="btn btn-primary">Save changes</button>
                </div>
                </div>
            </div>
        </div>
    }


    <div className="card col-sm-4">
        <img src={props.item.images[0].normal} className="card-img-top" style={{ height:260, }} />
        <div className="card-body">
            <h5 className="card-title"> { props.item.productName } </h5>
            <p className="card-text"> { props.item.brief } </p>
            <a onClick={(e) => { setIsModal(true); } } href="#" className="btn btn-primary" data-bs-toggle="modal" data-bs-target={'#proModal'+props.item.productId}>Detail</a>
        </div>
    </div>
  </>;
}