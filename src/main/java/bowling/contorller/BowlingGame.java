package bowling.contorller;

import bowling.domain.BowlingFrame;
import bowling.domain.BowlingNormalFrame;
import bowling.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private List<BowlingFrame> bowlingFrameList;

    public BowlingGame(List<BowlingFrame> bowlingFrameList) {
        this.bowlingFrameList = bowlingFrameList;
    }

    public static BowlingGame of() {
        List<BowlingFrame> bowlingFrameList = new ArrayList<>();
        bowlingFrameList.add(BowlingNormalFrame.first());
        return new BowlingGame(bowlingFrameList);
    }

    public int round() {
        return bowlingFrameList.size();
    }

    private BowlingFrame getFrame() {
        return bowlingFrameList.get(bowlingFrameList.size() - 1);
    }

    public BowlingFrame firstPitching(Point point) {
        BowlingFrame bowlingFrame = getFrame().firstPitching(point);
        bowlingFrameList.set(round() - 1, bowlingFrame);
        return bowlingFrame;
    }

    public BowlingFrame secondPitching(Point point) {
        BowlingFrame bowlingFrame = getFrame().secondPitching(point);
        bowlingFrameList.set(round() - 1, bowlingFrame);
        bowlingFrameList.add(bowlingFrame.nextFrame());
        return getFrame().secondPitching(point);
    }
}
