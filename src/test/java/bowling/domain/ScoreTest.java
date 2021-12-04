package bowling.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.exception.CannotCalculateException;

class ScoreTest {
	@DisplayName("계산 할수 없는 Score 검증")
	@Test
	void createIncalculableScore() {
		// given
		Score incalculableScore = Score.createIncalculableScore();

		// when
		int lastFrameScore = incalculableScore.getLastFrameScore();
		boolean canCalculateScore = incalculableScore.canCalculateScore();

		// then
		assertAll(
			() -> assertThat(lastFrameScore).isEqualTo(Score.INCALCULABLE_SCORE),
			() -> assertThat(canCalculateScore).isFalse(),
			() -> assertThatExceptionOfType(CannotCalculateException.class)
				.isThrownBy(incalculableScore::getScore)
		);
	}

	@DisplayName("현재 스코어를 합산하고 남은 횟수를 감소 시킨 후 반환 한다")
	@Test
	void bowl() {
		// given
		Score score = Score.create(10, 2);
		Pins pins = Pins.create(10);

		// when
		Score bowlScore = score.bowl(pins);

		// then
		assertThat(bowlScore).isEqualTo(Score.create(20, 1));
	}

	@DisplayName("left 횟수가 남아있을 경우 호출시 예외 발생")
	@Test
	void getScoreException() {
		// given
		Score score = Score.create(10, 2);

		// when then
		assertThatExceptionOfType(CannotCalculateException.class)
			.isThrownBy(score::getScore);
	}

	@DisplayName("left 횟수가 남아있지 않으면 호출시 score 반환")
	@Test
	void getScore() {
		// given
		Score score = Score.create(10, 0);

		// when then
		assertThatCode(score::getScore)
			.doesNotThrowAnyException();
	}

	@DisplayName("마지막 프레임에서 호출 할 경우 left 횟수가 남아있어도 score 반환")
	@Test
	void getLastFrameScore() {
		// given
		Score score = Score.create(10, 2);

		// when
		int lastFrameScore = score.getLastFrameScore();

		// then
		assertThat(lastFrameScore).isEqualTo(10);
	}
}
