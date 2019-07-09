package domain;

import View.BowlingFrame;

import java.util.stream.IntStream;

import static domain.NormalFrame.NO_MORE_NEXT;

public class FinalFrame implements BowlingFrame {
    private FinalScore finalScore;

    public FinalFrame() {
        finalScore = new FinalScore();
    }

    @Override
    public boolean doBowling(int point) {
        return finalScore.bowl(point);
    }

    @Override
    public int sumScore() {
        if (!isGameOver()) {
            return NO_MORE_NEXT;
        }
        return finalScore.sumScore();
    }

    @Override
    public String getResult() {
        return finalScore.getResult();
    }

    public boolean isGameOver() {
        return !finalScore.nowBowlable();
    }

    public boolean isStart() {
        if (finalScore.getPointSize() == 0) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public int framePoint(int checkingCount) {
        int frameScore = IntStream.range(0, checkingCount)
                .boxed()
                .mapToInt(n -> finalScore.getPoint(n))
                .sum();
        return frameScore;
    }

    public int getPointSize() {
        return finalScore.getPointSize();
    }
}
