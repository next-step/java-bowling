package bowling.view;

import bowling.domain.Bowler;

import java.util.Scanner;

public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static Bowler getBowler(String message) {
        System.out.print(message);
        String result = scanner.next();
        System.out.println();
        return Bowler.from(result);
    }

    public static int getHitCount(String message) {
        System.out.print(message);
        int result = scanner.nextInt();
        System.out.println();
        return result;
    }
}
