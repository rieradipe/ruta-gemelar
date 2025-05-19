import Boton from "../assets/componentes/Boton";
import React, { useState } from "react";

const Diario = () => {
  const [formulario, setFormulario] = useState("");
  const [mostrarBotones, setMostrarBotones] = useState(true);

  const handleGuardar = () => {
    alert("Entrada guardada correctamente");
    setMostrarBotones(true);
  };

  return (
    <div className="w-full max-w-3xl mx-auto px-4 py-8">
      <h2 className="text-3xl font-bold text-[#B6654F] italic text-center">
        Tu Diario Emocional
      </h2>

      {/* Contenido del diario */}

      {/* MIS ENTRADAS */}

      <div className="flex flex-wrap gap-4 mb-2 items-center">
        <Boton
          texto="---Ver mi Diario---"
          tipo="secundario"
          forma="redondo"
          to="#"
        />
        <Boton
          texto="---Modificar entrada---"
          tipo="secundario"
          forma="redondo"
          to="#"
        />
        <Boton
          texto="---Borrar la entrada---"
          tipo="secundario"
          forma="redondo"
          to="#"
        />
      </div>

      {/* NUEVA ENTRADA */}
      <div className="bg-[#FDF7F1] rounded-xl shadow-md p-6 gap-8">
        <h2 className="text-m  mb-4 italic text-[#B6654F]">Nueva entrada</h2>
        <textarea
          placeholder="Escribe aquí cómo te sientes hoy..."
          className="w-full min-h-[200px] max-h-[400px] p-4 border border-[#E3D5B7] rounded-xl resize-vertical text-base bg-[#FDF7F1] leading-relaxed"
          value={formulario}
          onChange={(e) => setFormulario(e.target.value)}
        />
        <div className="mt-4 flex justify-end">
          <Boton
            texto="Guardar entrada"
            tipo="primario"
            forma="redondo"
            onclick={handleGuardar}
          />
        </div>
      </div>
      {mostrarBotones && (
        <div className="bg-[#FDF7F1] mt-16 flex justify-center rounded-xl shadow-md p-6 gap-8">
          <Boton texto="---Home---" tipo="secundario" forma="redondo" to="/" />
          <Boton
            texto="---Semana---"
            tipo="secundario"
            forma="redondo"
            to="/semana"
          />
        </div>
      )}
    </div>
  );
};

export default Diario;
