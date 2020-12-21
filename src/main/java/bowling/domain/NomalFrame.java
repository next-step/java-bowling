package bowling.domain;

public class NomalFrame {

    private BowlingKnockDown bowlingKnockDowns;

    public NomalFrame(BowlingKnockDown bowlingKnockDowns) {
        this.bowlingKnockDowns = bowlingKnockDowns;
    }

    public static NomalFrame of(int countOfBowlingKnockDown) {
        return new NomalFrame(new BowlingKnockDown(countOfBowlingKnockDown));
    }

    public static NomalFrame of(String currentKnockDownExpression, int countOfBowlingKnockDown) {
        return new NomalFrame(new BowlingKnockDown(currentKnockDownExpression, countOfBowlingKnockDown));
    }

    public BowlingKnockDown getBowlingKnockDowns() {
        return bowlingKnockDowns;
    }

    public String currentKnockDown() {
        return bowlingKnockDowns.getKnockDownExpression().trim();
    }
}
