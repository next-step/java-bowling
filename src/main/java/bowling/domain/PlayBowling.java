package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.Records;

import static bowling.utils.BowlingConstants.ONE;

public class PlayBowling {

    private Player player;
    private Frame frame;

    public PlayBowling(Player player) {
        this.player = player;
        this.frame = Frame.generateNextFrame(ONE);
    }

    public Records playBowling(Pin pin) {
        int frameNum = this.frame.getCurrFrame();
        this.frame = player.rollBall(pin, this.frame);
        return player.findFrameByFrameNumber(frameNum).getRecords();
    }

    public boolean isFinished() {
        return player.isPlayerFinished();
    }

    public int playingFrameNumber() {
        return this.frame.getCurrFrame();
    }
}
