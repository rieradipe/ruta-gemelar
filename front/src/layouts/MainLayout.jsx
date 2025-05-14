import React from "react";
import { Outlet } from "react-router-dom";

import Header from "../assets/componentes/Header";
import Footer from "../assets/componentes/Footer";

const MainLayout = () => {
    return (
        <div className="layout">
        <Header />
        <main className="main-content">
            <Outlet />
            </main>
            <Footer />
            </div>
    );
};

export default MainLayout;