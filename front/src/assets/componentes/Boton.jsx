import React from 'react';

function Boton({ texto, onClick, tipo="primario",forma="normal", className =""}) {
  const base = "px-6 py-2 font-semibold transition duration 200 text-sm";
  const estilo = tipo === "primario"
  ? "bg-[#B6654F] text-white"
  : "bg-white text-[#B6654F] border-2 border-[#B6654F]";

  const redondez = forma === "redondo" ? "rounded-full" : "rounded-[15px]";

  const hover =
  tipo === "primario"
    ? "hover:bg-white hover:text-[#B6654F] hover:border-[#B6654F]"
    : "hover:bg-[#B6654F] hover:text-white";



  return (
    <button
      className={`${base} ${estilo} ${redondez} ${hover} ${className}`}
      onClick={onClick}
    >
        {texto}
    </button>
    );
}

export default Boton;