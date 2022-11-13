package bowling.view;

import java.util.Scanner;

public class InputView {

    public String inputName() {
        System.out.println("플레이어 이름은(3 english letters)?:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
