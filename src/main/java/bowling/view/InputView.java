package bowling.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int inputUserCount() {
        System.out.println("How many people? ");
        String inputCount = scanner.nextLine();
        validNumber(inputCount);
        return Integer.parseInt(inputCount);
    }

    private void validNumber(String inputCount) {
        if (!inputCount.matches("\\d")) {
            throw new IllegalArgumentException();
        }
    }

    public String inputName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public int inputPitch(String userName) {
        System.out.println(String.format("%s's turn : : ", userName));
        return scanner.nextInt();
    }
}
