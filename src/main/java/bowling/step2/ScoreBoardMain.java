package bowling.step2;

import bowling.step2.domain.Player;
import bowling.step2.view.InputView;
import bowling.step2.view.ResultView;

public class ScoreBoardMain {
    public static void main(String[] args) {
        Player player = new Player(InputView.inputPlayerName());
        ResultView.printGrameScoreBoard(player);
    }
}
