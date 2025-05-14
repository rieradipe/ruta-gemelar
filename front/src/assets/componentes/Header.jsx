import React from "react";
import { Link } from "react-router-dom";
import Boton from "./Boton";

const Header = () => {
  const estaLogueada = true; //añadimos logica token

  return (
    <header className="bg-[#FDF7F1] h-full shadow p-4 flex flex-col items-center justify-between px-8">
      <div className="flex justify-between">
        <div className="flex flex-col justify-center">
          <h1 className="text-2xl font-bold text-[#B6654F]">Ruta Gemelar</h1>
          <p className="text-[3af558] mt-2 italic">
            El camino <span className="text-[#B6654F]">invisible</span> de las
            madres múltiples
          </p>
        </div>
        {estaLogueada && (
          <nav className="mt-4">
            <Link to="/diario">
              <Boton
                texto="Ir al Diario"
                tipo="primario"
                forma="redondo"
                className="px-6 py-3"
              />
            </Link>
          </nav>
        )}
      </div>
    </header>
  );
};

export default Header;
