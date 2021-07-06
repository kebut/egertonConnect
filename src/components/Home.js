import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import conn from "../images/conn.png"
import Model from '../Model';
import Services from './Services';
import About from './About';
import Hubs from './Hubs';
import Patnership from './Patnership';
import Contact from './Contact';


function Home() {
  // const [model, setModel] = useState(false);

  // const getData = () => {
  //   return setModel(true);
  // }
  return (
    <>
      <section className="py-2 my-1 home bg">
        <div className="overlay">
          <div className="container-fluid">
            <div className="row">
              <div className="col-sm-6 text-center">
                <figure>
                  <img src={conn} alt="" className="img-fluid in" />
                </figure>
              </div>
              <div className="col-sm-6 intro">
                <h1 className="cu text-center">Egerton Connect Club</h1>
                <p className="cu text-center">Achieve progressive growth through team work and efficient systems. </p>
                <div className="cu text-center">
                  <Link to="/register" type="button" className="  btn bg-info my-2"
                    // onClick={getData}
                  >
                    Click To Join Us Today
                  </Link>
                  <h3 className="blue"> Reach Us Via </h3>
                  <h5>Email: egertonconnect8@gmail.com</h5>
                  <h5>Phone: 07053712 </h5>
                  <h5>Phone: 0704744808</h5>
                  <h5>Phone: 0743089484</h5>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
         <About />
          <Hubs />
          <Services />
          <Patnership />
          <Contact />
    
    </>
  )
}

export default Home
