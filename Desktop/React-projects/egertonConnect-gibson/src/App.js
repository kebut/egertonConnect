import React, { useState, useEffect } from 'react'
import { BrowserRouter as Router, Switch, Route, Link } from 'react-router-dom';
import CookieConsent from "react-cookie-consent";
import  './App.css';
import Navbar from './components/Navbar'
import Footer from './components/Footer';
import Services from './components/Services';
import About from './components/About';
import Hubs from './components/Hubs';
import Patnership from './components/Patnership';
import Contact from './components/Contact';
import Home from './components/Home';
import Membership from './components/Membership';
import Register from "./components/Register"
import LandingPage from './components/LandingPage';
function App() {
  const [loading, setLoading] = useState(false);
    

  useEffect(() => {
    setTimeout(() => {
      setLoading(true);
    }, 1000)
  }, []);
  return (
    <>
      {!loading ? (
        

        <div className="spinner">
              
              <div className="half-spinner"></div>
            </div>

      ) : (
          
          <Router>
            <Navbar />
            {/* <LandingPage /> */}
            <Switch>
              <Route path="/" exact>
                <Home/>
              </Route>
              <Route path="/home" exact>
                <Home/>
                </Route>
              <Route path="/about">
                <About/>
                </Route>
              <Route path="/services">
                <Services/>
              </Route>
              <Route path="/patnership">
                <Patnership/>
              </Route>
              <Route path="/hubs">
                <Hubs/>
              </Route>
              <Route path="/contact">
                <Contact/>
              </Route>
              <Route path="/membership">
                <Membership/>
              </Route>
              <Route path="/register">
                <Register/>
              </Route>
            </Switch>
            <CookieConsent
              // debug={true}
              location="bottom"
              style={{
                backgroundColor: 'rgba(0, 1, 1, 0.9',
               
              }
              }
              buttonStyle={{ backgroundColor: "#2ec4b6", borderRadius: "2rem",}}
              declineButtonStyle={{backgroundColor: "#ff3366", borderRadius: "2rem"}}
              enableDeclineButton
              expires={90}
              declineButtonText="I Decline"
              buttonText=" I Understand"
            >We use cookies to collect and analyse information on our site's performance and to enable the site to function.  </CookieConsent>
            <Footer />
          </Router>
      )}
    </>
  );
}

export default App


