package bowling.domain.bowl;

import bowling.domain.score.Score;

public class Gutter extends Ended{

    private static final int GUTTER_VALUE = 0;

    @Override
    public String toString(){
        return "[Gutter]";
    }

    @Override
    public String getSymbol() {
        return "-|-";
    }

    @Override
    public Score score() {
        return Score.gutter();
    }

    @Override
    public Score calculateScore(Score before) {
        return before.addValue(GUTTER_VALUE);
    }
}
