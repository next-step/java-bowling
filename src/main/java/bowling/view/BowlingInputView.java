package bowling.view;

import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BowlingInputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Supplier<Integer> countOfPlayer() {
        System.out.print("How many people? ");
        return SCANNER::nextInt;
    }

    public static IntFunction<String> playerName() {
        return (index) -> {
            System.out.print("플레이어 이름은(3 english letters)?: ");
            return SCANNER.next();
        };
    }

    public static ToIntFunction<Integer> countOfPins() {
        return (frameNumber) -> {
            System.out.printf("%d프레임 투구 : ", frameNumber);
            return SCANNER.nextInt();
        };
    }
}
