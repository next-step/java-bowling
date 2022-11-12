package bowling;

import java.util.Scanner;

public class InputView {

    public static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String scanName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }
}
