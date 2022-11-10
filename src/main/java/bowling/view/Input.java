package bowling.view;

import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int inputScore(int round) {
        System.out.print(round + "프레임 투구 : ");
        return Integer.parseInt(scanner.nextLine());
    }
}
