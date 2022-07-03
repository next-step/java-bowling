package bowling_step3.view;

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

    public static int scanNumPlayers() {
        System.out.println("How many people to play?");
        return Integer.parseInt(scanner.nextLine());
    }
}
