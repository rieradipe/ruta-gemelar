import React from "react";
import Boton from "../assets/componentes/Boton";

const Diario = () => {
  return (
    <>
      {/* Título separado */}
      <div className="text-center py-8">
        <h1 className="text-3xl font-bold text-[#B6654F] italic">
          Tu Diario Emocional
        </h1>
      </div>

      {/* Contenido del diario */}
      <div className="min-h-screen bg-[#FDF7F1] px-6 pb-10 flex flex-col items-center gap-8">
        {/* MIS ENTRADAS */}
        <div className="w-full max-w-3xl bg-white p-6 rounded-xl shadow-md">
          <h2 className="text-xl font-semibold text-[#B6654F] mb-4 italic">
            Mis entradas
          </h2>
          <div className="items-center flex flex-row mb-4 gap-4">
            <Boton texto="---Ver mi Diario---" tipo="secundario" to="#" />
            <Boton texto="---Modificar entrada---" tipo="secundario" to="#" />
            <Boton texto="---Borrar la entrada---" tipo="secundario" to="#" />
          </div>
        </div>

        {/* NUEVA ENTRADA */}
        <div className="w-full h-40 max-w-3xl bg-white p-6 rounded-xl shadow-md">
          <h2 className="text-m  mb-4 italic text-[#B6654F]">Nueva entrada</h2>
          <textarea
            placeholder="Escribe aquí cómo te sientes hoy..."
            className="w-full h-[400px] p-6 border border-[#E3D5B7] rounded-xl resize-none text-base bg-[#FDF7F1] leading-relaxed tracking-wide shadow-inner"
          />
          <div className="mt-4 flex justify-end">
            <Boton texto="Guardar entrada" tipo="primario" to="#" />
          </div>
        </div>
      </div>
    </>
  );
};

export default Diario;
