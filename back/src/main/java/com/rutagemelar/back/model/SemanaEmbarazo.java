package com.rutagemelar.back.model;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Data@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SemanaEmbarazo {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @JsonProperty("numeroSemana")
    private int numeroSemana;
    @JsonProperty("tipo_embarazo")
    private String tipoEmbarazo;
    private String titulo;

    @Column(length = 1500)
    @JsonProperty("descripcion")
    private String description;

    @Column(length = 1500)
    @JsonProperty("contenidoMama")
    private String contenidoMama;

    @Column(length = 1500)
    @JsonProperty("contenidoBebes")
    private String contenidoBebes;
    @JsonProperty("imagenUrl")
    private String imagenUrl;

 }


