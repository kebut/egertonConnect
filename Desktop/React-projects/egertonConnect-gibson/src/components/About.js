import React from 'react'
import conn from "../images/conn.jpg"

function About() {
    return (
        <div className="py-2 my-1 about bg">
            <div className="container">
                <div className="row">
                    <div className="col col-sm-12">
                        <h1 className="text-center blue">Who are We</h1>
                        <p>We are a welfare group and pride ourselves in having a peaceful working, reading and recreational environment. Our aim is to enable students acquire opportunities, networks, skills, while providing a support system to members. It was established in 2019, and located in Egerton University Njoro campus.</p>
                        <figure className="text-center">
                            <img src={conn} alt="logo" className="img-fluid we" />
                        </figure>
                        <h1 className="blue">Vision</h1>
                        <p>Achieve progressive growth through team work and efficient systems.</p>
                        <h1 className="blue">Mission</h1>
                        <p>To become a key player in promoting cultural, environmental and economic welfare while providing fair services.</p>
                        <h1 className="blue">Motto</h1>
                        <p>Embracing Nature</p>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default About
