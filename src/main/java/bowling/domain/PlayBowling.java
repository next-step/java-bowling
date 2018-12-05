package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.frame.Records;
import bowling.domain.score.BowlingScores;

import static bowling.utils.BowlingConstants.ONE;

public class PlayBowling {

    private Player player;
    private Frame frame;

    public PlayBowling(Player player) {
        this.player = player;
        this.frame = Frame.generateNextFrame(ONE);
    }

    public Records playBowling(Pin pin) {
        Records records = this.frame.getRecords();
        this.frame = player.rollBall(pin, this.frame);
        return records;
    }

    public boolean isFinished() {
        return player.isPlayerFinished(this.frame);
    }

    public int playingFrameNumber() {
        return this.frame.getCurrFrame();
    }

    public BowlingScores scores() {
        return new BowlingScores(Frames.getFrames());
    }
}
