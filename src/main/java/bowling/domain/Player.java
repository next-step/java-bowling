package bowling.domain;

import java.util.Objects;

public class Player {
    private static final int MAX_LENGTH = 3;

    private final String name;
    private final Frames frames;

    public Player(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 3글자 영문으로 입력하세요.");
        }
        this.name = name;
        this.frames = new Frames();
    }

    public Frames pitch(int frameNo, int point) {
        frames.pitch(frameNo, point);
        return frames;
    }

    public boolean isFrameContinue(int frameNo) {
        if (!frames.contains(frameNo)) {
            return true;
        }
        return frames.get(frameNo).isContinue2();
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player1 = (Player) o;
        return Objects.equals(name, player1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
