import React from 'react';

function Boton({ texto, onClick, tipo="primario",forma="normal", className =""}) {
  const base = "px-6 py-3 font-semibold transition duration 200 ";
  const estilo = tipo === "primario"
  ? "bg-[#B6654F] text-white"
  : "bg-white text-[#B6654F] border-2 border-[#B6654F]";

  const redondez = forma === "redondo" ? "rounded-full" : "rounded-[15px]";


  return (
    <button
      className={`${base} ${estilo} ${redondez} ${className}`}
      onClick={onClick}
    >
        {texto}
    </button>
    );
}

export default Boton;
