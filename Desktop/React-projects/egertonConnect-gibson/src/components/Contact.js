import React from 'react'


function Contacts() {
    return (
        <div className="mt-3 contacts bg">
            <div className="container">
                <h1 className="text-center blue">Contact Us</h1>
                <div className="row">
                    <div className="col col-sm-12">

                        <h5 className="red">Headquarters</h5>
                        <p>Egerton University, Njoro, Nakuru</p>
                        <h2 className="red">Reach us via</h2>
                        <ul>
                            <li>Phone 1:<a href="tel:  0793 715 710" class="blue" type="tel"> 0705371205</a></li>
                            <li>Phone 2: <a href="tel:  0793 715 710" class="blue" type="tel"> 0797416828</a></li>
                            <li>Phone 3: <a href="tel:  0793 715 710" class="blue" type="tel"> 0743089484</a></li>
                            <li>Email: <a href="mailto:egertonconnect8@gmail.com" class="blue">egertonconnect8@gmail.com</a></li>
                            <li> Facebook: <a href="https://www.facebook.com/Egerton-Connect-Club-111614930970841/" target="blank" class="blue"> <span class="fab fa-facebook te p-1 text-primary "></span>Egerton Connect</a>
                            </li>
                            <li>WhatsApp: <a href="https://wa.me/2540705371205" class="blue"> <span class="fab fa-whatsapp te text-success p-1"></span>WhatsApp</a> </li>
                            <li>Instagram: <a href="https://www.instagram.com/egertonconnectclub/" target="blank" class="blue"> <span class="fab fa-instagram te text-danger p-1"></span>Egerton Connect</a></li>
 
                        </ul>
  
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Contacts
