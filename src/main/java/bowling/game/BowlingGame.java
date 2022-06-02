package bowling.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bowling.player.Player;

public class BowlingGame {

	private static final int MIN_FRAME_NUMBER = 1;

	private final List<EachGame> eachGames = new ArrayList<>();
	private int frameNumber;

	private BowlingGame(List<EachGame> eachGames, int frameNumber) {
		this.eachGames.addAll(eachGames);
		this.frameNumber = frameNumber;
	}

	public static BowlingGame start(List<Player> players) {
		List<EachGame> eachGames = players.stream()
			.map(EachGame::start)
			.collect(Collectors.toList());
		return new BowlingGame(eachGames, MIN_FRAME_NUMBER);
	}

	public boolean isEnd() {
		return eachGames.stream()
			.allMatch(EachGame::isEnd);
	}

	public String getNowTurnPlayerName() {
		return nowTurnGame()
			.map(EachGame::playerName)
			.orElseThrow(() -> new IllegalStateException("현재 볼링 게임의 프레임에 해당 하는 각 게임이 존재하지 않습니다."));
	}

	public void throwBowl(int throwCount) {
		EachGame nowTurnGame = nowTurnGame()
			.orElseThrow(() -> new IllegalStateException("현재 볼링 게임의 프레임에 해당 하는 각 게임이 존재하지 않습니다."));
		nowTurnGame.throwBowl(throwCount);
	}

	private Optional<EachGame> nowTurnGame() {
		return eachGames.stream()
			.filter(eachGame -> eachGame.isNowTurnFrameNumber(frameNumber))
			.findFirst();
	}

	public void updateFrameNumber() {
		if (nowTurnGame().isEmpty()) {
			frameNumber += 1;
		}
	}

	public List<EachGame> eachGames() {
		return List.copyOf(eachGames);
	}
}
