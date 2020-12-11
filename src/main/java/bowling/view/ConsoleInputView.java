package bowling.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {
    public static final String ERROR_MSG_PREFIX = "[ERROR] ";
    private final Scanner scanner;

    public ConsoleInputView() {
        scanner = new Scanner(System.in);
    }

    @Override
    public String getPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    @Override
    public int getKnockDownPins(int frameIndex) {
        System.out.print(frameIndex + "프레임 투구 : ");
        return Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void printError(RuntimeException e) {
        System.out.println(ERROR_MSG_PREFIX + e.getMessage());
    }
}
