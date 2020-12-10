package bowling.view;

import java.util.Scanner;

public class ConsoleInputView {
    public static final String ERROR_MSG_PREFIX = "[ERROR] ";
    private final Scanner scanner;

    public ConsoleInputView() {
        scanner = new Scanner(System.in);
    }

    public String getPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public int getKnockDownPins(int frameIndex) {
        System.out.print(frameIndex +"프레임 투구 : ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void printError(RuntimeException e) {
        System.out.println(ERROR_MSG_PREFIX + e.getMessage());
    }
}
