package bowling.entity.frame;

import bowling.FrameResult;
import bowling.entity.BowlingBoard;
import bowling.entity.Pin;
import bowling.entity.score.None;
import bowling.entity.score.ScoreType;
import bowling.entity.score.Spare;
import bowling.entity.score.Strike;

public class LastFrame implements Frame {
    private static final int DEFAULT_BOWL_COUNT = 2;
    private static final int MAX_BOWL_COUNT = 3;

    private ScoreType scoreType;
    private int maxBowlCount;
    private int currentBowlCount;

    public LastFrame() {
        this.scoreType = new None();
        this.maxBowlCount = DEFAULT_BOWL_COUNT;
        this.currentBowlCount = 0;
    }

    @Override
    public int frameNo() {
        return 10;
    }

    @Override
    public Frame pinResult(Pin fallenPin) {
        scoreType = scoreType.pinResult(fallenPin);

        if (scoreType instanceof Strike || scoreType instanceof Spare) {
            maxBowlCount = MAX_BOWL_COUNT;
        }
        currentBowlCount++;
        return this;
    }

    @Override
    public void addFrameResult(BowlingBoard bowlingBoard) {
        if (!(scoreType instanceof None)) {
            bowlingBoard.addResult(new FrameResult(scoreType.scoreResult()));
        }
    }

    @Override
    public BowlingBoard bowlingBoard() {
        BowlingBoard bowlingBoard = new BowlingBoard();
        addFrameResult(bowlingBoard);
        return bowlingBoard;
    }

    @Override
    public boolean bowlingGameEnd() {
        return MAX_BOWL_COUNT == currentBowlCount;
    }
}
