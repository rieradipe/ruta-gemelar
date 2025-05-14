import React from "react";
import { Link } from "react-router-dom";
import Boton from "./Boton";
import "./Header.css";

const Header = () => {
  const estaLogueada = true; //añadimos logica token

  return (
    <header className="header">
      <div className="header-iner">
      <div className="header-text">
          <h1>Ruta Gemelar</h1>
          <p>
            El camino <span className="highlight">invisible</span> de las
            madres múltiples
          </p>
        </div>
        {estaLogueada && (
          <nav className="header-nav">
            <Link to="/diario">
              <Boton 
                texto="Ir al Diario"
                tipo="primario"
                forma="redondo"
              />
            </Link>
          </nav>
        )}
        </div>
    </header>
  );
};

export default Header;
