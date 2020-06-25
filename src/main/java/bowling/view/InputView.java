package bowling.view;

import java.util.Scanner;

public class InputView {
    public static String printPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int printFramePitches(int frameNumber) {
        System.out.print(frameNumber + "프레임 투구 : ");

        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
