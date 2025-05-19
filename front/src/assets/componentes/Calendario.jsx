import React, { useState } from "react";
import dayjs from "dayjs";
import "dayjs/locale/es";
dayjs.locale("es");

function Calendario() {
  const today = dayjs();
  const [currentMonth, setCurrentMonth] = useState(today.startOf("month"));

  const startDay = currentMonth.startOf("week");
  const endDay = currentMonth.endOf("month").endOf("week");

  const days = [];
  let day = startDay.clone();

  while (day.isBefore(endDay)) {
    days.push(day);
    day = day.add(1, "day");
  }

  const startNewMonth = (offset) => {
    setCurrentMonth(currentMonth.add(offset, "month"));
  };
  return (
    <div className="max-w-3xl w-full mx-auto p-4 bg-[#FDF7F1] rounded-xl shadow-md h-full flex flex-col">
      <div className="flex  justify-between items-center w-full max-w-4xl mb-2">
        <button
          onClick={() => startNewMonth(-1)}
          className="text-[#B6654F] font-bold"
        >
          ←
        </button>
        <h2 className="text-xl font-semibold text-[#B6654F]">
          {currentMonth.format("MMMM YYYY")}
        </h2>
        <button
          onClick={() => startNewMonth(1)}
          className="text-[#B6654F] font-bold"
        >
          →
        </button>
      </div>

      {/* Días de la semana */}
      <div className="grid grid-cols-7 grid-rows-6 gap-1 text-center text-sm">
        {["Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"].map((d) => (
          <div key={d} className="uppercase text-lg tracking-wide">
            {d}
          </div>
        ))}
      </div>

      {/* Días del mes */}
      <div className="grid grid-cols-7 grid-rows-6 gap-1 text-center font-bold text-[#B6654F] uppercase text-xs tracking-wide mb-1">
        {days.map((day, idx) => (
          <div
            key={idx}
            className={`aspect-square w-12 flex items-center justify-center border-2 rounded-xl shadow-md text-xs font-semibold transition
        ${
          day.month() !== currentMonth.month()
            ? "text-gray-300 border-gray-200"
            : "border-[#B6654F]"
        }
        ${
          day.isSame(today, "day")
            ? "bg-[#FDF7F1] scale-105 shadow-md"
            : "bg-white"
        }
        hover:bg-[#E6BAA6]/20
      `}
          >
            {day.date()}
          </div>
        ))}
      </div>
    </div>
  );
}

export default Calendario;
