package bowling.domain.frame;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bowling.domain.Index;
import bowling.domain.Pins;
import bowling.domain.state.Ready;

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
}
