package com.hyoj.bowling;

import com.hyoj.bowling.console.InputConsole;
import com.hyoj.bowling.console.OutputConsole;
import com.hyoj.bowling.domain.GameExecutor;
import com.hyoj.bowling.domain.GameBoard;

public class BowlingConsoleApp {
    public static void main(String[] args) {
        final String playerName = InputConsole.enterPlayerName();
        final GameExecutor gameExecutor = new GameExecutor(GameBoard.init(playerName));

        gameExecutor.play(
                InputConsole::enterKnockDownPinsCount,
                OutputConsole::printGameBoard);
    }
}
