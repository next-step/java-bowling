package domain;

public class Score {
    private static final int INITIAL_VALUE = -1;
    private static final int OVER_BOWL = -1;
    public static final int STRIKE = 10;

    private int first;
    private int second;

    public Score() {
        this.first = INITIAL_VALUE;
        this.second = INITIAL_VALUE;
    }

    public int bowl(int point) {
        if (this.first == INITIAL_VALUE) {
            return firstBowl(point);
        }
        if (this.second == INITIAL_VALUE) {
            return secondBowl(point);
        }

        return OVER_BOWL;
    }

    private int firstBowl(int point) {
        this.first = point;
        return sumScore();
    }

    private int secondBowl(int point) {
        if(first == STRIKE) {
            throw new IllegalStateException("초구가 10점이면 더이상 던질 수 없습니다.");
        }
        this.second = point;
        return sumScore();
    }

    private int sumScore() {
        int sum = first + (second == -1 ? 0 : second);
        if (sum > STRIKE) {
            throw new IllegalArgumentException("점수는 10점을 초과할 수 없습니다.");
        }
        return sum;
    }

    public boolean isStrike() {
        return first == STRIKE ? true : false;
    }

    public boolean isSpare() {
        if(first != STRIKE && sumScore() == STRIKE) {
            return true;
        }
        return false;
    }

    public String getScore() {
        final String SCORE_CONNECTOR = "|";

        if(isStrike()) {
            return CleanScore.valueOfSymbol(first, isStrike());
        }

        if(isSpare()) {
            return first +
                    SCORE_CONNECTOR +
                    CleanScore.valueOfSymbol(sumScore(), isStrike());
        }

        return OpenScore.valueOfScoreName(first) +
                SCORE_CONNECTOR +
                OpenScore.valueOfScoreName(second);
    }
}
