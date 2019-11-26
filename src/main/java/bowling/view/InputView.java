package bowling.view;

import java.util.Scanner;

public class InputView {

    public Object getName() {
        System.out.println("플레이어 이름은? (3 english letters?");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}
