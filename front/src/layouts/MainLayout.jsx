import React from "react";
import { Outlet, useLocation } from "react-router-dom";
import Boton from "../assets/componentes/Boton";

import Header from "../assets/componentes/Header";
import Footer from "../assets/componentes/Footer";

const MainLayout = () => {
  const location = useLocation();
  const pathname = location.pathname.toLowerCase();

  const esHome = pathname === "/";
  const esDiario = pathname === "/diario";
  const esSemana = pathname === "/semana";
  const esCalendarioUsuria = pathname === "/calendario";

  return (
    <div className="min-h-screen flex flex-col bg-[#FDF71F1] px-4 md:px-10 py-6">
      <Header />
      <main className="flex-grow overflow-y-auto  md:overflow-visible   px-4 md:px-10 py-6">
        <Outlet />
      </main>

      <Footer />
    </div>
  );
};

export default MainLayout;
