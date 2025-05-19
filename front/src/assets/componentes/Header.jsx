import Boton from "./Boton";
import { useLocation } from "react-router-dom";
const Header = () => {
  const estaLogueada = true;
  const location = useLocation();

  const noMostrarBotonDiario = location.pathname === "/diario";

  return (
    <header className="bg-[#FDF7F1] shadow-md relative">
      <div className="max-w-6xl mx-auto px-6 py-6 flex justify-center items-center">
        {estaLogueada && !noMostrarBotonDiario && (
          <div className="items-left left-0 top-1/2 -translate-y-1/2">
            <Boton
              texto="---Tú Diario---"
              tipo="secundario"
              to="/diario"
              forma="redondo"
            />
          </div>
        )}

        {/* Bloque de texto centrado */}
        <div className="text-center">
          <h1 className="text-3xl font-bold text-[#B6654F]">Ruta Gemelar</h1>
          <p className="text-[#3A3F58] italic text-sm mt-1">
            El camino <span className="text-[#B6654F]">invisible</span> de las
            madres múltiples
          </p>
        </div>
      </div>
    </header>
  );
};

export default Header;
