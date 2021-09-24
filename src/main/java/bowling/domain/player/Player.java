package bowling.domain.player;

import bowling.domain.frame.Pitch;
import bowling.domain.score.Scores;
import bowling.domain.frame.Frames;

import java.util.Objects;

public class Player {
    private final Name name;
    private final Frames frames;
    private final Scores scores;

    public Player(Name name) {
        this.name = name;
        this.frames = Frames.init();
        this.scores = Scores.init();
    }

    public boolean gameOver() {
        return frames.over();
    }

    public boolean turnOver() {
        return scores.turnOver();
    }

    public void bowl(Pitch pitch) {
        frames.recodePitch(pitch);
        scores.score(pitch);
    }

    public boolean isLastFrame() {
        return frames.isLastFrame();
    }

    public String name() {
        return name.getName();
    }

    public Frames getFrames() {
        return frames;
    }

    public Scores getScores() {
        return scores;
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
