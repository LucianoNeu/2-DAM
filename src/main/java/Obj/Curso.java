package Obj;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Curso")
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso", nullable = false)
    private Long id_curso;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "aula")
    private String aula;

    @OneToOne
    @JoinColumn(name = "id_profesor", unique = true)
    private Profesor profesor;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alumno> alumnos;

    public Curso() {}

    public Curso(String nombre, String aula, Profesor profesor) {
        this.nombre = nombre;
        this.aula = aula;
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id_curso=" + id_curso +
                ", nombre='" + nombre + '\'' +
                ", aula='" + aula + '\'' +
                ", profesor=" + (profesor != null ? profesor.getNombre() : "Sin profesor") +
                ", alumnos=" + (alumnos != null ? alumnos.size() : 0) +
                '}';
    }
}