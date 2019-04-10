package com.hyoj.bowling;

import com.hyoj.bowling.console.InputConsole;
import com.hyoj.bowling.console.OutputConsole;

public class BowlingConsoleApp {
    public static void main(String[] args) {
        final String playerName = InputConsole.enterPlayerName();

        OutputConsole.printFrameTitles();

    }
}
