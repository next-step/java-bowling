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

class PlayResultTest {

	@Test
	@DisplayName("게임의 결과를 생성한다.")
	public void createGameResult() {
		PlayResult playResult = new PlayResult(getGameResult());

		assertThat(playResult).isEqualTo(new PlayResult(getGameResult()));
	}

	@ParameterizedTest
	@DisplayName("게임의 결과를 조회 할 수 있다.")
	@CsvSource(value = {"0,5", "1,3"})
	public void findGameScore(int index, int pin) {
		PlayResult playResult = new PlayResult(getGameResult());

		assertThat(playResult.findScore(index)).isEqualTo(pin);
	}

	@Test
	@DisplayName("게임의 종합 점수를 알수 있다.")
	public void findGameTotalScore() {
		PlayResult playResult = new PlayResult(getGameResult());

		assertThat(playResult.findTotalScore()).isEqualTo(8);
	}

	@Test
	@DisplayName("게임의 결과의 스코어를 알수 있다.")
	public void getGameScore() {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(5));
		PlayResult playResult = new PlayResult(pins);

		assertThat(playResult.getGameScore()).isEqualTo("5");
	}

	@Test
	@DisplayName("게임의 시작 유무를 알수 있다.")
	public void isGameStart() {
		List<Pin> pins = new ArrayList<>();
		PlayResult playResult = new PlayResult(pins);

		assertAll(
			() -> assertThat(playResult.isGameStart()).isFalse(),
			() -> {
				List<Pin> pins2 = new ArrayList<>();
				pins2.add(new Pin(10));
				PlayResult playResult2 = new PlayResult(pins2);
				assertThat(playResult2.isGameStart()).isTrue();
			}
		);
	}

	@Test
	@DisplayName("게임의 결과수가 2개미만이면 2번째 플레이는 false 이다.")
	public void isSecondPlayByOneSize() {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(5));
		PlayResult playResult = new PlayResult(pins);

		assertThat(playResult.isSecondPlay()).isFalse();
	}

	@Test
	@DisplayName("게임의 결과수가 2개이상이면 2번째 플레이는 true 이다.")
	public void isSecondPlay() {
		List<Pin> pins2 = new ArrayList<>();
		pins2.add(new Pin(4));
		pins2.add(new Pin(6));

		PlayResult playResult2 = new PlayResult(pins2);

		assertThat(playResult2.isSecondPlay()).isTrue();
	}

	@Test
	@DisplayName("첫번째 볼이 10점이면 스트라이크 이다.")
	public void isStrike() {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(10));

		PlayResult playResult = new PlayResult(pins);

		assertThat(playResult.isStrike()).isTrue();
	}

	@Test
	@DisplayName("첫번째 볼이 스트라이크가 아니고 두번째 볼과의 합이 10이면 스페어이다.")
	public void isSpare() {
		List<Pin> pins = new ArrayList<>();
		pins.add(new Pin(4));
		pins.add(new Pin(6));

		PlayResult playResult = new PlayResult(pins);

		assertThat(playResult.isSpare()).isTrue();
	}

	private List<Pin> getGameResult() {
		return Arrays.asList(new Pin(5), new Pin(3));
	}

}