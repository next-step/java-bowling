package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Player {

    private static final String PLAYER_NAME_LENGTH = "플레이어 이름은 최대 영문3 글자 입니다.";

    private String name;

    private Frames frames;

    public Player(String name) {
        validationUserName(name);
        this.name = name;
        this.frames = new Frames();
    }

    private void validationUserName(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH);
        }
    }

    public boolean isEndGame() {
        return frames.isEndAllFrame();
    }

    public void bowl(Function<String, String> rollingBall) {
        int countOfPins = Integer.parseInt(rollingBall.apply(name));
        Pin pin = new Pin(countOfPins);
        frames.play(pin);
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames.getFrames());
    }

    public int frameSize() {
        return frames.size();
    }

    public int getLastFrameIndex() {
        return frames.currentFrameIndex();
    }

    public String name() {
        return name;
    }

    public boolean isSameFrameIndex(int frameIndex) {
        return getLastFrameIndex() == frameIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
