package camp.nextstep.edu.nextstep8.bowling;

import static camp.nextstep.edu.nextstep8.bowling.constant.BowlingRule.MAX_FRAME;
import static camp.nextstep.edu.nextstep8.bowling.constant.BowlingRule.MAX_SCORE;

public class BowlingGame {
    private int score;
    private int spare;
    private int frame = 1;
    private ScoreBoard scoreBoard = new ScoreBoard();

    public void play() {
        String player = BowlingGameInput.getPlayer();

        while(true) {
            BowlingGameView.showDashboard(player, scoreBoard);

            score = BowlingGameInput.getHitCount(frame);
            validateScore(score);

            if(score < MAX_SCORE) {
                spare = BowlingGameInput.getHitCount(frame);
                validateSpare(score, spare);
            }

            scoreBoard.markScore(frame, score, spare);
            spare = 0;

            if(meetGameOverCondition(frame, score)) {
                BowlingGameView.showDashboard(player, scoreBoard);
                break;
            }
            frame++;
        }
    }

    private void validateScore(int score) {
        if(MAX_SCORE < score) {
            throw new IllegalArgumentException(MAX_SCORE + "점 을 넘을 수 없습니다");
        }
    }

    public void validateSpare(int score, int spare) {
        if(MAX_SCORE < score + spare) {
            throw new IllegalArgumentException("잔여 Spare 점수를 초과하였습니다");
        }
    }

    private boolean meetGameOverCondition(int frame, int score) {
        if(meetOneMoreChance(frame, score)) {
            return false;
        }

        return MAX_FRAME < frame;
    }

    private boolean meetOneMoreChance(int frame, int score) {
        return MAX_FRAME == frame &&
                (MAX_SCORE == score || MAX_SCORE == (frame + score));
    }
}
