package bowling.view;

import bowling.Username;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static Username playerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        String name = scanner.nextLine();
        return new Username(name);
    }

    public static int hitCount(int number) {
        System.out.print(number + " 프레임투구 : ");
        return scanner.nextInt();
    }
}
