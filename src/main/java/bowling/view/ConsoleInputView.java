package bowling.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {

    private static final Scanner SCANNER = new Scanner(System.in).useDelimiter(System.lineSeparator());

    @Override
    public String receivePlayerName() {
        printMessage("플레이어 이름은(3 english letters)? : ");
        return getUserInput();
    }

    @Override
    public int receiveNumberOfKnockedDownPins(int frameNumber) {
        printMessage(frameNumber + "프레임 투구 : ");
        return Integer.parseInt(getUserInput());
    }

    private String getUserInput() {
        return SCANNER.next();
    }

    private void printMessage(String message) {
        System.out.print(message);
    }

}
