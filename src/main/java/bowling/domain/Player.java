package bowling.domain;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Player {

    private final String PLAYER_NAME_LENGTH = "플레이어 이름은 최대 영문3 글자 입니다.";

    private String name;

    private Frames frames;

    public Player(String name) {
        this.name = name;
        this.frames = new Frames();
        if (name.length() > 3) {
            throw new IllegalArgumentException(PLAYER_NAME_LENGTH);
        }
    }

    public boolean isEndGame() {
        return frames.allFrameEnd();
    }

    public void bowl(Function<String, String> rollingBall) {
        int countOfPins = Integer.parseInt(rollingBall.apply(frames.currentFrameIndex()));
        frames.play(new Pin(countOfPins));
    }

    public List<Frame> frames() {
        return frames.getFrames();
    }

    public int frameSize() {
        return frames.size();
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

    public String name() {
        return name;
    }
}
