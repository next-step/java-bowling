package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        return scanner.next();
    }

    public static int inputPitchBowl(int stage) {
        System.out.print(stage + "프레임 투구 : ");
        return scanner.nextInt();
    }


}
