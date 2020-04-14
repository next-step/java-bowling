package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return scanner.nextLine();
    }

    public static int inputBowl(String playerName) {
        System.out.print(String.format("%s `s 프레임 투구 : ", playerName));
        return Integer.parseInt(scanner.nextLine());
    }
}
