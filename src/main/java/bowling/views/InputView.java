package bowling.views;

import bowling.domain.Player;

import java.util.Scanner;

public final class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int playersCount() {
        System.out.print("How many people? ");
        final int result = scanner.nextInt();
        clearInputBuffer();
        return result;
    }

    public static String name() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static String name(int number) {
        System.out.printf("플레이어 %d의 이름은(3 english letters)?: ", number);
        return scanner.nextLine();
    }

    public static int knockedPinsCount(final int currentFrameIndex) {
        System.out.printf("%d프레임 투구: ", currentFrameIndex);
        final int result = scanner.nextInt();
        clearInputBuffer();
        return result;
    }

    public static int knockedPinsCount(Player player) {
        System.out.printf("%s's turn: ", player.name());
        final int result = scanner.nextInt();
        clearInputBuffer();
        return result;
    }

    private static void clearInputBuffer() {
        scanner.nextLine();
    }
}
