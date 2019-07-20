package com.jaeyeonling.bowling.view;

import com.jaeyeonling.bowling.domain.user.User;
import com.jaeyeonling.bowling.domain.user.Username;

import java.util.Scanner;

public final class ConsoleInputView {

    private static final String READ_COUNT_OF_USER_MESSAGE = "How many people?";
    private static final String READ_USER_MESSAGE = "플레이어 %d의 이름은?(" + Username.LENGTH + " english letters): ";
    private static final String READ_KNOCKDOWN_PINS_MESSAGE = "%s's turn :";

    private static final Scanner CONSOLE = new Scanner(System.in);

    private ConsoleInputView() { }

    public static int readCountOfUser() {
        return readIntWithMessage(READ_COUNT_OF_USER_MESSAGE);
    }

    public static String readUsername(final int count) {
        return readStringWithMessage(String.format(READ_USER_MESSAGE, count));
    }

    public static int readKnockdownPins(final User user) {
        return readIntWithMessage(String.format(READ_KNOCKDOWN_PINS_MESSAGE, user.getUsername().getUsername()));
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
