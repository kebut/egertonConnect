import React from 'react'
import Navbar from './Navbar'
import Footer from './Footer';
import Services from './Services';
import About from './About';
import Hubs from './Hubs';
import Patnership from './Patnership';
import Contact from './Contact';
import Home from './Home';

function LandingPage() {
    return (
        <div>
              
          <Home />
          <About />
          <Hubs />
          <Services />
          <Patnership />
          <Contact />
          
        </div>
    )
}

export default LandingPage
