package com.hyoj.bowling.domain;

import com.hyoj.bowling.console.OutputConsole;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class GameBoard {
    public static final int MAX_FRAMES_COUNT = 10;

    private final String playerName;
    private final List<Frame> frames;

    private GameBoard(String playerName, List<Frame> frames) {
        if (playerName.length() != 3) {
            throw new IllegalArgumentException("플레이어 이름은 3글자 이어야 함");
        }

        this.playerName = playerName;
        this.frames = frames;
    }

    public boolean add(final Frame frame) {
        if (frames.size() >= MAX_FRAMES_COUNT) {
            throw new IllegalArgumentException("프레임 수 초과");
        }

        return frames.add(frame);
    }

    public static GameBoard init(String playerName) {
        List<Frame> frames = new ArrayList<>();

        final DefaultFrame firstFrame = (DefaultFrame) DefaultFrame.createFirstFrame();
        frames.add(firstFrame);

        for (int i = 1; i < MAX_FRAMES_COUNT; i++) {
            DefaultFrame frame = (DefaultFrame) frames.get(i - 1);
            frames.add(frame.createNextFrame());
        }

        return new GameBoard(playerName, frames);
    }

    public GameBoard play(final Function<Integer, Integer> pinsSupplier,
                          final Consumer<GameBoard> boardPrinter,
                          int index) {
        final int frameTitleIndex = index + 1;
        final Frame frame = frames.get(index);

        do {
            frame.add(pinsSupplier.apply(frameTitleIndex));
            boardPrinter.accept(this);
        } while (!frame.isDone());

        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(
                OutputConsole.BAR + OutputConsole.toStringWithBar(playerName));

        frames.forEach(frame -> result.append(OutputConsole.toStringWithBar(frame.toString())));

        return result.toString();
    }
}
