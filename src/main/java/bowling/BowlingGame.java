package bowling;

import bowling.domain.PlayerName;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGame {

    private final InputView inputView;
    private final OutputView outputView;

    public BowlingGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printPlayerNameInputMessage();
        PlayerName playerName = new PlayerName(inputView.getPlayerName());
    }

    public static void main(String[] args) {
        InputView inputView = new InputView(System.in);
        OutputView outputView = new OutputView(System.out);
        BowlingGame bowlingGame = new BowlingGame(inputView, outputView);
        bowlingGame.run();
    }

}
