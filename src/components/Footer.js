import React from 'react'
import {Link} from "react-router-dom"
function Footer() {
    return (
        <footer id="mainfooter" className="py-2">
            <div className="container">
                <div className="col hov">
                    <h5>Useful Links</h5>
                    <ul>

                        <li>
                            <a href="" className="cu" target="blank">Egerton connect portal</a>

                        </li>
                        <li>
                            <Link to="/services" className="cu">Our Core Programs</Link>
                        </li>
                        <li>
                            <a href="mailto:egertonconnect8@gmail.com" className="cu">egertonconnect8@gmail.com</a>
                        </li>
                    </ul>
                    <div className="text-center">
                        <p className="cu">Egerton Connect Club &copy; <span id="year"></span> Run and Designed By <a href="https://theonlineclinic.co.ke" className="cu toc h5" target="blank">The Online Clinic</a></p>
                    </div>
                </div>
            </div>
        </footer>
    );
}

export default Footer
