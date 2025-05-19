import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Boton from "../assets/componentes/Boton";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    // Simulación de login: guarda un token falso y redirige
    if (email && password) {
      localStorage.setItem("token", "TOKEN_DE_PRUEBA");
      navigate("/semana");
    } else {
      alert("Completa ambos campos");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex flex-col gap-4">
      <h2 className="text-xl font-semibold text-[#B6654F] mb-2">
        Inicia sesión
      </h2>

      <div className="w-64">
        <label className="block text-sm font-medium text-[#B6654F] mb-1">
          Email
        </label>
        <input
          type="email"
          placeholder="ejemplo@email.com"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="w-full px-4 py-3 border border-[#E3D5B7] rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-[#B6654F] text-sm"
          required
        />
      </div>
      <div className="w-full">
        <label className="block text-sm font-medium text-[#B6654F] mb-1">
          Contraseña
        </label>
        <input
          type="password"
          placeholder="********"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full px-4 py-3 border border-[#E3D5B7] rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-[#B6654F] text-sm"
          required
        />
      </div>
      <Boton
        texto="---Guardar---"
        tipo="secundario"
        forma="redondo"
        type="submit"
      />
    </form>
  );
};

export default Login;
