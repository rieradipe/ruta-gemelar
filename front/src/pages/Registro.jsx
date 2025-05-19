import React from "react";
import FormularioRegistro from "../assets/componentes/FormularioRegistro";
import Boton from "../assets/componentes/Boton";

const Registro = () => {
  return (
    <div className="flex flex-col items-center px-6 py-6 bg-[#FDF7F1]">
      {/* CONTENEDOR visual principal */}
      <div className="flex flex-col md:flex-row gap-10 bg-white border-[3px] border-[#B6654F]  rounded-2xl shadow-lg p-6 md:p-10 max-w-2xl  mt-10">
        {/* Imagen */}
        <div className="w-full md:w-1/2 flex justify-center items-center p-4">
          <img
            src="didi1.png"
            alt="Bebés gemelos"
            className="w-full h-auto object-contain rounded-xl"
          />
        </div>

        {/* Formulario */}
        <div className="w-full md:w-1/2 flex justify-center items-center p-4">
          <FormularioRegistro />
        </div>
      </div>
      <Boton
        texto="---Guardar---"
        tipo="secundario"
        forma="redondo"
        type="submit"
      />
    </div>
  );
};

export default Registro;
