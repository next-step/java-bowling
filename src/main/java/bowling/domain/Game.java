package bowling.domain;

import bowling.domain.frame.Frame;

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
        Frame recentFrame = board.getRecentFrame(frameNum - 1);

        if (!recentFrame.isLastFrame()) {
            playNormalGame(recentFrame, score);
        }
        if (recentFrame.isLastFrame()) {

            playLastGame(recentFrame, score);
        }
    }

    private void playNormalGame(Frame recentFrame, Score score) {
        if (!recentFrame.hasDoneFirstPitch()) {
            recentFrame.makeScore(score, 1);
            if (score.isStrike()) {
                frameNum++;
            }
            return;
        }
        if (recentFrame.hasDoneFirstPitch()) {
            recentFrame.makeScore(score, 2);
            frameNum++;
        }
    }

    private void playLastGame(Frame recentFrame, Score score) {

        if (recentFrame.hasDoneFirstPitch() && !recentFrame.hasDoneSecondPitch()) {
            recentFrame.makeScore(score, 2);
            if (!recentFrame.isSpare(recentFrame.getFirstScore(), recentFrame.getSecondScore()) && !recentFrame.isStrike()) {
                frameNum++;
                return;
            }
            return;
        }
        if (recentFrame.hasDoneFirstPitch() && recentFrame.hasDoneSecondPitch()) {
            recentFrame.makeScore(score, 3);
            frameNum++;
        }
        if (recentFrame.isEmpty()) {
            recentFrame.makeScore(score, 1);
            return;
        }
    }

    public int getFrameNum() {
        return this.frameNum;
    }
}


