package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.BowlingGames;
import bowling.domain.KnockDownPins;
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

        BowlingGames bowlingGames = BowlingGames.init(playerNames);
        while (!bowlingGames.isEnd()) {
            setKnockDownPins2(bowlingGames);
            resultView.print2(bowlingGames.convertToDto());
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

    private void setKnockDownPins2(BowlingGames bowlingGames) {
        try {
            KnockDownPins knockDownPins = ValidInputHelper.get(() -> getKnockDownPins2(bowlingGames), inputView::printError);
            bowlingGames.setKnockDownPins(knockDownPins);
        } catch (RuntimeException e) {
            inputView.printError(e);
            setKnockDownPins2(bowlingGames);
        }
    }

    private KnockDownPins getKnockDownPins(BowlingGame bowlingGame) {
        Integer knockDownPins = ValidInputHelper.get(() -> inputView.getKnockDownPins(bowlingGame.getCurrentFrameNo()), inputView::printError);
        return KnockDownPins.valueOf(knockDownPins);
    }

    private KnockDownPins getKnockDownPins2(BowlingGames bowlingGames) {
        Integer knockDownPins = ValidInputHelper.get(() -> inputView.getKnockDownPins2(bowlingGames.getCurrentPlayer()), inputView::printError);
        return KnockDownPins.valueOf(knockDownPins);
    }
}
