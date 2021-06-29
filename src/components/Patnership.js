import React from 'react'
import toc from "../images/envt.jpg"

function Patnership() {
    return (
        <div className="mt-3 patnership bg">
            <div className="container">
                <h1 className="text-center blue">Patnership</h1>
                <div className="row">
                    <div className="col col-sm-12">
                        <h2 className="red">Partner with us today</h2>
                        <p>Closely we relate with students, alumni, professional clubs, associations, government, institutions, unions and societies of similar interests to promote mentorship.</p>
                        <div>
                            <button className="btn btn-info">
                                Patner Today call : 0705371205/0743089484
                            </button>
                            <p>or</p>
                            <h3 className="btn btn-info mt-2">Email us : egertonconnect8@gmail.com</h3>

                        </div>
                        <h3 className="red text-center mb-2">Our Partners</h3>
                        <div className="row">
                            <div className="col-sm-4">
                                <figure>
                                    <img src={toc} alt="" className="img-fluid" />
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Patnership
