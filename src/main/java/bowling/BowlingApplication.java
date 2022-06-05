package bowling;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bowling.game.BowlingGame;
import bowling.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingApplication {

	private static final InputView INPUT_VIEW = new InputView();
	private static final OutputView OUTPUT_VIEW = new OutputView();

	public static void main(String[] args) {

		int playerCount = INPUT_VIEW.askPlayerCount();

		List<Player> players = IntStream.rangeClosed(1, playerCount)
			.mapToObj(INPUT_VIEW::askPlayerName)
			.map(Player::new)
			.collect(Collectors.toList());

		BowlingGame bowlingGame = BowlingGame.start(players);
		OUTPUT_VIEW.showResult2(bowlingGame);

		while (!bowlingGame.isEnd()) {
			String playerNameOfTurn = bowlingGame.getNowTurnPlayerName();
			int throwCount = INPUT_VIEW.askThrowCount(playerNameOfTurn);
			bowlingGame.throwBowl(throwCount);
			bowlingGame.updateFrameNumber();
			OUTPUT_VIEW.showResult2(bowlingGame);
		}
	}
}
