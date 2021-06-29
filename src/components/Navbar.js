
import React, {useState} from 'react'
import {Link} from "react-router-dom"

function Navbar() {
    const [isNavCollapsed, setIsNavCollapsed] = useState(true);

    const handleNavCollapse = () => setIsNavCollapsed(!isNavCollapsed);
    return (
        <div>
            <nav className="navbar navbar-expand-md  sticky-top" id="mainNav">
                <div className="container">
                    <Link to="/" className="navbar-brand" />
      
                    <h3 className="d-inline cu">Egerton Connect</h3>
    
                    {/* <button className="navbar-toggler ml-2 my-3" data-toggle="collapse" data-target="#navbarCollapse">
                        <span className=" fas fa-bars"></span>
                    </button> */}
                    <button class="custom-toggler navbar-toggler"
                        type="button"
                        data-toggle="collapse"
                        data-target="#navbarsExample09"
                        aria-controls="navbarsExample09"
                        aria-expanded={!isNavCollapsed ? true : false}
                        aria-label="Toggle navigation"
                        onClick={handleNavCollapse}>
                        <i className="fas fa-bars text-white"></i>
                    </button>
                    {/* <div className="collapse navbar-collapse navbar-collapse-right" id="navbarCollapse"> */}
                    <div class={`${isNavCollapsed ? 'collapse' : ''} navbar-collapse`} id="navbarsExample09">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link to="/" className="nav-link cu">Home</Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/about" className="nav-link cu">About Us</Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/services" className="nav-link cu">Services</Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/membership" className="nav-link cu">Membership</Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/patnership" className="nav-link cu">Partnership</Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/hubs" className="nav-link cu">Our Hubs</Link>
                            </li>
                            <li className="nav-item">
                                <Link to="/contact" className="nav-link cu">Contacts</Link>
                            </li>
                        </ul>
      
                    </div>
                </div>

            </nav>
        </div>
    );
}

export default Navbar

