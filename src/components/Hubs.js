import React from 'react';
import data from '../data';
// import gis from "../images/gis.jpg";
// import social1 from "../images/social1.jpg";
// import art from "../images/art.jpg";
// import code from "../images/code.jpg";
// import research from "../images/conn.jpg";
// import fx1 from "../images/fx1.jpg";
// import business from "../images/business.jpg";


function Hubs() {
    return (
        <div className="mt-3 hubs bg" >
            <div className="container">
                <h1 className="text-center blue underline ">Our Hubs</h1>
                <div className="row mt-4">
                {data.hubs.map((item, index) => {
                        return (
                            <div className="col-12 col-6 3 col-sm-4 mx-0 mb-4 " key={index}>
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

                    {/* <div className="col-sm-6 text-center">
                        <figure>
                            <img src={gis} alt="" className="img-fluid" />
                        </figure>
                        <figcaption>
                            <h3 className="red">GIS HUB</h3>
                            <p>AIM : Understand the various mapping techniques that are used in the 21st century and how you can incorporate the skills in real world.</p>
                        </figcaption>
                    </div>
                    <div className="col-sm-6 text-center">
                        <figure>
                            <img src={social1} alt="" className="img-fluid" />
                        </figure>
                        <figcaption>
                            <h3 className="red">SOCIAL MEDIA HUB</h3>
                            <p>AIM : Grasp and learn the art of marketing, promotion and creativity through social media platforms and apparels.</p>
                        </figcaption>
                    </div>
                    <div className="col-sm-6 text-center">
                        <figure>
                            <img src={art} alt="" className="img-fluid" />
                        </figure>
                        <figcaption>
                            <h3 className="red">ART HUB</h3>
                            <p>AIM : Appreciate the work of art including photography, drawing, engineering and new emerging designs in fashion.</p>
                        </figcaption>
                    </div>
                    <div className="col-sm-6 text-center">
                        <figure>
                            <img src={code} alt="" className="img-fluid" />
                        </figure>
                        <figcaption>
                            <h3 className="red">CODE HUB</h3>
                            <p>AIM : learn the various types of programming language, different operating systems and basic data management.</p>
                        </figcaption>
                    </div>
                    <div className="col-sm-6 text-center">
                        <figure>
                            <img src={research} alt="" className="img-fluid" />
                        </figure>
                        <figcaption>
                            <h3 className="red">RESEARCH HUB</h3>
                            <p>AIM :Learn on various tpyes of research and engage on research activities in various subjects.</p>
                        </figcaption>
                    </div>
                    <div className="col-sm-6 text-center">
                        <figure>
                            <img src={fx1} alt="" className="img-fluid" />
                        </figure>
                        <figcaption>
                            <h3 className="">FOREX HUB</h3>
                            <p>AIM :  Understand the various styles of trading and the different ways of investing.</p>
                        </figcaption>
                    </div>
                    <div className="col-sm-6 text-center">
                        <figure>
                            <img src={business} alt="" className="img-fluid" />
                        </figure>
                        <figcaption>
                            <h3 className="red">BIZ HUB</h3>
                            <p>AIM : Get proficient knowledge in business setups and online business opportunities.</p>
                        </figcaption>
                    </div>*/}
                </div> 
            </div>
        </div>
    )
}

export default Hubs
