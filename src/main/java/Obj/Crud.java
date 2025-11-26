package Obj;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Crud {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Escuela");
    private final EntityManager em = emf.createEntityManager();

    public void insertProfesor() {
        String nombre = Util.input("Ingrese el nombre del profesor: ");
        String especialidad = Util.input("Ingrese la especialidad del profesor: ");
        String email = Util.input("Ingrese el email del profesor: ");

        em.getTransaction().begin();
        em.persist(new Profesor(nombre, especialidad, email));
        em.getTransaction().commit();
        System.out.println("Profesor insertado");
    }

    public void updateProfesor() {
        TypedQuery<Profesor> query = em.createQuery("select p from Profesor p", Profesor.class);
        List<Profesor> profesores = query.getResultList();

        if (profesores.isEmpty()) {
            System.out.println("No hay profesores para actualizar.");
            return;
        }

        System.out.println("Profesores:");
        for (int i = 0; i < profesores.size(); i++) {
            System.out.println((i + 1) + ". " + profesores.get(i).getNombre());
        }

        int index = Integer.parseInt(Util.input("Seleccione el número del profesor: ")) - 1;
        Profesor profesor = profesores.get(index);

        String nuevoNombre = Util.input("Nuevo nombre (" + profesor.getNombre() + "): ");
        String nuevaEspecialidad = Util.input("Nueva especialidad (" + profesor.getEspecialidad() + "): ");
        String nuevoEmail = Util.input("Nuevo email (" + profesor.getGmail() + "): ");

        em.getTransaction().begin();
        profesor.setNombre(nuevoNombre.isEmpty() ? profesor.getNombre() : nuevoNombre);
        profesor.setEspecialidad(nuevaEspecialidad.isEmpty() ? profesor.getEspecialidad() : nuevaEspecialidad);
        profesor.setGmail(nuevoEmail.isEmpty() ? profesor.getGmail() : nuevoEmail);
        em.getTransaction().commit();

        System.out.println("Profesor actualizado correctamente.");
    }

    public void deleteProfesor() {
        TypedQuery<Profesor> query = em.createQuery("select p from Profesor p", Profesor.class);
        List<Profesor> profesores = query.getResultList();

        if (profesores.isEmpty()) {
            System.out.println("No hay profesores para eliminar.");
            return;
        }

        System.out.println("Profesores:");
        for (int i = 0; i < profesores.size(); i++) {
            System.out.println((i + 1) + ". " + profesores.get(i).getNombre());
        }

        int index = Integer.parseInt(Util.input("Seleccione el número del profesor a eliminar: ")) - 1;
        Profesor profesor = profesores.get(index);

        em.getTransaction().begin();
        em.remove(profesor);
        em.getTransaction().commit();

        System.out.println("Profesor eliminado correctamente.");
    }

    public void mostrarProfesores(boolean recursivo) {
        TypedQuery<Profesor> query = em.createQuery("SELECT p FROM Profesor p", Profesor.class);
        List<Profesor> profesores = query.getResultList();

        if (profesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
            return;
        }

        for (Profesor profesor : profesores) {
            System.out.println("Profesor: " + profesor.getNombre() + " (" + profesor.getEspecialidad() + ")");

            if (recursivo) {
                Curso curso = profesor.getCurso();
                if (curso != null) {
                    System.out.println("   → Curso: " + curso.getNombre() + " (" + curso.getAula() + ")");
                    if (curso.getAlumnos() != null && !curso.getAlumnos().isEmpty()) {
                        for (Alumno alumno : curso.getAlumnos()) {
                            System.out.println("       - Alumno: " + alumno.getNombre() + " (" + alumno.getEdad() + " años)");
                        }
                    } else {
                        System.out.println("       (sin alumnos)");
                    }
                } else {
                    System.out.println("   (sin curso asignado)");
                }
            }
        }
    }

    public void insertCurso() {
        String nombre = Util.input("Ingrese el nombre del curso: ");
        String aula = Util.input("Ingrese el aula del curso: ");

        TypedQuery<Profesor> query = em.createQuery("select p from Profesor p", Profesor.class);
        List<Profesor> profesores = query.getResultList();

        if (profesores.isEmpty()) {
            System.out.println("No hay profesores disponibles. Inserta uno primero.");
            return;
        }

        System.out.println("Profesores disponibles:");
        for (int i = 0; i < profesores.size(); i++) {
            System.out.println((i + 1) + ". " + profesores.get(i).getNombre());
        }

        int index = Integer.parseInt(Util.input("Seleccione el número del profesor: ")) - 1;
        Profesor profesor = profesores.get(index);

        em.getTransaction().begin();
        Curso curso = new Curso(nombre, aula, profesor);
        em.persist(curso);
        em.getTransaction().commit();

        System.out.println("Curso insertado correctamente.");
    }

    public void updateCurso() {
        TypedQuery<Curso> query = em.createQuery("select c from Curso c", Curso.class);
        List<Curso> cursos = query.getResultList();

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos para actualizar.");
            return;
        }

        System.out.println("Cursos:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).getNombre());
        }

        int index = Integer.parseInt(Util.input("Seleccione el curso: ")) - 1;
        Curso curso = cursos.get(index);

        String nuevoNombre = Util.input("Nuevo nombre (" + curso.getNombre() + "): ");
        String nuevaAula = Util.input("Nueva aula (" + curso.getAula() + "): ");

        em.getTransaction().begin();
        curso.setNombre(nuevoNombre.isEmpty() ? curso.getNombre() : nuevoNombre);
        curso.setAula(nuevaAula.isEmpty() ? curso.getAula() : nuevaAula);
        em.getTransaction().commit();

        System.out.println("Curso actualizado correctamente.");
    }

    public void deleteCurso() {
        TypedQuery<Curso> query = em.createQuery("select c from Curso c", Curso.class);
        List<Curso> cursos = query.getResultList();

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos para eliminar.");
            return;
        }

        System.out.println("Cursos:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).getNombre());
        }

        int index = Integer.parseInt(Util.input("Seleccione el curso a eliminar: ")) - 1;
        Curso curso = cursos.get(index);

        em.getTransaction().begin();
        em.remove(curso);
        em.getTransaction().commit();

        System.out.println("Curso eliminado correctamente.");
    }

    public void insertAlumno() {
        String nombre = Util.input("Ingrese el nombre del alumno: ");
        String edad = Util.input("Ingrese la edad del alumno: ");

        TypedQuery<Curso> query = em.createQuery("select c from Curso c", Curso.class);
        List<Curso> cursos = query.getResultList();

        if (cursos.isEmpty()) {
            System.out.println("No hay cursos disponibles. Inserta uno primero.");
            return;
        }

        System.out.println("Cursos disponibles:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).getNombre());
        }

        int index = Integer.parseInt(Util.input("Seleccione el número del curso: ")) - 1;
        Curso curso = cursos.get(index);

        em.getTransaction().begin();
        em.persist(new Alumno(nombre, Integer.parseInt(edad), curso));
        em.getTransaction().commit();
        System.out.println("Alumno insertado");
    }

    public void updateAlumno() {
        TypedQuery<Alumno> query = em.createQuery("select a from Alumno a", Alumno.class);
        List<Alumno> alumnos = query.getResultList();

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos para actualizar.");
            return;
        }

        System.out.println("Alumnos:");
        for (int i = 0; i < alumnos.size(); i++) {
            System.out.println((i + 1) + ". " + alumnos.get(i).getNombre());
        }

        int index = Integer.parseInt(Util.input("Seleccione el alumno: ")) - 1;
        Alumno alumno = alumnos.get(index);

        String nuevoNombre = Util.input("Nuevo nombre (" + alumno.getNombre() + "): ");
        String nuevaEdad = Util.input("Nueva edad (" + alumno.getEdad() + "): ");

        em.getTransaction().begin();
        alumno.setNombre(nuevoNombre.isEmpty() ? alumno.getNombre() : nuevoNombre);
        if (!nuevaEdad.isEmpty()) alumno.setEdad(Integer.parseInt(nuevaEdad));
        em.getTransaction().commit();

        System.out.println("Alumno actualizado correctamente.");
    }

    public void deleteAlumno() {
        TypedQuery<Alumno> query = em.createQuery("select a from Alumno a", Alumno.class);
        List<Alumno> alumnos = query.getResultList();

        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos para eliminar.");
            return;
        }

        System.out.println("Alumnos:");
        for (int i = 0; i < alumnos.size(); i++) {
            System.out.println((i + 1) + ". " + alumnos.get(i).getNombre());
        }

        int index = Integer.parseInt(Util.input("Seleccione el alumno a eliminar: ")) - 1;
        Alumno alumno = alumnos.get(index);

        em.getTransaction().begin();
        em.remove(alumno);
        em.getTransaction().commit();

        System.out.println("Alumno eliminado correctamente.");
    }
}