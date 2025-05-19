import React from "react";
import { Outlet } from "react-router-dom";

import Header from "../assets/componentes/Header";
import Footer from "../assets/componentes/Footer";

const MainLayout = () => {
  return (
    <div className="min-h-screen flex flex-col justify-between px-4 md:px-10 py-6 bg-[#FDF7F1]">
      <Header />
      <main className="flex-grow">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
};

export default MainLayout;
