package Obj;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Profesor")
public class Profesor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long id_profesor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "especialidad")
    private String especialidad;

    @Column(name = "gmail")
    private String gmail;

    @OneToOne(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Curso curso;

    public Profesor() {}

    public Profesor(String nombre, String especialidad, String gmail) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.gmail = gmail;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id_profesor=" + id_profesor +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", gmail='" + gmail + '\'' +
                '}';
    }
}
