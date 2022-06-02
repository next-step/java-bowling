package bowling.domain;

import bowling.engine.ScoreStrategy;

public class FinalFrame implements bowling.engine.FinalFrame {
    private static final int TEN = 10;
    private static final int ZERO = 0;

    private final NormalFrame normalFrame;
    private final int Bonus;

    public FinalFrame(ScoreStrategy scoreStrategy) {
        int first = scoreStrategy.createFirst();
        int second = scoreStrategy.createSecond(first);
        this.normalFrame = NormalFrame.firstWithFactor(first, second);
        this.Bonus = selectBonus(first + second, scoreStrategy);
    }

    public FinalFrame(NormalFrame normalFrame, int bonus) {
        this.normalFrame = normalFrame;
        Bonus = bonus;
    }

    public NormalFrame getNormalFrame() {
        return normalFrame;
    }

    public int getBonus() {
        return Bonus;
    }

    @Override
    public int selectBonus(int sum, ScoreStrategy scoreStrategy) {
        if (sum == TEN) {
            return scoreStrategy.createFirst();
        }
        return ZERO;
    }
}
