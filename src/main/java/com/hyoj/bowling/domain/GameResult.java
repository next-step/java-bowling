package com.hyoj.bowling.domain;

import com.hyoj.bowling.console.OutputConsole;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

public class GameResult {
    public static final int MAX_FRAMES_COUNT = 10;

    private final String playerName;
    private final List<Frame> frames;

    public GameResult(String playerName) {
        this.playerName = playerName;
        this.frames = new ArrayList<>();
    }

    public boolean add(final Frame frame) {
        if (frames.size() >= MAX_FRAMES_COUNT) {
            throw new IllegalArgumentException("프레임 수 초과");
        }

        return frames.add(frame);
    }

    public boolean hasOneMoreShotAtFinalFrame() {
        return frames.get(MAX_FRAMES_COUNT - 1).canOneMoreShot();
    }

    public GameResult addFrame(Function<Integer, Integer> pinsSupplier, Consumer<GameResult> gameResultConsumer) {
        final Frame defaultFrame = new DefaultFrame();
        final int frameTitleIndex = frames.size() + 1;

        this.add(defaultFrame);

        do {
            defaultFrame.add(pinsSupplier.apply(frameTitleIndex));
            gameResultConsumer.accept(this);
        } while (!defaultFrame.isDone());

        return this;
    }

    public GameResult addFinalFrame(Function<Integer, Integer> pinsSupplier, Consumer<GameResult> gameResultConsumer) {
        addFrame(pinsSupplier, gameResultConsumer);

        final FinalFrame finalFrame = new FinalFrame(frames.get(MAX_FRAMES_COUNT - 1));

        if (hasOneMoreShotAtFinalFrame()) {
            finalFrame.add(pinsSupplier.apply(MAX_FRAMES_COUNT));
        }

        this.frames.set(MAX_FRAMES_COUNT - 1, finalFrame);

        gameResultConsumer.accept(this);

        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(
            OutputConsole.BAR + OutputConsole.toStringWithBar(playerName));

        frames.forEach(frame -> result.append(OutputConsole.toStringWithBar(frame.toString())));

        IntStream.range(frames.size(), MAX_FRAMES_COUNT)
            .forEach(i -> result.append(OutputConsole.toStringWithBar("")));

        return result.toString();
    }
}
