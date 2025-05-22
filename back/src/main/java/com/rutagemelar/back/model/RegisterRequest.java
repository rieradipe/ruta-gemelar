package com.rutagemelar.back.model;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class RegisterRequest {
    private String nombre;
    private String email;
    private String password;
    private String tipoEmbarazoGemelar;
    private String fechaProbableParto;
}
