package bowling.domain.frame;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Index;
import bowling.domain.Pins;
import bowling.domain.Score;
import bowling.domain.state.Ready;
import bowling.domain.state.Strike;

class NormalFrameTest {
	@DisplayName("초기 상태 검증")
	@Test
	void initialize() {
		// given
		NormalFrame normalFrame = NormalFrame.initialize();

		// when
		int frameIndex = normalFrame.getFrameIndex();
		boolean end = normalFrame.isEnd();
		String symbol = normalFrame.symbol();

		// then
		assertAll(
			() -> assertThat(frameIndex).isEqualTo(Index.MIN_OF_INDEX),
			() -> assertThat(end).isFalse(),
			() -> assertThat(symbol).isBlank()
		);
	}

	@DisplayName("bowl 호출시 종료 상태로 변경되면 다음 라운드 객체 반환")
	@Test
	void bowlStrike() {
		// given
		NormalFrame normalFrame = NormalFrame.initialize();
		Pins pins = Pins.create(10);

		// when
		Frame resultFrame = normalFrame.bowl(pins);

		// then
		assertThat(resultFrame).isEqualTo(NormalFrame.create(Index.create(2)));
	}

	@DisplayName("bowl 호출시에 진행 상태이면 현재 라운드 객체 반환")
	@Test
	void bowlFistBowl() {
		// given
		NormalFrame normalFrame = NormalFrame.initialize();
		Pins pins = Pins.create(5);

		// when
		Frame resultFrame = normalFrame.bowl(pins);

		// then
		assertThat(resultFrame).isEqualTo(resultFrame);
	}

	@DisplayName("bowl 호출시 다음이 10 라운드 일 경우 LastFrame 반환")
	@Test
	void bowlLastFrame() {
		// given
		NormalFrame normalFrame = NormalFrame.create(Index.create(9), Ready.create());
		Pins pins = Pins.create(10);

		// when
		Frame resultFrame = normalFrame.bowl(pins);

		// then
		assertThat(resultFrame).isInstanceOf(LastFrame.class);
	}

	@DisplayName("추가 점수 계산시에 스코어 계산 가능 상태이면 현재 점수 반환")
	@Test
	void calculateAdditionalScore() {
		// given
		NormalFrame normalFrame = NormalFrame.create(Index.create(5), Strike.create());
		Score prevScore = Score.create(10, 1);

		// when
		int result = normalFrame.calculateAdditionalScore(prevScore);

		// then
		assertThat(result).isEqualTo(20);
	}

	@DisplayName("추가 점수 계산시에 다음 프레임이 null 이면 -1 반환")
	@Test
	void calculateAdditionalScoreNextFrameNull() {
		// given
		NormalFrame normalFrame = NormalFrame.create(Index.create(5), Strike.create());
		Score prevScore = Score.create(10, 2);

		// when
		int result = normalFrame.calculateAdditionalScore(prevScore);

		// then
		assertThat(result).isEqualTo(Score.INCALCULABLE_SCORE);
	}

	@DisplayName("strike")
	@Test
	void strike() {
		verify(singletonList(10), true, "X", Score.INCALCULABLE_SCORE);
	}

	@DisplayName("5pins spare")
	@Test
	void fivePinsSpare() {
		verify(asList(5, 5), true, "5|/", Score.INCALCULABLE_SCORE);
	}

	@DisplayName("4pins miss")
	@Test
	void fourPinsMiss() {
		verify(asList(4, 3), true, "4|3", 7);
	}

	@DisplayName("3pins gutter")
	@Test
	void threePinsGutter() {
		verify(asList(3, 0), true, "3|-", 3);
	}

	@DisplayName("6pins")
	@Test
	void sixPins() {
		verify(singletonList(6), false, "6", Score.INCALCULABLE_SCORE);
	}

	@DisplayName("ready")
	@Test
	void ready() {
		verify(emptyList(), false, "", Score.INCALCULABLE_SCORE);
	}

	private void verify(List<Integer> pinNumbers, boolean end, String symbol, int score) {
		// given
		NormalFrame normalFrame = NormalFrame.create(Index.create(5), Ready.create());

		// when
		for (int pinNumber : pinNumbers) {
			normalFrame.bowl(Pins.create(pinNumber));
		}

		// then
		assertAll(
			() -> assertThat(normalFrame.isEnd()).isEqualTo(end),
			() -> assertThat(normalFrame.symbol()).isEqualTo(symbol),
			() -> assertThat(normalFrame.score()).isEqualTo(score)
		);
	}
}
