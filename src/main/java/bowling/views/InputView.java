package bowling.views;

import java.util.Scanner;

public final class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String name() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public static int knockedPinsCount(final int currentFrameIndex) {
        System.out.printf("%d프레임 투구: ", currentFrameIndex);
        final int result = scanner.nextInt();
        clearInputBuffer();
        return result;
    }

    private static void clearInputBuffer() {
        scanner.nextLine();
    }
}
