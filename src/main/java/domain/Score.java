package domain;

public class Score {
    private static final int STRIKE = 10;

    private int first;
    private int second;

    public Score() {
        this.first = -1;
        this.second = -1;
    }

    public int bowl(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("음수는 입력 할 수 없습니다.");
        }

        if (this.first == -1) {
            return firstBowl(score);
        }
        if (this.second == -1) {
            return secondBowl(score);
        }

        throw new IllegalStateException("한 프레임에 공을 세 번 이상 던질 수 없습니다.");
    }

    private int firstBowl(int score) {
        this.first = score;
        return sumScore();
    }

    private int secondBowl(int score) {
        if(first == STRIKE) {
            throw new IllegalStateException("초구가 10점이면 더이상 던질 수 없습니다.");
        }
        this.second = score;
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
