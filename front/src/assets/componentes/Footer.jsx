import React from "react";

import "./Footer.css"


const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer-logo-container">
                <img src="/logoLunas.png" alt="Logo Ruta Gemelar" />
            </div>
            <div className="footer-text">
                <p>© {new Date().getFullYear()} Ruta Gemelar · Todas las lunas contigo</p>
            </div>
        </footer>
    )
}

export default Footer;