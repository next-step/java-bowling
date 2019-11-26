package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {
    private String name;
    private Frames frames;

    public Player(String name) {
        this.name = name;
        this.frames = new Frames();
    }

    public String getName() {
        return name;
    }

    public Frame frameByIndex(int index) {
        return frames.frameByIndex(index);
    }

    public boolean isFallDownAble(int i) {
        return frames.isFallDownAble(i);
    }

    public void fallDown(int index, int pinCount) {
        frames.fallDown(index, pinCount);
    }

    public List<Integer> getScore() {
        return IntStream.range(0, FrameNumber.LAST_FRAME)
                .mapToObj(i -> frames.getScore(i).getScore())
                .collect(Collectors.toList());
    }
}
