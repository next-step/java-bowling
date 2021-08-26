package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

	@ParameterizedTest
	@DisplayName("게임의 결과를 조회 할 수 있다.")
	@CsvSource(value = {"0,5", "1,3"})
	public void findGameScore(int index, int pin) {
		GameResult gameResult = new GameResult(getGameResult());

		assertThat(gameResult.findScore(index)).isEqualTo(new Pin(pin));
	}

	@Test
	@DisplayName("게임의 종합 점수를 알수 있다.")
	public void findGameTotalScore() {
		GameResult gameResult = new GameResult(getGameResult());

		assertThat(gameResult.findTotalScore()).isEqualTo(8);
	}

	@Test
	@DisplayName("게임의 합이 10 이 아니면 스트라이크가 아니다.")
	public void isStrikeOrSpare() {
		GameResult gameResult2 = new GameResult(Arrays.asList(new Pin(10), new Pin(5)));

		assertAll(
			() -> assertThat(gameResult2.isStrikeOrSpare()).isFalse(),
			() -> {
				GameResult gameResult3 = new GameResult(Arrays.asList(new Pin(4), new Pin(6)));

				assertThat(gameResult3.isStrikeOrSpare()).isTrue();
			}
		);
	}

	@ParameterizedTest
	@DisplayName("게임의 결과가 첫번째 10점이면 스트라이크 이다.")
	@CsvSource(value = {"10,true", "9,false"})
	public void isStrike(int pin, boolean strike) {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(pin));
		GameResult gameResult = new GameResult(pins);

		assertThat(gameResult.isFirstStrike()).isEqualTo(strike);
	}

	@Test
	@DisplayName("게임의 결과의 합산이 스트라이크나 스페어를 확인 할 수 있다.")
	public void isNotStrikeOrSpare() {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(5));
		GameResult gameResult = new GameResult(pins);

		assertAll(
			() -> assertThat(gameResult.isNotStrikeOrSpare()).isFalse(),
			() -> {
				pins.add(new Pin(4));
				assertThat(gameResult.isNotStrikeOrSpare()).isTrue();
			}
		);
	}

	@Test
	@DisplayName("게임의 결과의 스코어를 알수 있다.")
	public void getGameScore() {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(5));
		GameResult gameResult = new GameResult(pins);

		assertThat(gameResult.getGameScore()).isEqualTo("5");
	}

	@Test
	@DisplayName("마지막 프레임의 첫번쨰 볼링이 스트라이크면 나머지값만 반환한다.")
	public void findReminderScore() {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(10));
		pins.add(new Pin(5));
		GameResult gameResult = new GameResult(pins);

		assertThat(gameResult.findReminderScore()).isEqualTo(5);
	}

	private List<Pin> getGameResult() {
		return Arrays.asList(new Pin(5), new Pin(3));
	}

}