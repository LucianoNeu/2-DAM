import Obj.Crud;
import Obj.Util;

public class Main {
    public static void main(String[] args) {
        boolean lp = true;
        while (lp) {
            Crud crud = new Crud();
            String comand = Util.input("$ ");
            String[] comands = comand.split(" ");

            // Insert
            if (comands[0].equalsIgnoreCase("insert")) {
                if (comands[1].equalsIgnoreCase("curso")) {
                    crud.insertCurso();
                }
                else if (comands[1].equalsIgnoreCase("alumno")) {
                    crud.insertAlumno();
                }
                else if (comands[1].equalsIgnoreCase("profesor")) {
                    crud.insertProfesor();
                } else { System.out.println("Comando invalido"); }
            }

            // Delete
            else if (comands[0].equalsIgnoreCase("delete")) {
                if (comands[1].equalsIgnoreCase("curso")) {
                    crud.deleteCurso();
                }
                else if (comands[1].equalsIgnoreCase("alumno")) {
                    crud.deleteAlumno();
                }
                else if (comands[1].equalsIgnoreCase("profesor")) {
                    crud.deleteProfesor();
                } else { System.out.println("Comando invalido"); }
            }

            // Update
            else if (comands[0].equalsIgnoreCase("update")) {
                if (comands[1].equalsIgnoreCase("curso")) {
                    crud.updateCurso();
                }
                else if (comands[1].equalsIgnoreCase("alumno")) {
                    crud.updateAlumno();
                }
                else if (comands[1].equalsIgnoreCase("profesor")) {
                    crud.updateProfesor();
                } else { System.out.println("Comando invalido"); }
            }

            // Mostrar
            else if (comands[0].equalsIgnoreCase("show")) {
                if (comands[1].equalsIgnoreCase("-r")) {
                    if (comands[2].equalsIgnoreCase("profesor")) {
                        crud.mostrarProfesores(true);
                    } else { System.out.println("Comando invalido"); }
                }
                else {
                    if (comands[1].equalsIgnoreCase("profesor")) {
                        crud.mostrarProfesores(false);
                    } else { System.out.println("Comando invalido"); }
                }
            }

            // Salir
            else if (comands[0].equalsIgnoreCase("exit")) {
                lp = false;
            } else { System.out.println("Comando invalido"); }
        }
    }
}
