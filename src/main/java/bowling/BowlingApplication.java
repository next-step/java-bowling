package bowling;

import bowling.domain.Player;
import bowling.domain.Result;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {

        String name = InputView.inputPlayerNmae().trim();
        Player player = new Player(name);

        System.out.println(player);

        ResultView.printScoreBoardTop();
    }
}
