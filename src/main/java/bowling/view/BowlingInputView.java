package bowling.view;

import bowling.domain.Player;

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
            System.out.printf("플레이어 %d의 이름은(3 english letters)?: ", index);
            return SCANNER.next();
        };
    }

    public static ToIntFunction<Player> countOfPins() {
        return (player) -> {
            System.out.printf("%s's turn : ", player.getName());
            return SCANNER.nextInt();
        };
    }
}
