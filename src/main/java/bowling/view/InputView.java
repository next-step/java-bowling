package bowling.view;

import bowling.domain.BowlingPins;
import bowling.vo.GameUser;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static GameUser askUserName() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return new GameUser(SCANNER.nextLine());
    }

    public static BowlingPins askPins(int frameOrder) {
        System.out.printf("%d 프레임 투구: ", frameOrder);
        String pins = SCANNER.nextLine();
        return BowlingPins.of(Integer.parseInt(pins));
    }
}
