package Obj;

import java.util.Scanner;

public class Util {
    public static String input(String text){
        Scanner sc = new Scanner(System.in);
        System.out.print(text);
        return sc.nextLine();
    }
}
