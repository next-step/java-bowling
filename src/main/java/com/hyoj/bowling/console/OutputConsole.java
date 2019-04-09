package com.hyoj.bowling.console;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputConsole {
    public static int TEXT_SIZE = 6;
    public static final List<String> FRAME_TITLES = new ArrayList<>(
            Arrays.asList("NAME", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10")
    );

    public static void printFrameTitles() {
        FRAME_TITLES.forEach(title -> System.out.println(StringUtils.center(title, TEXT_SIZE)));
    }
}
