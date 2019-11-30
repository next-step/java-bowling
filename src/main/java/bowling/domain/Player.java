package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {
    private static final String NAME_LENGTH_MESSAGE = "이름은 3글자까지만 가능합니다.";
    private static final int NAME_LENGTH = 3;
    private String name;
    private Frames frames;

    public Player(String name) {
        if (name.length() > NAME_LENGTH) {
            throw new IllegalArgumentException(NAME_LENGTH_MESSAGE);
        }
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

    public List<Frame> getFrames() {
        return frames.unmodifiableFrames();
    }
}
