import Boton from "./Boton";
const Header = () => {
  const estaLogueada = true;

  return (
    <header className="bg-[#FDF7F1] shadow-md ">
      <div className="max-w-6xl mx-auto px-6 py-6 flex justify-between items-start gap-6">
        {estaLogueada && (
          <Boton texto="---Tú Diario---" tipo="secundario" to="/diario">
            Ir al Diario
          </Boton>
        )}

        {/* Bloque de texto centrado */}
        <div className="text-center flex-1">
          <h1 className="text-3xl font-bold text-[#B6654F]">Ruta Gemelar</h1>
          <p className="text-[#3A3F58] italic text-sm">
            El camino <span className="text-[#B6654F]">invisible</span> de las
            madres múltiples
          </p>
        </div>
      </div>
    </header>
  );
};

export default Header;
