import React from "react";
import { useNavigate } from "react-router-dom";
import Boton  from "../assets/componentes/Boton";



function Home() {
  const navigate = useNavigate();

return (
    <main>
  
        <img src="/ImageHome.jpg"
        alt="Bebes gemelos simulando un embarazo abanzado"
        className="w-40 h-40 object-contain rounded-[15px] border-2 border-[#B6654F]" />

        <p className="text-center text-sm text-[#3A3F58] px-4">
            Ruta Gemelar nace del deseo de acompañar y visibilizar el camino emocional de los embarazos múltiples.
            Una experiencia profunda, desafiante y a menudo solitaria, que nuestra creadora vivió en primera persona.
            Esta app es un espacio íntimo para reflexionar, registrar emociones y sentirse menos sola en el viaje.
        </p>
        <div className="flex gap-3 justify-center w-full max-w-xs">
            <Boton texto="Registrarse"
            tipo="primario"
            forma="normal"
            onClick= { () => navigate("/Register")}
            className="w-full rounded-[15px]"
            />


            <Boton texto="Iniciar sesión"
            tipo="primario"
            forma="normal"
            onClick= { () =>  navigate("/Login")} 
            className="w-full rounded-[15px]"
            />
        </div>
        
    </main>

    
);

}
export default Home;
