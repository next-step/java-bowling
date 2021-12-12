package bowling.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String inputName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

}
