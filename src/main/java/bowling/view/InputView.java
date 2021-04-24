package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String inputPlayer() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public int inputCountOfDownPin(int round) {
        System.out.printf(String.format("%n%s프레임 투구 : ", round));
        return Integer.parseInt(scanner.nextLine());
    }
}
