package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.score.Pin;
import bowling.state.BowlingState;

import java.util.List;

/**
 * Created By mand2 on 2020-12-21.
 */
public class Bowling {

    private final Name playerName;
    private final Frames frames;

    private Bowling(Name playerName) {
        this(playerName, Frames.init());
    }

    private Bowling(Name playerName, Frames frames) {
        this.playerName = playerName;
        this.frames = frames;
    }

    public static Bowling of(Name playerName) {
        return new Bowling(playerName);
    }

    public String getPlayerName() {
        return playerName.getName();
    }

    public void pitch(Pin knockDownPin) {
        frames.bowling(knockDownPin);
    }

    public boolean isGameEnd() {
        return frames.isFrameEnd();
    }

    public int currentIndex() {
        return this.frames.getCurrentIndex();
    }

    public List<BowlingState> getState() {
        return this.frames.getState();
    }
}
