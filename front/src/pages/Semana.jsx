import React, { useState } from "react";
import Boton from "../assets/componentes/Boton";

const Semana = () => {
  const [mostrarBotones, setMostrarBotones] = useState(true);

  return (
    <>
      <div className="text-center mb-10">
        <h1 className="text-4xl font-bold text-[#B6654F] italic">Semana 12</h1>
      </div>

      <div className="flex flex-row md:flex-row justify-center items-start gap-8 max-w-6xl mx-auto w-full mt-10">
        {/* Cambios en la madre */}
        <div className="flex-1 basis-1/3 bg-white border border-[#E3D5B7] rounded-xl p-6 shadow-md min-h-[220px] md:h-[280px]">
          <h2 className="text-xl font-semibold text-[#B6654F] mb-3">Para ti</h2>
          <p className="text-base text-gray-700 h-full">
            Estás entrando en un momento de mayor energía...
          </p>
        </div>

        {/* Imagen en el centro */}
        <div className="flex-1 basis-1/3 h-[350px] flex justify-center items-center">
          <img
            src="/public/didi1.png"
            alt="Ilustración semana"
            className="rounded-2xl shadow-md object-cover h-full max-h-[400px] w-auto"
          />
        </div>

        {/* Cambios en los bebés */}
        <div className="flex-1 basis-1/3 bg-white border border-[#E3D5B7] rounded-xl p-6 shadow-md min-h-[220px] md:h-[280px]">
          <h2 className="text-xl font-semibold text-[#B6654F] mb-3">
            Para tus bebés
          </h2>
          <p className="text-base text-gray-700">
            Esta semana sus órganos vitales están formados...
          </p>
        </div>
      </div>
      {mostrarBotones && (
        <div className="bg-[#FDF7F1] mt-16 flex justify-center rounded-xl shadow-md p-6 gap-8">
          <Boton texto="---Home---" tipo="secundario" forma="redondo" to="/" />
          <Boton
            texto="---Calendario---"
            tipo="secundario"
            forma="redondo"
            to="/CalendarioUsuaria"
          />
        </div>
      )}
    </>
  );
};

export default Semana;
