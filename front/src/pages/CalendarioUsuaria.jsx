import React from "react";
import Calendario from "../assets/componentes/Calendario";

function CalendarioUsuaria() {
  return (
    <div className="min-h-screen bg-[#FDF7F1] flex flex-col item-center p-6">
      <h1 className="text-3xl text-[3A3F58] italic flex justify-center mb-4 italic">
        Tú calendario
      </h1>
      <Calendario />
    </div>
  );
}

export default CalendarioUsuaria;
