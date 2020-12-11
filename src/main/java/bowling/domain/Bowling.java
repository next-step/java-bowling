package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.score.Score;

import java.util.List;

public class Bowling {
    private Frames frames;

    private Bowling(Frames frames) {
        this.frames = frames;
    }

    public static Bowling of(Player player) {
        Frames frames = Frames.init(player);

        return new Bowling(frames);
    }

    public boolean isNotEnd() {
        return frames.isNotEnd();
    }

    public int getFrameNumber() {
        return frames.getLastNumber();
    }

    public void bowl(Score score) {
        frames.bowl(score);
    }

    public String getPlayerName() {
        return frames.getPlayerName();
    }

    public List<Score> getResult(int frameNumber) {
        return frames.getResult(frameNumber);
    }

    public Score getTotalScore(int frameNumber) {
        return frames.getTotalScore(frameNumber);
    }
}
