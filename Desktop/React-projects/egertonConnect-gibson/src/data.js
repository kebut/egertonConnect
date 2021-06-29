import re from "./images/re.jpg";
import fx from "./images/fx.jpg";
import free from "./images/free.jpg";
import web from "./images/web.jpg";
import networking from "./images/networking.jpg";
import char from "./images/char.jpg";
import tailor from "./images/tailor made 4.jpg";
import fut from "./images/fut.jpg";
import envt from "./images/envt.jpg";
import gis from "./images/gis.jpg";
import social1 from "./images/social1.jpg";
import art from "./images/art.jpg";
import code from "./images/code.jpg";
import research from "./images/research.jpg";
import fx1 from "./images/fx1.jpg";
import business from "./images/business.jpg";


const data = {
    cardData: [
        {
            id: 1,
            imgSrc: re,
            title: "title 1",
            desc: "desc1"
        },
        {
            id: 2,
            imgSrc: fx,
            title: "title 2",
            desc: "desc2"
        },
        {
            id: 3,
            imgSrc: free,
            title: "title 3",
            desc: "desc3"
        },
        {
            id: 4,
            imgSrc: web,
            title: "title 4",
            desc: "desc4"
        },

    ],
    services: [
        {
            id: 1,
            img: re,
            topic: "Environmental Conservation and Recreation",
            content: "Having being hosted by the environment for centuries, it's high time we as humans host the environment for the good it has done in supporting our lives. Help us gift nature by planting a tree and engaging in environmental conservation activities."
        },
        {
            id: 2,
            img: fx,
            topic: "Forex,Stocks and Commodity Trading",
            content: "Speaking of 'money' behind charts and the people who wear navy-blue suits. Come learn the various types of trading, investing and scalping strategies. We focus on both international and national stock exchange markets."
        },
        {
            id: 3,
            img: networking,
            topic: "Networking",
            content: "Networking is the exchange of information and ideas among people with a common profession or special interest, usually in an informal social setting. Networking often begins with a single point of common ground. Tag along let's expand our circles."
        },
        {
            id: 4,
            img: web,
            topic: "Web app Development",
            content: "Expressing your thoughts, dreams, business setups, art and talent in an online way can be very interesting and fun. Imagine all those pictures, stories and memories put in lines of codes 'Talking the computer language'."
        },
        {
            id: 5,
            img: free,
            topic: "Transcription and Online Writing",
            content: "Online work is a source of employment that allows one to work from anywhere, at any given time and for whomever as long as one has a computer or a smart device, and an internet connection. Online work offers a level of freedom and flexibility not usually found in the traditional workplace where one has to work from 8 am to 5 pm."
        },
        {
            id: 6,
            img: envt,
            topic: "Environmental Conservation and Recreation",
            content: "Having being hosted by the environment for centuries, it's high time we as humans host the environment for the good it has done in supporting our lives. Help us gift nature by planting a tree and engaging in environmental conservation activities."
        },
        {
            id: 7,
            img: tailor,
            topic: "Domestic tourism",
            content: "Nature is full of wonders and breathe-taking scenaries, it is artistic and will never seize to amaze. We undertake domestic tours as a way of appreciating and enjoying this unending beauty. Join us, we want to add that smile to that group photo or selfie."
        },
        {
            id: 8,
            img: fut,
            topic: "Mentorship and skill development",
            content: "Self awareness is the part of understanding yourself. It involves knowing and appreciating the changes in your body and psychological mark-up as you get older. Tag along and learn these vital skills: communication skills, organizational skills, problem-solving skills, leadership skills, collaboration, teamwork, negotiation skills etiquette and netiquette"
        },
        {
            id: 9,
            img: char,
            topic: "Charity and Community Welfare",
            content: "We as the humans are social beings, it is our default mode and we cannot fail to recognize this major role. Thus giving back to the society and participating in activites that add to the well-being of a community is very important. Charity with us as we embark on this crucial role. "
        },
    ],
    hubs: [
        {
            id: 1,
            img: gis,
            topic: "GIS HUB",
           content: "AIM : Understand the various mapping techniques that are used in the 21st century and how you can incorporate the skills in real world."
        },
        {
            id: 2,
            img: social1,
            topic: "SOCIAL MEDIA HUB",
           content: "AIM : Grasp and learn the art of marketing, promotion and creativity through social media platforms and apparels."
        },
        
        {
            id: 4,
            img: art,
            topic: "ART HUB",
           content: "AIM : Appreciate the work of art including photography, drawing, engineering and new emerging designs in fashion."
        },
        {
            id: 5,
            img: code,
            topic: "CODE HUB",
           content: "AIM : learn the various types of programming language, different operating systems and basic data management."
        },
        {
            id: 6,
            img: research,
            topic: "RESEARCH HUB",
           content: "AIM :Learn on various tpyes of research and engage on research activities in various subjects."
        },
        {
            id: 7,
            img: fx1,
            topic: "FOREX HUB",
           content: "AIM :  Understand the various styles of trading and the different ways of investing."
        },
        {
            id: 8,
            img: business,
            topic: "BIZ HUB",
           content: "AIM : Get proficient knowledge in business setups and online business opportunities."
        }
    ]
};

export default data;