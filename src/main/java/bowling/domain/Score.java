package bowling.domain;

public class Score {

    private int countOfHit;
    private int countOfRemain;

    public Score(int countOfHit, int countOfRemain) {
        this.countOfHit = countOfHit;
        this.countOfRemain = countOfRemain;
    }

    public String getScore() {
        if (countOfHit == 10 && countOfRemain == 1) {
            return " X |";
        }

        if (countOfHit == 10 && countOfRemain == 0) {
            return "|/"+" |";
        }

        if (countOfHit == 0 && countOfRemain == 0) {
            return "|-"+" |";
        }

        if (countOfHit == 0 && countOfRemain == 1) {
            return " -";
        }

        if (countOfRemain == 0) {
            return "|"+String.valueOf(countOfHit)+" |";
        }

        return " "+String.valueOf(countOfHit);
    }
}
