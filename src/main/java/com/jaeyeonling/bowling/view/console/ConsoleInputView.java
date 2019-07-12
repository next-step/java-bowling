package com.jaeyeonling.bowling.view.console;

import com.jaeyeonling.bowling.domain.user.Username;
import com.jaeyeonling.bowling.view.Visualizable;

import java.util.Scanner;

public final class ConsoleInputView {

    private static final String READ_USER_MESSAGE = String.format("플레이어 이름은(%d english letters)? ", Username.LENGTH);
    private static final String READ_KNOCKDOWN_PINS_MESSAGE = "%s 프레임 투구 : ";

    private static final Scanner CONSOLE = new Scanner(System.in);

    private ConsoleInputView() { }

    public static String readUsername() {
        return readStringWithMessage(READ_USER_MESSAGE);
    }

    public static int readKnockdownPins(final Visualizable<?> visualizer) {
        return readIntWithMessage(String.format(READ_KNOCKDOWN_PINS_MESSAGE, visualizer.visualize()));
    }

    private static String readStringWithMessage(final String message) {
        ConsoleOutputView.print(message);
        return readString();
    }

    private static int readIntWithMessage(final String message) {
        ConsoleOutputView.print(message);
        return readInt();
    }

    private static String readString() {
        return CONSOLE.nextLine();
    }

    private static int readInt() {
        final int readValue = CONSOLE.nextInt();
        CONSOLE.nextLine();

        return readValue;
    }
}
