import React from "react";
import { Link } from "react-router-dom";
function Boton({ texto, to, onClick, tipo, forma, className }) {
  const base = "box-border font-semibold text-lg transition duration-200 no-underline w-[20ch] text-center";
  const estilo =
    tipo === "primario"
      ? "bg-[#B6654F] text-white"
      : "bg-white text-[#B6654F] border-2 border-[#B6654F]";

  const redondez = forma === "redondo" ? "rounded-full" : "rounded-[15px]";

  const hover =
    tipo === "primario"
      ? "hover:bg-white hover:text-[#B6654F] hover:border-[#B6654F]"
      : "hover:bg-[#B6654F] hover:text-white";

  const classes = `${base} ${estilo} ${redondez} ${hover} ${className}`;

  if (to) {
    return (
      <Link to={to} className={classes}>
        {texto}
      </Link>
    );
  }

  return (
    <button
      className={`${base} ${estilo} ${redondez} ${hover} ${className}`}
      onClick={onClick}
    >
      {texto}
    </button>
  );
}
Boton.defoultProps = {
  tipo: "primario",
  forma: "normal",
  className: "",
};

export default Boton;
