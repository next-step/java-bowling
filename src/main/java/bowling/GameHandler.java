package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.KnockDownPins;
import bowling.domain.Player;
import bowling.domain.PlayerName;
import bowling.domain.PlayerNames;
import bowling.helper.ValidInputHelper;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class GameHandler {
    private final InputView inputView;
    private final ResultView resultView;

    public GameHandler(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        Integer numberOfPlayers = ValidInputHelper.get(this::getNumberOfPlayers, inputView::printError);
        PlayerNames playerNames = getPlayerNames(numberOfPlayers);
        BowlingGame bowlingGame = BowlingGame.init(playerNames);
        while (!bowlingGame.isEnd()) {
            setKnockDownPins(bowlingGame);
            resultView.print(bowlingGame.convertToDto());
        }
    }

    private PlayerNames getPlayerNames(Integer numberOfPlayers) {
        List<PlayerName> playerNames = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            int playerNo = i + 1;
            PlayerName playerName = ValidInputHelper.get(() -> getPlayerName(playerNo), inputView::printError);
            playerNames.add(playerName);
        }
        return PlayerNames.of(playerNames);
    }

    private Integer getNumberOfPlayers() {
        return ValidInputHelper.get(inputView::getNumberOfPlayers, inputView::printError);
    }

    private PlayerName getPlayerName(int playerNo) {
        String inputPlayerName = ValidInputHelper.get(() -> inputView.getPlayerName(playerNo), inputView::printError);
        return PlayerName.valueOf(inputPlayerName);
    }

    private void setKnockDownPins(BowlingGame bowlingGame) {
        try {
            KnockDownPins knockDownPins = ValidInputHelper.get(() -> getKnockDownPins(bowlingGame), inputView::printError);
            bowlingGame.setKnockDownPins(knockDownPins);
        } catch (RuntimeException e) {
            inputView.printError(e);
            setKnockDownPins(bowlingGame);
        }
    }

    private KnockDownPins getKnockDownPins(BowlingGame bowlingGame) {
        Integer knockDownPins = ValidInputHelper.get(() -> {
            Player currentPlayer = bowlingGame.getCurrentPlayer();
            PlayerName playerName = currentPlayer.getPlayerName();
            return inputView.getKnockDownPins(playerName);
        }, inputView::printError);
        return KnockDownPins.valueOf(knockDownPins);
    }
}
