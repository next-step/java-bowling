package com.hyoj.bowling.console;

import com.hyoj.bowling.domain.GameBoard;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputConsole {
    public static int TEXT_SIZE = 7;
    public static String BAR = "|";

    public static final List<String> FRAME_TITLES = new ArrayList<>(
            Arrays.asList("NAME", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10")
    );

    public static void printGameBoard(final GameBoard gameBoard) {
        System.out.print(BAR);
        FRAME_TITLES.forEach(title -> System.out.print(toStringWithBar(title)));
        System.out.println();
        System.out.print(gameBoard);
        System.out.println();
    }

    public static String toStringWithBar(final String input) {
        return StringUtils.center(input, TEXT_SIZE) + BAR;
    }
}
