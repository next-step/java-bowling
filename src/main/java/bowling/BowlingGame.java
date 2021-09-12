package bowling;

import bowling.domain.FrameNumber;
import bowling.domain.PinCount;
import bowling.domain.Player;
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
        Player player = new Player(inputView.getPlayerName());
        outputView.printScoreBoard(player.toRenderer());
        FrameNumber frameNumber = FrameNumber.FIRST;
        while (!frameNumber.isOver()) {
            outputView.printBowlInputMessage(frameNumber.getNumber());
            frameNumber = player.bowl(frameNumber, PinCount.valueOf(inputView.getFallenPinCount()));
            outputView.printScoreBoard(player.toRenderer());
        }
    }

    public static void main(String[] args) {
        InputView inputView = new InputView(System.in);
        OutputView outputView = new OutputView(System.out);
        BowlingGame bowlingGame = new BowlingGame(inputView, outputView);
        bowlingGame.run();
    }

}
