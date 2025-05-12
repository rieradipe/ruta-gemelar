import React from 'react';

function Boton({ texto, onClick, primario = true, className = "" }) {
  const base = "px-6 py-3 rounded-[15px] font-semibold transition duration 200 ";
  const estilo = primario
  ? "bg-[#B6654F] text-white"
  : "bg-white text-[#B6654F] border-2 border-[#B6654F]";


  return (
    <button
      className={`${base} ${estilo} ${className}`}
      onClick={onClick}
    >
        {texto}
    </button>
    );
}

export default Boton;
