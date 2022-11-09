package bowling.step2;

import bowling.step2.domain.Frame;
import bowling.step2.domain.Player;
import bowling.step2.utils.GameRuleUtils;
import bowling.step2.view.InputView;
import bowling.step2.view.ResultView;


public class BowlingGameMain {

    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        ResultView.printInitScoreBoard(player);
        for (int i = GameRuleUtils.GAME_START_INDEX; i <= GameRuleUtils.GAME_LAST_INDEX; i++) {
            player.addScoreMap(i, playGame(i));
            ResultView.printGameScoreBoard(player);
        }
    }

    private static Frame playGame(int i) {
        Frame frame = new Frame(InputView.inputScore(i));

        if(frame.hasNext(i)){
            frame.add(InputView.inputScore(i));
        }

        if(i == GameRuleUtils.GAME_LAST_INDEX && frame.hasBonus(i)){
            frame.add(InputView.inputScore(i));
        }
        return frame;
    }
}
