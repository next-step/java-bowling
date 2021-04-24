package bowling.view;

import java.util.Scanner;

public class ConsoleInputView implements InputView {

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
        printMessage(System.lineSeparator());
        Scanner scanner = new Scanner(System.in).useDelimiter(System.lineSeparator() + "|\n");
        return scanner.next();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

}
