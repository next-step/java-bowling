package bowling.model.score;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.model.Pin;

class ScoreTest {

	@Test
	@DisplayName("스코어를 초기화 한다.")
	public void init() {
		Score score = Score.init();

		assertThat(score).isEqualTo(Score.init());
	}

	@Test
	@DisplayName("스트라이크를 생성 한다.")
	public void createStrike() {
		Score score = Score.strike(new ArrayList<>());

		assertThat(score).isEqualTo(Score.strike(new ArrayList<>()));
	}

	@Test
	@DisplayName("스페어를 생성 한다.")
	public void createSpare() {
		Score score = Score.spare(new ArrayList<>());

		assertThat(score).isEqualTo(Score.spare(new ArrayList<>()));
	}

	@Test
	@DisplayName("미스를 생성 한다.")
	public void createMiss() {
		Score score = Score.miss(new ArrayList<>(), 5);

		assertThat(score).isEqualTo(Score.miss(new ArrayList<>(), 5));
	}

	@Test
	@DisplayName("낫띵을 생성 한다.")
	public void createNothing() {
		Score score = Score.nothing(new ArrayList<>());

		assertThat(score).isEqualTo(Score.nothing(new ArrayList<>()));
	}

	@Test
	@DisplayName("더블을 생성 한다.")
	public void createDouble() {
		Score score = Score.init().Double();

		assertThat(score).isEqualTo(Score.init().Double());
	}

	@Test
	@DisplayName("프레임의 스코어가 1개이고 스트라이크이면 더블조건이 true 이다.")
	public void isDoubleCondition() {
		Score score = Score.strike(getPins(10));

		assertThat(score.isDoubleCondition()).isTrue();
	}

	@Test
	@DisplayName("프레임의 값에 따라 총 결과값을 가져온다.")
	public void getFrameScore() {
		Score score = Score.strike(getPins(5, 4));

		Assertions.assertAll(
			() -> assertThat(score.getFrameScore(1)).isEqualTo(5),
			() -> assertThat(score.getFrameScore(2)).isEqualTo(9)
		);
	}

	@Test
	@DisplayName("볼링의 점수가 더블이면 다음프레임에 계산한다.")
	public void moveNotNextCalculate() {
		Score score = Score.strike(getPins(10));
		Score score2 = Score.strike(getPins(10));
		boolean moveFlag = score2.moveNotNextCalculate(score);

		assertThat(moveFlag).isTrue();
	}

	@Test
	@DisplayName("볼링의 스트라이크 일때 점수를 계산한다.")
	public void calculateScoreByStrike() {
		Score score = Score.strike(getPins(10));
		Score score2 = Score.miss(getPins(4, 5), 9);
		int result = score2.calculate(score);

		assertThat(result).isEqualTo(19);
	}

	@Test
	@DisplayName("볼링의 스페어 일때 점수를 계산한다.")
	public void calculateScoreBySpare() {
		Score score = Score.spare(getPins(4, 6));
		Score score2 = Score.miss(getPins(4, 5), 9);
		int result = score2.calculate(score);

		assertThat(result).isEqualTo(14);
	}

	@Test
	@DisplayName("볼링의 미스 일때 점수를 계산한다.")
	public void calculateScoreByMiss() {
		Score score = Score.miss(getPins(4, 4), 8);

		assertThat(score.getScore()).isEqualTo(8);
	}

	@Test
	@DisplayName("볼링의 더블 일때 점수를 계산한다.")
	public void calculateScoreByDouble() {
		Score score = Score.strike(getPins(10));
		Score score2 = Score.spare(getPins(4, 6));

		assertThat(score2.calculate(score.Double())).isEqualTo(24);
	}

	private List<Pin> getPins(int... pin) {
		List<Pin> pins = new ArrayList<>();
		for (int number : pin) {
			pins.add(new Pin(number));
		}
		return pins;
	}

}