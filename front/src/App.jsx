import { Routes, Route } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import Home from "./pages/Home";
import Registro from "./pages/Registro";
import Login from "./pages/Login";
import CalendarioUsuaria from "./pages/CalendarioUsuaria";
import Semana from "./pages/Semana";
import "./App.css";
import Diario from "./pages/Diario";

function App() {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route index element={<Home />} />
        <Route path="registro" element={<Registro />} />
        <Route path="login" element={<Login />} />
        <Route path="calendario" element={<CalendarioUsuaria />} />
        <Route path="semana" element={<Semana />} />
        <Route path="diario" element={<Diario />} />
      </Route>
    </Routes>
  );
}

export default App;
