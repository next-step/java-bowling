package bowling.domain;

import java.util.Random;

import bowling.view.ResultView;

public class PlayBowling {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;

    private static PlayBowling playBowling = new PlayBowling();
    private static ResultView resultView = ResultView.getResultView();
    private Random random = new Random();
    private boolean bonusFlag = false;

    public int generateBowlingScore(int rangePin) {
        validatePinRange(rangePin);
        return random.nextInt(rangePin);
    }

    public void validatePinRange(int rangePin) {
        if (rangePin > MAX_PINS) {
            throw new IllegalArgumentException("발생 할 수 있는 스코어는 10을 넘을 수 없습니다.");
        }

        if (rangePin < MIN_PINS) {
            throw new IllegalArgumentException("발생 할 수 있는 스코어는 0미만이 될 수 없습니다.");
        }
    }

    public void playFrame(Game game, NormalFrame normalFrame) {
        setGameScore(game, normalFrame);
        if (normalFrame.getFalledPins() == MAX_PINS) {
            bonusFlag = true;
        }
    }

    public void playFinalFrame(Game game, FinalFrame finalFrame) {
        setGameScore(game, finalFrame);
        if (finalFrame.getBonusCount() > 0) {
            getFirstScore(game, finalFrame);
        }
    }

    private void setGameScore(Game game, NormalFrame normalFrame) {
        int firstScore = getFirstScore(game, normalFrame);
        resultView.printPlayFrame(game, normalFrame, firstScore);
        setSecondScore(game, normalFrame, firstScore);
    }

    private int getFirstScore(Game game, NormalFrame normalFrame) {
        int firstScore = setScore(normalFrame, MAX_PINS);
        game.addFrame(normalFrame);
        return firstScore;
    }

    private void setSecondScore(Game game, NormalFrame normalFrame, int firstScore) {
        int remainPin = MAX_PINS - firstScore;
        if (remainPin > 0) {
            int secondScore = setScore(normalFrame, remainPin);
            resultView.printPlayFrame(game, normalFrame, secondScore);
        }
    }

    private int setScore(NormalFrame normalFrame, int value) {
        int firstScore = generateBowlingScore(value);
        normalFrame.bowl(firstScore);
        return firstScore;
    }

    public boolean isBonusFlag() {
        return bonusFlag;
    }

    public static PlayBowling getPlayBowling() {
        return playBowling;
    }
}
