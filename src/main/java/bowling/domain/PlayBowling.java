package bowling.domain;

import java.util.Scanner;

import bowling.view.ResultView;

public class PlayBowling {
    private static final int MAX_PINS = 10;
    private static final int MIN_PINS = 0;

    private static PlayBowling playBowling = new PlayBowling();
    private static ResultView resultView = ResultView.getResultView();
    private Scanner scanner = new Scanner(System.in);
    private boolean bonusFlag = false;

    public int inputScore(NormalFrame frame) {
        System.out.print(frame.getFrameNum() + "프레임 투구 : ");
        int inputPin = scanner.nextInt();
        validatePinRange(inputPin);
        return inputPin;
    }

    public void validatePinRange(int inputPin) {
        if (inputPin > MAX_PINS) {
            throw new IllegalArgumentException("쓰러 트릴 수 있는 핀은 10개를 넘을 수 없습니다.");
        }

        if (inputPin < MIN_PINS) {
            throw new IllegalArgumentException("쓰러 트릴 수 있는 핀은 0미만이 될 수 없습니다.");
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
            setScore(finalFrame);
            resultView.printPlayFrame(game);
        }
    }

    private void setGameScore(Game game, NormalFrame normalFrame) {
        int firstScore = getFirstScore(game, normalFrame);
        resultView.printPlayFrame(game);
        setSecondScore(game, normalFrame, firstScore);
    }

    private int getFirstScore(Game game, NormalFrame normalFrame) {
        int firstScore = setScore(normalFrame);
        game.addFrame(normalFrame);
        return firstScore;
    }

    private void setSecondScore(Game game, NormalFrame normalFrame, int firstScore) {
        int remainPin = MAX_PINS - firstScore;
        if (remainPin > 0) {
            int secondScore = setScore(normalFrame);
            resultView.printPlayFrame(game);
        }
    }

    private int setScore(NormalFrame normalFrame) {
        int firstScore = inputScore(normalFrame);
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
