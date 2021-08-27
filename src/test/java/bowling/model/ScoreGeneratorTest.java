package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreGeneratorTest {

	@Test
	@DisplayName("점수 하나만 있을시 스트라이크, 커터, 기타 점수를 환산한다.")
	public void getOneScore() {
		assertAll(
			() -> assertThat(ScoreGenerator.scoreGenerator(10)).isEqualTo("X"),
			() -> assertThat(ScoreGenerator.scoreGenerator(0)).isEqualTo("-"),
			() -> assertThat(ScoreGenerator.scoreGenerator(5)).isEqualTo("5")
		);
	}

	@Test
	@DisplayName("점수가 2개 있을시 스트라이크, 커터, 기타 점수를 환산한다.")
	public void getTwoScore() {
		assertAll(
			() -> assertThat(ScoreGenerator.scoreGenerator(10, 0)).isEqualTo("X|-"),
			() -> assertThat(ScoreGenerator.scoreGenerator(4, 6)).isEqualTo("4|/"),
			() -> assertThat(ScoreGenerator.scoreGenerator(3, 4)).isEqualTo("3|4"),
			() -> assertThat(ScoreGenerator.scoreGenerator(3, 0)).isEqualTo("3|-")
		);
	}

	@Test
	@DisplayName("점수가 3개 있을시 스트라이크, 커터, 기타 점수를 환산한다.")
	public void getThreeScore() {
		assertAll(
			() -> assertThat(ScoreGenerator.scoreGenerator(10, 10, 10)).isEqualTo("X|X|X"),
			() -> assertThat(ScoreGenerator.scoreGenerator(10, 10, 4)).isEqualTo("X|X|4"),
			() -> assertThat(ScoreGenerator.scoreGenerator(10, 10, 0)).isEqualTo("X|X|-"),
			() -> assertThat(ScoreGenerator.scoreGenerator(10, 0, 10)).isEqualTo("X|-|X"),
			() -> assertThat(ScoreGenerator.scoreGenerator(10, 4, 6)).isEqualTo("X|4|/"),
			() -> assertThat(ScoreGenerator.scoreGenerator(10, 4, 5)).isEqualTo("X|4|5"),
			() -> assertThat(ScoreGenerator.scoreGenerator(4, 6, 10)).isEqualTo("4|/|X"),
			() -> assertThat(ScoreGenerator.scoreGenerator(4, 6, 7)).isEqualTo("4|/|7"),
			() -> assertThat(ScoreGenerator.scoreGenerator(4, 6, 0)).isEqualTo("4|/|-")
		);
	}

}