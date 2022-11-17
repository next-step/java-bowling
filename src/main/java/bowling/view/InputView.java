package bowling.view;

import bowling.domain.Name;
import bowling.domain.Hit;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Name inputName() {
        System.out.print("플레이어 이름은 (3 english letters)?: ");

        String name = scanner.nextLine();
        return new Name(name);
    }

    public static Hit inputPins(int frame) {
        System.out.printf("%d프레임 투구: ", frame);

        return new Hit(scanner.nextInt());
    }
}
