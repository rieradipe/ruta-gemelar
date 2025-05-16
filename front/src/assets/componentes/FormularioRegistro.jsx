import React, { useState } from "react";


const FormularioRegistro = () => {
  const [form, setForm] = useState({
    nombre: "",
    email: "",
    password: "",
    repeatPassword: "",
    fpp: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (form.password !== form.repeatPassword) {
      alert("Las contraseñas no coinciden");
      return;
    }

    console.log("Enviando datos:", form);
  };

  return (
    <form
      onSubmit={handleSubmit}
      className="w-full max-w-[400] rounded-xl shadow p-6 flex flex-col gap-4"
    >
      {[
        { name: "nombre", label: "Nombre:", type: "text" },
        { name: "email", label: "Email:", type: "email" },
        { name: "password", label: "Contraseña:", type: "password" },
        {
          name: "repeatPassword",
          label: "Repite tu contraseña",
          type: "password",
        },
        { name: "fpp", label: "Fecha probable de parto:", type: "date" },
      ].map(({ name, label, type }) => (
        <div key={name} className="flex flex-col">
          <label
            htmlFor={name}
            className="text font-semibold  mb-1 pl-4"
          >
            {label}
          </label>
          <input
            type={type}
            id={name}
            name={name}
            value={form[name]}
            onChange={handleChange}
            className="text-sm border border-[#B6654F] rounded-lg px-3 py-1 focus:outline-none focus:ring-1 focus:ring-[#B6654F]"
            required
          />
        </div>
      ))}
    </form>
  );
};

export default FormularioRegistro;
