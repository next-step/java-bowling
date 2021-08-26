package bowling.model;

import static org.assertj.core.api.Assertions.*;

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

		assertThat(playResult.findScore(index)).isEqualTo(new Pin(pin));
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

	private List<Pin> getGameResult() {
		return Arrays.asList(new Pin(5), new Pin(3));
	}

}