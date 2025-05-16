import React from "react";
import FormularioRegistro from "../assets/componentes/FormularioRegistro";
import Boton from "../assets/componentes/Boton";

const Registro = () => {
    return (
        <div className="flex flex-col items-center px-6 py-6 bg-[#FDF7F1]">
          {/* CONTENEDOR visual principal */}
          <div className="flex flex-row md:flex-row gap-10 bg-white border border-[#B6654F] rounded-2xl shadow-lg p-6 md:p-10 max-w-3xl  mt-10"
          style={{ margin: "15px", padding: "15px"}}>

            
            {/* Imagen */}
            <div className="w-full md:w-1/2 flex justify-center items-center">
              <img
                src="didi1.png"
                alt="Bebés gemelos"
                className="w-full h-auto object-contain rounded-xl"
                 style={{ margin: "15px", padding: "15px"}}
              />
            </div>
      
            {/* Formulario */}
            <div className="w-full md:w-1/2 flex justify-center items-center"
            style={{ margin: "15px", padding: "15px"}}>
              <FormularioRegistro />
            </div>
          </div>
      
          {/* 🔽 BOTONES debajo del div visual */}
          <div className="flex justify-center gap-10 mt-6">
            <Boton texto="Volver" tipo="secundario" to="/" />
            <Boton texto="Guardar" tipo="primario" />
          </div>
        </div>
      );
    }
    
    export default Registro;
