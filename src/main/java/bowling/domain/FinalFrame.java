package bowling.domain;

public class FinalFrame extends Frame {

    public FinalFrame(int score) {
        super(score);
    }

    public String toScoreSymbol() {
        if (scores.size() <= 2) {
            return super.toScoreSymbol();
        }

        int firstScore = scores.get(0);
        int secondScore = scores.get(1);
        int thirdScore = scores.get(2);

        return changeScoreToSpareInFinalFrame(firstScore, secondScore, thirdScore);
    }

    private String changeScoreToSpareInFinalFrame(int firstScore, int secondScore, int thirdScore) {
        if (TEN_SCORE != firstScore && firstScore + secondScore == 10) {
            return changeScoreToSpare(firstScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(thirdScore);
        }

        if (secondScore != TEN_SCORE && secondScore + thirdScore == 10) {
            return changeScoreToSymbol(firstScore) + SEPARATOR_SYMBOL + changeScoreToSpare(secondScore);
        }
        return changeScoreToSymbol(firstScore) + SEPARATOR_SYMBOL + changeScoreToSymbol(secondScore)
                + SEPARATOR_SYMBOL + changeScoreToSymbol(thirdScore);
    }


    @Override
    public boolean isNext() {
        return scores.size() == 3 || (scores.size() == 2 && scores.stream().mapToInt(Integer::intValue).sum() < 10);
    }
}
