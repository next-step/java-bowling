package bowling.domain;

import java.util.Scanner;

import bowling.view.ResultView;

public class PlayBowling {
    private static final int MAX_PINS = 10;
    private static final int FRAME_NUM = 10;
    private static PlayBowling playBowling = new PlayBowling();
    private static ResultView resultView = ResultView.getResultView();
    private Scanner scanner = new Scanner(System.in);
    private boolean bonusFlag = false;

    public static PlayBowling getPlayBowling() {
        return playBowling;
    }

    public void play(Game game, NormalFrame frame) {
        frame = playNormalFrame(game, frame);
        playFinalFrame(game, frame);
    }

    private NormalFrame playNormalFrame(Game game, NormalFrame frame) {
        for (int i = 0; i < FRAME_NUM - 1; i++) {
            int felledPins = firstRoll(game, frame);
            felledPins = getfelledPins(game, frame, felledPins);
            bonus(felledPins);
            frame = getNormalFrame(frame, i);
        }
        return frame;
    }

    /**
     * 마지막, 첫번째 프레임을 제외하고 만든다.
     * @param frame
     * @param i
     * @return
     */
    private NormalFrame getNormalFrame(NormalFrame frame, int i) {
        if (i != FRAME_NUM - 2) {
            frame = (NormalFrame) frame.create();
        }
        return frame;
    }

    private void bonus(int felledPins) {
        if (felledPins == MAX_PINS) {
            bonusFlag = true;
        }
    }

    private int getfelledPins(Game game, NormalFrame frame, int felledPins) {
        if (felledPins < MAX_PINS) {
            felledPins = roll(game, frame);
        }
        return felledPins;
    }

    private void playFinalFrame(Game game, NormalFrame frame) {
        if (bonusFlag) {
            frame.bonus();
        }
        FinalFrame finalFrame = (FinalFrame) frame.create();
        firstRoll(game, finalFrame);
        roll(game, finalFrame);

        if (bonusFlag) {
            roll(game, finalFrame);
        }
    }

    private int roll(Game game, Frame frame) {
        int felledPins;
        System.out.print(frame.getFrameNum() + "프레임 투구 :");
        felledPins = scanner.nextInt();
        frame.bowl(felledPins);
        felledPins += felledPins;
        resultView.print(game);
        return felledPins;
    }

    private int firstRoll(Game game, Frame frame) {
        int felledPins = inputFelledPins(frame);
        game.addFrame(frame);
        resultView.print(game);
        return felledPins;
    }

    private int inputFelledPins(Frame frame) {
        System.out.print(frame.getFrameNum() + "프레임 투구 :");
        int felledPins = scanner.nextInt();
        frame.bowl(felledPins);
        return felledPins;
    }
}
