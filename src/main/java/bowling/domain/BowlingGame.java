package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BowlingGame {
    private final Player player;
    private final List<Frame> frames;

    public BowlingGame(String playerName) {
        this.player = new Player(playerName);
        this.frames = new ArrayList<>();
    }

    public List<Frame> pitch(int count) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
