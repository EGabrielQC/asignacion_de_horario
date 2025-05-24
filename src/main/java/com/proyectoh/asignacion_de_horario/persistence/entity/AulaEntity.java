package com.proyectoh.asignacion_de_horario.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//Anotaciones @
@Setter//Metodos set
@Getter//Metodos Get
@AllArgsConstructor//Constructor lleno
@NoArgsConstructor//Constructor sin argumento
@Entity//Mapea la clase a una tabla
@Table (name = "aulas")//genera la tabls
//esto es la clase que se va mapear a tablas en la bd
public class AulaEntity {
    @Id
    //
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@GenerateValue recibe parametros/Identity Genera los registros uno en uno
    private Integer id;
    private String nombre;
    private Integer capacidad;
    private String tipo;
    //Relación de llegada para Aula
    //## Recuerda que cuando en la relacion principal estar ManytoOne en la relación de llegada se invierte.
    @OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
    private List<HorarioEntity>horarioEntities = new ArrayList<>();
}
