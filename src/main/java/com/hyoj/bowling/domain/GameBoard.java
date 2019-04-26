package com.hyoj.bowling.domain;

import com.hyoj.bowling.console.OutputConsole;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;

public class GameBoard {
    public static final int MAX_FRAMES_COUNT = 10;

    private final String playerName;
    private final Frames frames;

    private GameBoard(final String playerName, final Frames frames) {
        if (!Pattern.matches("^[a-zA-Z]{3}$", playerName)) {
            throw new IllegalArgumentException("플레이어 이름은 영어로 3글자 이어야 함");
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
        Frames frames = new Frames();

        final DefaultFrame firstFrame = (DefaultFrame) DefaultFrame.createFirstFrame();
        frames.add(firstFrame);

        for (int i = 1; i < MAX_FRAMES_COUNT; i++) {
            DefaultFrame frame = (DefaultFrame) frames.get(i - 1);
            frames.add(frame.createNextFrame());
        }

        return new GameBoard(playerName, frames);
    }

    public GameBoard play(final Function<Integer, Pins> pinsSupplier,
                          final Consumer<GameBoard> boardPrinter,
                          int index) {
        final int frameTitleIndex = index + 1;
        final Frame frame = frames.get(index);

        do {
            frame.throwBowlingBall(pinsSupplier.apply(frameTitleIndex));
            boardPrinter.accept(this);
        } while (!frame.isDone());

        return this;
    }

    @Override
    public String toString() {
        return OutputConsole.BAR + OutputConsole.toStringWithBar(playerName) + frames.toString();
    }
}
