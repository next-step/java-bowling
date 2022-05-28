package bowling.view;

import bowling.domain.Pins;
import bowling.domain.User;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static User inputLetters() {
        System.out.print("플레이어 이름은(3 english letters)?");
        return new User(SCANNER.nextLine());
    }

    public static Pins inputBowl(int n) {
        System.out.print(n + "프레임 투구 : ");
        return new Pins(SCANNER.nextInt());
    }

}
