package bowling.domain;

public class FinalFrame extends Frame {
    private static int MAX_COUNT = 3;

    public FinalFrame(int score) {
        super(score);
    }

    public String frameScoreToSymbolString() {
        if (scores.size() <= 2) {
            return super.frameScoreToSymbolString();
        }
        return changeScoreToSpareInFinalFrame(scores.get(0), scores.get(1), scores.get(2));
    }

    private String changeScoreToSpareInFinalFrame(Score firstScore, Score secondScore, Score thirdScore) {
        if (firstScore.compareTo(Score.valueOf(Score.TEN_SCORE)) != 0 && firstScore.sum(secondScore) == Score.TEN_SCORE) {
            return firstScore.changeScoreToSpare() + SEPARATOR_SYMBOL + thirdScore.changeScoreToSymbol();
        }
        if (secondScore.compareTo(Score.valueOf(Score.TEN_SCORE)) != 0 && secondScore.sum(thirdScore) == Score.TEN_SCORE) {
            return firstScore.changeScoreToSymbol() + SEPARATOR_SYMBOL + secondScore.changeScoreToSpare();
        }
        return firstScore.changeScoreToSymbol() + SEPARATOR_SYMBOL + secondScore.changeScoreToSymbol() + SEPARATOR_SYMBOL + thirdScore.changeScoreToSymbol();
    }

    @Override
    public boolean isNext() {
        return scores.size() == MAX_COUNT || (scores.size() == 2 && Score.sumOfScores(scores) < Score.TEN_SCORE);
    }
}
