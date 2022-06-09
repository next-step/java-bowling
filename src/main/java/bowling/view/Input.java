package bowling.view;

import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);
    public static String scan() {
        return scanner.nextLine();
    }


    public static String scanPlayer() {
        Output.print("Please put player name (Max 3 latters)\n");
        return scan();
    }
}
