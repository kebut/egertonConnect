import React from 'react'

function Membership() {
    return (
        <div className="container membership bg mb-2">
            <h1 className="text-center blue">Join Us Today</h1>
            <div className="row">
                <div className="col col-sm-12">

                    <p>Membership is open to all bona fide students of Egerton University and staff. Registration fees shall be 200 shillings and a renewal fee of 150 shillings per semester. N.B these fees will/can change depending on the club financial status.</p>
                    <div className="">
                        <button type="button" className="btn btn-info" data-toggle="modal" data-target="#join">
                            Click to Join us Now
                        </button>

                    </div>
                    <div className="mem"></div>
                    
                    
                </div>
            </div>
        </div>
    );
}

export default Membership