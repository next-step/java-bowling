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

    public int inputScore(Frame frame) {
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
        getFirstScore(game, finalFrame);
        resultView.printPlayFrame(game);
        scoreAndPrint(game, finalFrame);

        if (finalFrame.getBonusCount() > 0) {
            scoreAndPrint(game, finalFrame);
        }
    }

    private void scoreAndPrint(Game game, Frame normalFrame) {
        setScore(normalFrame);
        resultView.printPlayFrame(game);
    }

    private void setGameScore(Game game, Frame normalFrame) {
        int firstScore = getFirstScore(game, normalFrame);
        resultView.printPlayFrame(game);
        setSecondScore(game, normalFrame, firstScore);
    }

    private int getFirstScore(Game game, Frame frame) {
        int firstScore = setScore(frame);
        game.addFrame(frame);
        return firstScore;
    }

    private void setSecondScore(Game game, Frame normalFrame, int firstScore) {
        int remainPin = MAX_PINS - firstScore;
        if (remainPin > 0) {
            scoreAndPrint(game, normalFrame);
        }
    }

    private int setScore(Frame normalFrame) {
        int score = inputScore(normalFrame);
        normalFrame.bowl(score);
        return score;
    }

    public boolean isBonusFlag() {
        return bonusFlag;
    }

    public static PlayBowling getPlayBowling() {
        return playBowling;
    }
}
