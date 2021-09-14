package bowling;

import bowling.view.BowlingInputView;
import bowling.view.BowlingOutputView;

public class BowlingMain {
    public static void main(String[] args) {
        String name = BowlingInputView.getPlayerNameWithPrompt("플레이어 이름은(3 english letters)?: ");
        Player player = new Player(name);

        ScoreFrames scoreFrames = new ScoreFrames();
        BowlingOutputView.printFramesStatus(player, scoreFrames);

        while (scoreFrames.isContinued()) {
            String message = String.format("%d프레임 투구 : ", scoreFrames.getCurrentTurn());
            int score = BowlingInputView.getBowlingScoreWithPrompt(message);

            scoreFrames.bowl(score);

            BowlingOutputView.printFramesStatus(player, scoreFrames);
        }
    }
}
