package com.hyoj.bowling.domain;

import java.util.function.Consumer;
import java.util.function.Function;

public class GameExecutor {
    private final GameBoard gameBoard;

    public GameExecutor(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void play(Function<Integer, Integer> pinsSupplier,
                     Consumer<GameBoard> boardPrinter) {

        for (int i = 0; i < GameBoard.MAX_FRAMES_COUNT ; i++) {
            gameBoard.play(pinsSupplier, boardPrinter, i);
        }
    }
}