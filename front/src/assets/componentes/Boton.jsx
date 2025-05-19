import React from "react";
import { Link } from "react-router-dom";
function Boton({ texto, to, onClick, tipo, forma, className, type }) {
  const base =
    "inline-block font-semibold text-base text-center px-6 py-2 transition durantion-200 no-underline";
  const estilo =
    tipo === "primario"
      ? "bg-[#B6654F] text-white"
      : "bg-white text-[#B6654F] border-2 border-[#B6654F]";

  const redondez = forma === "redondo" ? "rounded-full" : "rounded-[xl]";

  const hover =
    tipo === "primario"
      ? "hover:bg-[#a3503c] hover:text-white"
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
      type={type}
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
  typo: "button",
};

export default Boton;
