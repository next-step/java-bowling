package bowling;

import bowling.domain.Board;
import bowling.domain.frame.Pin;
import bowling.domain.game.BowlingGames;
import bowling.dto.BoardDto;
import bowling.view.InputView;
import bowling.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Bowling {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        BowlingGames bowlingGames = createBowlingGames(inputView);
        while (!bowlingGames.isGameEnd()) {
            int falldPins = inputView.inputPitch(bowlingGames.nowTurnUserName());
            bowlingGames.bowlNowTurn(Pin.from(falldPins));
            Board board = new Board(bowlingGames.createResults());
            outputView.renderBoard(BoardDto.of(board));
        }
    }

    private static BowlingGames createBowlingGames(InputView inputView) {
        int count = inputView.inputUserCount();
        List<String> names = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String name = inputView.inputName();
            names.add(name);
        }
        return BowlingGames.fromWithUserNames(names);
    }
}
