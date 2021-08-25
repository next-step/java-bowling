package bowling.model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

	@Test
	@DisplayName("게임의 결과를 생성한다.")
	public void createGameResult() {
		GameResult gameResult = new GameResult(getGameResult());

		assertThat(gameResult).isEqualTo(new GameResult(getGameResult()));
	}

	@Test
	@DisplayName("게임의 결과를 저장한다.")
	public void saveGameResult() {
		GameResult gameResult = new GameResult(new ArrayList<>());
		gameResult.save(new Pin(5));
		gameResult.save(new Pin(3));

		assertThat(gameResult).isEqualTo(new GameResult(getGameResult()));
	}

	@Test
	@DisplayName("게임의 결과를 조회 할 수 있다.")
	public void findGameScore() {
		GameResult gameResult = new GameResult(getGameResult());

		assertThat(gameResult.findScore(0)).isEqualTo(new Pin(5));
		assertThat(gameResult.findScore(1)).isEqualTo(new Pin(3));
	}

	@Test
	@DisplayName("게임의 종합 점수를 알수 있다.")
	public void findGameTotalScore() {
		GameResult gameResult = new GameResult(getGameResult());

		assertThat(gameResult.findTotalScore()).isEqualTo(8);
	}

	@Test
	@DisplayName("게임의 결과가 스트라이크인지 스페어 인지 판단한다.")
	public void isStrikeOrSpare() {
		GameResult gameResult = new GameResult(getGameResult());

		assertThat(gameResult.isStrikeOrSpare()).isFalse();

		GameResult gameResult2 = new GameResult(Arrays.asList(new Pin(10), new Pin(5)));

		assertThat(gameResult2.isStrikeOrSpare()).isTrue();

		GameResult gameResult3 = new GameResult(Arrays.asList(new Pin(4), new Pin(6)));

		assertThat(gameResult3.isStrikeOrSpare()).isTrue();
	}

	@Test
	@DisplayName("게임의 결과가 첫번째 10점이면 스트라이크 이다.")
	public void isStrike() {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(10));
		GameResult gameResult = new GameResult(pins);

		assertThat(gameResult.isStrike()).isTrue();

		List<Pin> pins2 = new ArrayList<>();
		pins2.add(new Pin(9));
		GameResult gameResult2 = new GameResult(pins2);

		assertThat(gameResult2.isStrike()).isFalse();

	}

	private List<Pin> getGameResult() {
		return Arrays.asList(new Pin(5), new Pin(3));
	}

}