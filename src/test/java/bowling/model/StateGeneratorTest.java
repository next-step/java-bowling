package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateGeneratorTest {

	@Test
	@DisplayName("점수 하나만 있을시 스트라이크, 커터, 기타 점수를 환산한다.")
	public void getOneScore() {
		assertAll(
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(10))).isEqualTo("X"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(0))).isEqualTo("-"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(5))).isEqualTo("5")
		);
	}

	@Test
	@DisplayName("점수가 2개 있을시 스트라이크, 커터, 기타 점수를 환산한다.")
	public void getTwoScore() {
		assertAll(
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(10), new Pin(0))).isEqualTo("X|-"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(4), new Pin(6))).isEqualTo("4|/"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(3), new Pin(4))).isEqualTo("3|4"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(3), new Pin(0))).isEqualTo("3|-")
		);
	}

	@Test
	@DisplayName("점수가 3개 있을시 스트라이크, 커터, 기타 점수를 환산한다.")
	public void getThreeScore() {
		assertAll(
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(10), new Pin(10), new Pin(10))).isEqualTo("X|X|X"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(10), new Pin(10), new Pin(4))).isEqualTo("X|X|4"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(10), new Pin(10), new Pin(0))).isEqualTo("X|X|-"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(10), new Pin(0), new Pin(10))).isEqualTo("X|-|X"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(10), new Pin(4), new Pin(6))).isEqualTo("X|4|/"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(10), new Pin(4), new Pin(5))).isEqualTo("X|4|5"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(4), new Pin(6), new Pin(10))).isEqualTo("4|/|X"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(4), new Pin(6), new Pin(7))).isEqualTo("4|/|7"),
			() -> assertThat(ScoreGenerator.scoreGenerator(new Pin(4), new Pin(6), new Pin(0))).isEqualTo("4|/|-")
		);
	}

}