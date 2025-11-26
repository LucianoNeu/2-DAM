package Obj;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;


@Data
@Entity
@Table(name = "Alumno")
public class Alumno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    public Alumno() {}

    public Alumno(String nombre, Integer edad, Curso curso) {
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", curso=" + curso +
                '}';
    }
}
