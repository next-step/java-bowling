package bowling.domain;

import bowling.domain.frameScore.FrameScore;

import java.util.List;

public class FinalFrame implements Frame {
    private static final int SHOT_LIMIT = 3;

    private final ShotScores shotScores;

    private FinalFrame(ShotScores shotScores) {
        this.shotScores = shotScores;
    }

    public static FinalFrame of() {
        return new FinalFrame(ShotScores.of());
    }

    @Override
    public Frame last() {
        throw new UnsupportedOperationException("FinalFrame can not get last");
    }

    @Override
    public Frame next() {
        throw new UnsupportedOperationException("FinalFrame can not get next");
    }

    @Override
    public boolean isFrameSet() {
        return shotScores.hasSize(SHOT_LIMIT) ||
                (shotScores.hasSize(2) && !shotScores.hasClear());
    }

    @Override
    public List<ShotScore> shotScores() {
        return shotScores.shotScores();
    }

    @Override
    public FrameScore getFrameScore() {
        return new FrameScore() {
            @Override
            public boolean isCalculated() {
                return isFrameSet();
            }

            @Override
            public int getScore() {
                return shotScores.totalScore();
            }
        };
    }

    @Override
    public boolean isScoreCalculated() {
        return isFrameSet();
    }

    @Override
    public void shot(int shot) {
        if (isScoreCalculated()) {
            throw new IllegalStateException(String.format("shot Frame fail. this FinalFrame already calculated instance=%s", this));
        }

        shotScores.add(shotScores.getNext(shot));
    }

    @Override
    public String toString() {
        return "FinalFrame{" +
                "shotScores=" + shotScores +
                '}';
    }
}
