package bowling.domain;

import java.util.Scanner;

import bowling.domain.State.LastFrameBowl;
import bowling.view.ResultView;

public class PlayBowling {
    private static final int MAX_PINS = 10;
    private static final int FRAME_NUM = 10;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean bonusFlag = false;
    private static int frameFelledPins = 0;
    private static Frame startFrame;

    public static void play(Game game, Frame frame) {
        startFrame = frame;
        startFrame.createBoard();
        frame = playNormalFrame(game, frame);
        playFinalFrame(game, frame);
    }

    private static Frame playNormalFrame(Game game, Frame frame) {
        for (int i = 0; i < FRAME_NUM - 1; i++) {
            frame = firstRoll(game, frame);
            frame = getFelledPins(game, frame);
            frameFelledPins = 0;
        }

        return frame;
    }

    private static void checkBonus() {
        if (bonusFlag) {
            return;
        }

        if (frameFelledPins == MAX_PINS) {
            bonusFlag = true;
        }
    }

    private static Frame getFelledPins(Game game, Frame frame) {
        if (frameFelledPins < MAX_PINS) {
            frame = roll(game, frame);
        }
        return frame;
    }

    private static void playFinalFrame(Game game, Frame frame) {
        frame = firstRoll(game, frame);
        frame = roll(game, frame);

        if (bonusFlag) {
            roll(game, frame);
        }
    }

    private static Frame roll(Game game, Frame frame) {
        System.out.println();
        System.out.print(frame.getFrameNum() + "프레임 투구 :");
        int felledPins = scanner.nextInt();
        frameFelledPins += felledPins;
        callBonus(frame);
        frame = frame.bowl(felledPins);
        game = new Game(game.getUserName(), startFrame.createBoard());
        ResultView.print(game);
        return frame;
    }

    private static Frame firstRoll(Game game, Frame frame) {
        frame = inputFelledPins(frame);
        game = new Game(game.getUserName(), startFrame.createBoard());
        ResultView.print(game);
        return frame;
    }

    private static Frame inputFelledPins(Frame frame) {
        System.out.println();
        System.out.print(frame.getFrameNum() + "프레임 투구 :");
        int felledPins = scanner.nextInt();
        frameFelledPins += felledPins;
        callBonus(frame);
        return frame.bowl(felledPins);
    }

    private static void callBonus(Frame frame) {
        checkBonus();
        if (frame.getFrameNum() == FRAME_NUM - 1 && bonusFlag) {
            NormalFrame normalFrame = (NormalFrame) frame;
            normalFrame.bonus();
        }
    }
}
