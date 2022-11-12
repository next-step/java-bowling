package bowling;

import bowling.domain.PlayerName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApp {

    private final InputView inputView = new InputView();
    private final ResultView resultView = new ResultView();

    public void start(){
        PlayerName playerName = inputView.getPlayName();
        resultView.printDefaultPanels(playerName);
    }
}
