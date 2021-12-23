package bowling;

import java.util.List;

public class Player {
    public static final String MAX_LENGTH_MESSAGE = "이름은 1~3글자입니다.";

    private static final int MAX_LENGTH = 3;

    private final String name;
    private final Frames frames = new Frames();

    public Player(String name) {
        if (name == null || name.trim().isEmpty() || name.trim().length() > MAX_LENGTH) {
            throw new IllegalArgumentException(MAX_LENGTH_MESSAGE);
        }
        this.name = name.trim();
    }

    public boolean isEndGame() {
        return frames.isEnd();
    }

    public void prepareFrame() {
        frames.prepareFrame();
    }

    public boolean isNotCurrentFrameEnd() {
        return frames.isNotCurrentFrameEnd();
    }

    public void bowl(int knockedOutCount) {
        frames.bowl(knockedOutCount);
    }

    public String name() {
        return name;
    }

    public List<Frame> frames() {
        return frames.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player player = (Player) o;

        return name != null ? name.equals(player.name) : player.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
