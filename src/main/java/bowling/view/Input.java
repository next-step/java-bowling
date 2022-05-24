package bowling.view;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);
    public static String scan() {
        System.out.println("put your nick name(3 characters): ");
        return scanner.nextLine();
    }
}
