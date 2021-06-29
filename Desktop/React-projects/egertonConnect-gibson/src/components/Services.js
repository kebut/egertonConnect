import React from 'react'
import data from '../data';
// import re from "../images/re.jpg"
// import  fx  from "../images/fx.jpg"
// import networking from "../images/networking.jpg"
// import free from "../images/free.jpg"
// import  char  from "../images/char.jpg"
// import  tailor  from "../images/tailor made 4.jpg"
// import web from "../images/web.jpg"
// import fut from "../images/fut.jpg"
// import envt from "../images/envt.jpg"


function Services() {
    return (
        <div className="py-2 my-1 services bg">
            <div className="container">
                <h1 className="text-center blue">What We Do</h1>
                <h2 className="text-center red">Our Core Programs</h2>
                <div className="row justify-content-center align-item-center">
                {data.services.map((item, index) => {
                        return (
                            <div className="col-12 col-6 3 col-sm-4 mx-0 mb-4" key={index}>
                                <div className="card p-0 overflow-hidden h-100 shadow">
                                    <img src={item.img} class="card-img-top im" alt={item.topic} />
                                    <div className="card-body">
                                        <h3 className="card-title red my-2">{item.topic}</h3>
                                        <p className="card-text">{item.content}</p>
                                        
                                    </div>
                                </div>
                            </div>
                        );
                        
                    })}
                    {/* <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={re} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Environmental Conservation and Recreation</h5>
                                <p>Having being hosted by the environment for centuries, it's high time we as humans host the environment for the good it has done in supporting our lives. Help us gift nature by planting a tree and engaging in environmental conservation activities.</p>
                            </figcaption>
                        </figure>
                    </div>
                    <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={fx} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Forex,Stocks and Commodity Trading</h5>
                                <p>Speaking of <span class="blue font-weight-bold">"money"</span> behind charts and the people who ware navy-blue suits. Come learn the various types of trading, investing and scalping strategies. We focus on both international and national stock exchange markets.</p>
                            </figcaption>
                        </figure>
                    </div>
                    <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={networking} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Networking</h5>
                                <p>Networking is the exchange of information and ideas among people with a common profession or special interest, usually in an informal social setting. Networking often begins with a single point of common ground. Tag along let's expand our circles.</p>
                            </figcaption>
                        </figure>
                    </div>
                    <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={web} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Web app Development</h5>
                                <p>Expressing your thoughts, dreams, business setups, art and talent in an online way can be very interesting and fun. Imagine all those pictures, stories and memories put in lines of codes <span className="blue">"Talking the computer language".</span></p>
                            </figcaption>
                        </figure>
                    </div>
                    <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={free} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Transcription and Online Writing</h5>
                                <p>Online work is a source of employment that allows one to work from anywhere, at any given time and for whomever as long as one has a computer or a smart device, and an internet connection. Online work offers a level of freedom and flexibility not usually found in the traditional workplace where one has to work from 8 am to 5 pm.</p>
                            </figcaption>
                        </figure>
                    </div>
                    <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={envt} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Environmental Conservation and Recreation</h5>
                                <p>Having being hosted by the environment for centuries, it's high time we as humans host the environment for the good it has done in supporting our lives. Help us gift nature by planting a tree and engaging in environmental conservation activities.</p>
                            </figcaption>
                        </figure>
                    </div>
                    <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={tailor} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Domestic tourism</h5>
                                <p>Nature is full of wonders and breathe-taking scenaries, it is artistic and will never seize to amaze. We undertake domestic tours as a way of appreciating and enjoying this unending beauty. Join us, we want to add that smile to that group photo or selfie. </p>
                            </figcaption>
                        </figure>
                    </div>
                    <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={fut} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Mentorship and skill development</h5>
                                <p>Self awareness is the part of understanding yourself. It involves knowing and appreciating the changes in your body and psychological mark-up as you get older. Tag along and learn these vital skills: <span class="red">communication skills, organizational skills, problem-solving skills, leadership skills, collaboration, teamwork, negotiation skills etiquette and netiquette</span></p>
                            </figcaption>
                        </figure>
                    </div>
                    <div className="col-12 col-sm-4 text-center">
                        <figure>
                            <img src={char} alt="" className="img-fluid" />
                            <figcaption>
                                <h5 className="red my-2">Charity and Community Welfare</h5>
                                <p>We as the humans are social beings, it is our default mode and we cannot fail to recognize this major role. Thus giving back to the society and participating in activites that add to the well-being of a community is very important. Charity with us as we embark on this crucial role. </p>
                            </figcaption>
                        </figure>
                    </div> */}
                  
                </div>
            </div>
        </div>
    )
}

export default Services
