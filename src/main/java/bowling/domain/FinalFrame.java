package bowling.domain;

public class FinalFrame extends Frame {

    public FinalFrame(int score) {
        super(score);
    }

    public void addScore(int score) {
        validate(score);
        scores.add(score);
    }

    public String scoreToSymbol() {
        if (scores.size() <= 2) {
            return super.scoreToSymbol();
        }
        int firstScore = scores.get(0);
        int secondScore = scores.get(1);
        int thirdScore = scores.get(2);

        return changeScoreToSpareInFinalFrame(firstScore, secondScore, thirdScore);
    }

    private String changeScoreToSpareInFinalFrame(int firstScore, int secondScore, int thirdScore) {
        if (TEN_SCORE != firstScore && firstScore + secondScore == TEN_SCORE) {
            return changeScoreToSpare(firstScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(thirdScore);
        }
        if (secondScore != TEN_SCORE && secondScore + thirdScore == TEN_SCORE) {
            return changeScoreToSymbol(firstScore) + SEPARATOR_SYMBOL + changeScoreToSpare(secondScore);
        }
        return changeScoreToSymbol(firstScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(secondScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(thirdScore);
    }

    @Override
    public boolean isNext() {
        return scores.size() == 3 || (scores.size() == 2 && sumOfScore() < 10);
    }
}
