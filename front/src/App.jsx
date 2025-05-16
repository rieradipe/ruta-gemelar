import { Routes, Route } from "react-router-dom";
import MainLayout from "./layouts/MainLayout";
import Home from "./pages/Home";
import Registro from "./pages/Registro";
import Login from "./pages/Login";


function App() {
  return (
    <Routes>
    <Route path="/" element={<MainLayout />} >
      <Route index element = {<Home />} />
      <Route path="/registro" element={<Registro />} />
      <Route path="/login" element={<Login />} />
      </Route>
    </Routes>
  );
}

export default App;
