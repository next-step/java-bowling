package bowling.domain;

import bowling.domain.frame.DefaultFrame;

import static bowling.domain.Board.BOARD_MAX_SIZE;

public class Game {
    private final Board board;

    private int frameNum = 1;

    public Game(Board board) {
        this.board = board;
    }

    public void init() {
        board.init();
    }

    public boolean isGameOver() {
        return frameNum > BOARD_MAX_SIZE;
    }

    public Board getBoard() {
        return board;
    }

    public void playGame(KnockedPins knockedPins) {
        DefaultFrame recentDefaultFrame = board.getRecentFrame(frameNum - 1);

        if (!recentDefaultFrame.isLastFrame()) {
            playNormalGame(recentDefaultFrame, knockedPins);
        }
        if (recentDefaultFrame.isLastFrame()) {

            playLastGame(recentDefaultFrame, knockedPins);
        }
    }

    private void playNormalGame(DefaultFrame recentDefaultFrame, KnockedPins knockedPins) {
        if (!recentDefaultFrame.hasDoneFirstPitch()) {
            recentDefaultFrame.bowl(knockedPins, 1);
            if (knockedPins.isStrike()) {
                frameNum++;
            }
            return;
        }
        if (recentDefaultFrame.hasDoneFirstPitch()) {
            recentDefaultFrame.bowl(knockedPins, 2);
            frameNum++;
        }
    }

    private void playLastGame(DefaultFrame recentDefaultFrame, KnockedPins knockedPins) {

        if (recentDefaultFrame.hasDoneFirstPitch() && !recentDefaultFrame.hasDoneSecondPitch()) {
            recentDefaultFrame.bowl(knockedPins, 2);
            if (!recentDefaultFrame.isSpare(recentDefaultFrame.getFirstScore(), recentDefaultFrame.getSecondScore()) && !recentDefaultFrame.isStrike()) {
                frameNum++;
                return;
            }
            return;
        }
        if (recentDefaultFrame.hasDoneFirstPitch() && recentDefaultFrame.hasDoneSecondPitch()) {
            recentDefaultFrame.bowl(knockedPins, 3);
            frameNum++;
        }
        if (recentDefaultFrame.isEmpty()) {
            recentDefaultFrame.bowl(knockedPins, 1);
            return;
        }
    }

    public int getFrameNum() {
        return this.frameNum;
    }
}


