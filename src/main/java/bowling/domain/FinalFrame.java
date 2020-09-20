package bowling.domain;

public class FinalFrame {
    private Score finalScore;
    private NormalFrame normalFrame;

    private FinalFrame(NormalFrame normalFrame, Score finalScore) {
        this.normalFrame = normalFrame;
        this.finalScore = finalScore;
    }

    public static FinalFrame init() {
        return new FinalFrame(NormalFrame.init(), Score.NONE);
    }

    public boolean possibleFinalScore() {
        if (normalFrame.getFirstScore().equals(Score.STRIKE) || normalFrame.getSecondScore().equals(SecondScore.SPARE)) {
            return true;
        }
        return false;
    }

    public NormalFrame getNormalFrame() {
        return normalFrame;
    }

    public Score getFinalScore() {
        return finalScore;
    }

    public int hit(int hit, int currentFrame) {
        if (possibleFinalScore()) {
            return finalScore(hit, currentFrame);
        }
        int result = normalFrame.hit(hit, currentFrame);
        if (possibleFinalScore()) {
            --result;
        }
        return result;
    }


    public int finalScore(int hit, int result) {
        checkValidationScore(hit);
        this.finalScore = Score.valueOf(hit);
        return ++result;
    }

    private static void checkValidationScore(int hit) {
        if (hit < NormalFrame.MIN_SCORE || hit > NormalFrame.MAX_SCORE) {
            throw new IllegalArgumentException("0에서 10 사이의 값을 입력해주세요.");
        }
    }

}
