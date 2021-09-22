package bowling.view;

import bowling.domain.Player;
import java.util.Scanner;

public class InputView {

    private InputView() {
    }

    public static Player user() {
        Scanner sc = new Scanner(System.in);
        System.out.println("플레이어 이름은 : ");
        String name = sc.nextLine();
        return Player.from(name);
    }

    public static int downPinsCount() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
