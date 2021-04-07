package bowling.controller;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

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
			players.getPlayers().forEach(playerRunFunc.apply(inputView).apply(turn, players));
		}

	}

	private Function<InputView, BiFunction<Integer, Players, Consumer<Player>>> playerRunFunc = (inputView) -> (turn, players) -> (player) -> {
		while (player.isKeepGoing(turn)) {
			player.addPlayerFrameScore(inputView.getFrameScore(player));
			showGame(players);
		}
	};

	private Player makePlayer() {
		String name = inputView.getUserName();
		return new Player(name);
	}

	private Players makePlayers(int count) {
		return new Players(inputView.getPlayers(count));
	}

	private int makePeopleCount() {
		return inputView.getPeopleCount();
	}

	private void showGame(Player player) {
		outputView.showFrames();
		outputView.showPlayerFrames(player);
		outputView.showPlayerFrameScore(player);
	}

	private void showGame(Players players) {
		outputView.showFrames();
		players.getPlayers().forEach(player -> {
			outputView.showPlayerFrames(player);
			outputView.showPlayerFrameScore(player);
		});
	}

}
