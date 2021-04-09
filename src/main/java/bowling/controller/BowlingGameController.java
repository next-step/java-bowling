package bowling.controller;

import bowling.entity.Player;
import bowling.entity.Players;
import bowling.views.InputView;
import bowling.views.OutputView;

public class BowlingGameController {
	private final InputView inputView;
	private final OutputView outputView;

	public BowlingGameController() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	public void run() {
		int count = makePeopleCount();

		Players players = makePlayers(count);
		while (players.isKeepGoing()) {
			int turn = players.turn();
			players.getPlayers().forEach((player) -> playerRunFrame(turn, players, player));
		}
	}

	private void playerRunFrame(int turn, Players players, Player player) {
		while (player.isKeepGoingTurn(turn)) {
			player.addPlayerFrameScore(inputView.getFrameScore(player));
			showGame(players);
		}
	}

	private Players makePlayers(int count) {
		return new Players(inputView.getPlayers(count));
	}

	private int makePeopleCount() {
		return inputView.getPeopleCount();
	}

	private void showGame(Players players) {
		outputView.showFrames();
		players.getPlayers().forEach(player -> {
			outputView.showPlayerFrames(player);
			outputView.showPlayerFrameScore(player);
		});
	}

}
