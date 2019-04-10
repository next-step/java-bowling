package com.hyoj.bowling.console;

import com.hyoj.bowling.domain.GameResult;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputConsole {
    public static int TEXT_SIZE = 6;
    public static String BAR = "|";

    public static final List<String> FRAME_TITLES = new ArrayList<>(
            Arrays.asList("NAME", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10")
    );

    public static void printFrameTitles() {
        System.out.print(BAR);
        FRAME_TITLES.forEach(title -> System.out.print(printWithBar(title)));
    }

    public static void printFrameResults(final String playerName, final GameResult gameResult) {
        System.out.print(BAR + printWithBar(playerName));
        System.out.print(gameResult);
    }

    private static String printWithBar(String input) {
        return StringUtils.center(input, TEXT_SIZE) + BAR;
    }
}
