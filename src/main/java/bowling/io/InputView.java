package bowling.io;

import bowling.model.frame.FrameNumber;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String askOfPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)? ");
        return scanner.nextLine();
    }

    public static int askCountOfDownPins(FrameNumber currentNumber) {
        System.out.print(String.format("%s 프레임 투구 : ", currentNumber));
        int countOfDownPins = scanner.nextInt();
        scanner.nextLine();
        return countOfDownPins;
    }
}
