import React from "react";

import Boton from "../assets/componentes/Boton";


function Home() {
 

  return (
    <div className="flex flex-col items-center text-center px-4">
    <img
      src="/ImageHome.jpg"
      alt="Bebés gemelos simulando un embarazo avanzado"
      className="w-40 h-40 object-contain rounded-[15px] border-2 border-[#B6654F] mb-4"
    />

    <p className="text-sm text-[#3A3F58] mb-4">
      Ruta Gemelar nace del deseo de acompañar y visibilizar el camino emocional de los embarazos múltiples.
      Una experiencia profunda, desafiante y a menudo solitaria, que nuestra creadora vivió en primera persona.
      Esta app es un espacio íntimo para reflexionar, registrar emociones y sentirse menos sola en el viaje.
    </p>

    <p className="text-center mt-4 text-[#3A3F58]">
      Bienvenida a la app. Aquí puedes explorar, registrar, y reflexionar.
    </p>

    <div className="flex gap-4 justify-center mt-6">
      <Boton texto="Iniciar sesión" tipo="primario" to="/login" />
      <Boton texto="Registrarse" tipo="secundario" to="/registro" />
    </div>
  </div>
);
}
    
  
export default Home;
