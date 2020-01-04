package bowling.domain.player;

import bowling.domain.FrameResults;
import bowling.domain.set.FrameSet;
import bowling.domain.set.FrameSets;

import java.util.Objects;

public class Player {

    public static final int MIN_NAME_LENGTH = 3;

    private final String name;
    private final FrameSets frameSets;

    public static Player create(String name) {
        return new Player(name);
    }

    private Player(String name) {
        assertNameLength(name);
        this.name = name;
        this.frameSets = FrameSets.create();
    }

    public String getName() {
        return name;
    }

    public int getPlayCount() {
        return frameSets.getCurrentPlayCount();
    }

    public void play(int hitCount) {
        frameSets.play(hitCount);
    }

    public FrameResults getCurrentResult() {
        return frameSets.getCurrentFrameResults();
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

    private void assertNameLength(String name) {
        if (name == null || name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("플레이어 이름은 최소 3글자 이상이여야 합니다.");
        }
    }
}
