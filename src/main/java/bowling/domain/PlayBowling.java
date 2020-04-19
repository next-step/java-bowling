package bowling.domain;

import java.util.Scanner;

import bowling.view.ResultView;

public class PlayBowling {
    private static final int MAX_PINS = 10;
    private static final int FRAME_NUM = 10;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean bonusFlag = false;

    public static void play(Game game, NormalFrame frame) {
        frame = playNormalFrame(game, frame);
        playFinalFrame(game, frame);
    }

    private static NormalFrame playNormalFrame(Game game, NormalFrame frame) {
        for (int i = 0; i < FRAME_NUM - 1; i++) {
            int felledPins = firstRoll(game, frame);
            felledPins = getFelledPins(game, frame, felledPins);
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
    private static NormalFrame getNormalFrame(NormalFrame frame, int i) {
        if (i != FRAME_NUM - 2) {
            frame = (NormalFrame) frame.create();
        }
        return frame;
    }

    private static void bonus(int felledPins) {
        if (bonusFlag) {
            return;
        }

        if (felledPins == MAX_PINS) {
            bonusFlag = true;
        }
    }

    private static int getFelledPins(Game game, NormalFrame frame, int felledPins) {
        if (!frame.isFinish()) {
            felledPins = roll(game, frame);
        }
        return felledPins;
    }

    private static void playFinalFrame(Game game, NormalFrame frame) {
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

    private static int roll(Game game, Frame frame) {
        int felledPins;
        System.out.print(frame.getFrameNum() + "프레임 투구 :");
        felledPins = scanner.nextInt();
        frame.bowl(felledPins);
        felledPins += felledPins;
        ResultView.print(game);
        return felledPins;
    }

    private static int firstRoll(Game game, Frame frame) {
        int felledPins = inputFelledPins(frame);
        game.addFrame(frame);
        ResultView.print(game);
        return felledPins;
    }

    private static int inputFelledPins(Frame frame) {
        System.out.print(frame.getFrameNum() + "프레임 투구 :");
        int felledPins = scanner.nextInt();
        frame.bowl(felledPins);
        return felledPins;
    }
}
