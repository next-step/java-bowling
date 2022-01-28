package bowling.view;

import java.util.Scanner;

public class Input {
    static Scanner SCANNER = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int inputScore(int frameNumber) {
        System.out.println();
        System.out.print(frameNumber + " 프레임 투구 : ");
        return SCANNER.nextInt();
    }
}
