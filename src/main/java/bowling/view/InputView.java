package bowling.view;

import java.util.Scanner;

public class InputView {
    public static final Scanner scanner = new Scanner(System.in);

    public static String doInputPlayer() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static String doInputScore(int frameNumber) {
        System.out.print(frameNumber + "프레임 투구 : ");
        return scanner.nextLine();
    }
}
