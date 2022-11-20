package bowling.view;

import bowling.domain.pin.FallenPins;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {

    }

    public static String getPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
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
        String numberOfFallenPin = scanner.nextLine();
        return FallenPins.of(Integer.parseInt(numberOfFallenPin));
    }

}
