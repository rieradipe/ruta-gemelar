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
      className="flex flex-col gap-6 bg-white border-[3px] border-[#B6654F] rounded-2xl shadow-lg w-full max-w-2xl px-10  py-12"
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
        {
          name: "fpp",
          label: "Fecha probable de parto:",

          type: "date",
        },
      ].map(({ name, label, type }) => (
        <div key={name} className="flex flex-col gap-1 w-full max-w-md mx-auto">
          <label
            htmlFor={name}
            className="text-[#3A3F58] font-semibold text-lg mb-1"
          >
            {label}
          </label>
          <input
            type={type}
            id={name}
            name={name}
            value={form[name]}
            onChange={handleChange}
            required
            className="w-full border-[2px] border-[#B6654F] rounded-xl px-4 py-3 text-sm focus:outline-none focus:ring-2 focus:ring-[#B6654F]"
          />
        </div>
      ))}
    </form>
  );
};

export default FormularioRegistro;
