package bowling.domain;

public class NormalFrame {

    public static final int MIN_SCORE = 0;
    public static final int MAX_SCORE = Score.STRIKE_VALUE;

    private Score firstScore;
    private SecondScore secondScore;
    private boolean possibleSecondHit = true;

    private NormalFrame(Score firstScore, SecondScore secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
    }

    public static NormalFrame of(Score firstScore) {
        return new NormalFrame(firstScore, SecondScore.NONE);
    }

    public static NormalFrame init() {
        return new NormalFrame(Score.NONE, SecondScore.NONE);
    }

    public void firstScore(int hit) {
        checkValidationScore(hit);
        this.firstScore = Score.valueOf(hit);
        if (hit == MAX_SCORE) {
            possibleSecondHit = false;
        }
    }

    private static void checkValidationScore(int hit) {
        if (hit < MIN_SCORE || hit > MAX_SCORE) {
            throw new IllegalArgumentException("0에서 10 사이의 값을 입력해주세요.");
        }
    }

    public void secondScore(SecondScore secondScore) {
        checkValidationTotalScore(secondScore.getHit());
        this.secondScore = secondScore;
    }

    private void checkValidationTotalScore(int hit) {
        checkValidationScore(hit);
        int totalScore = firstScore.getHit() + hit;
        if (totalScore < MIN_SCORE || totalScore > MAX_SCORE) {
            throw new IllegalArgumentException("한 프레임에서 10개 이상 치실 수 없습니다.");
        }
    }

    public NormalFrame secondScore(int hit) {
        checkValidationTotalScore(hit);
        this.secondScore = SecondScore.valueOf(firstScore.getHit(), hit);
        return new NormalFrame(firstScore, secondScore);
    }

    public boolean isPossibleSecondHit() {
        return possibleSecondHit;
    }

    public Score getFirstScore() {
        return firstScore;
    }

    public SecondScore getSecondScore() {
        return secondScore;
    }

    public int hit(int hit, int currentFrame) {
        if (firstScore.equals(Score.NONE)) {
            return hitFirst(hit, currentFrame);
        }
        secondScore(hit);
        ++currentFrame;
        return currentFrame;
    }

    public int hitFirst(int hit, int currentFrame) {
        firstScore(hit);
        if (!isPossibleSecondHit()) {
            ++currentFrame;
        }
        return currentFrame;
    }
}
