package bowling.view;

import bowling.domain.FallenPins;
import bowling.domain.Player;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static Player getPlayer() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return new Player(scanner.nextLine());
    }

    public static FallenPins getFallenPins(int numberOfFrame) {
        FallenPins fallenPins = null;
        while (fallenPins == null) {
            System.out.printf("%d프레임 투구 : ", numberOfFrame);
            fallenPins = getFallenPins();
        }
        return fallenPins;
    }

    private static FallenPins getFallenPins() {
        try {
            String numberOfFallenPin = scanner.nextLine();
            return FallenPins.of(Integer.parseInt(numberOfFallenPin));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("입력값을 확인하고 다시 입력해주세요.");
            return null;
        }
    }

}
