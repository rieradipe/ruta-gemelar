import React, { useState } from "react";
import Boton from "../assets/componentes/Boton";
import Calendario from "../assets/componentes/Calendario";

function CalendarioUsuaria() {
  const [mostrarBotones, setMostrarBotones] = useState(true);
  return (
    <>
      <div className="bg-[#FDF7F1] flex flex-col items-center  p-4 max-w-4xl mx-auto min-h-screen overflowautorounded-xl shadow-md">
        <h1 className="text-3xl text-[3A3F58] italic flex justify-center mb-4 italic">
          Tú calendario
        </h1>
        <Calendario />
        {mostrarBotones && (
          <div className="bg-[#FDF7F1] mt-16 flex justify-center rounded-xl shadow-md p-6 gap-8">
            <Boton
              texto="---Home---"
              tipo="secundario"
              forma="redondo"
              to="/"
            />
            <Boton
              texto="---Semana---"
              tipo="secundario"
              forma="redondo"
              to="/semana"
            />
          </div>
        )}
      </div>
    </>
  );
}

export default CalendarioUsuaria;
