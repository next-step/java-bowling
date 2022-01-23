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

    public void playGame(Score score) {
        DefaultFrame recentDefaultFrame = board.getRecentFrame(frameNum - 1);

        if (!recentDefaultFrame.isLastFrame()) {
            playNormalGame(recentDefaultFrame, score);
        }
        if (recentDefaultFrame.isLastFrame()) {

            playLastGame(recentDefaultFrame, score);
        }
    }

    private void playNormalGame(DefaultFrame recentDefaultFrame, Score score) {
        if (!recentDefaultFrame.hasDoneFirstPitch()) {
            recentDefaultFrame.makeScore(score, 1);
            if (score.isStrike()) {
                frameNum++;
            }
            return;
        }
        if (recentDefaultFrame.hasDoneFirstPitch()) {
            recentDefaultFrame.makeScore(score, 2);
            frameNum++;
        }
    }

    private void playLastGame(DefaultFrame recentDefaultFrame, Score score) {

        if (recentDefaultFrame.hasDoneFirstPitch() && !recentDefaultFrame.hasDoneSecondPitch()) {
            recentDefaultFrame.makeScore(score, 2);
            if (!recentDefaultFrame.isSpare(recentDefaultFrame.getFirstScore(), recentDefaultFrame.getSecondScore()) && !recentDefaultFrame.isStrike()) {
                frameNum++;
                return;
            }
            return;
        }
        if (recentDefaultFrame.hasDoneFirstPitch() && recentDefaultFrame.hasDoneSecondPitch()) {
            recentDefaultFrame.makeScore(score, 3);
            frameNum++;
        }
        if (recentDefaultFrame.isEmpty()) {
            recentDefaultFrame.makeScore(score, 1);
            return;
        }
    }

    public int getFrameNum() {
        return this.frameNum;
    }
}


