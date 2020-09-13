package bowling.domain.frame;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.DownedPinCount.*;
import static bowling.domain.frame.Frame.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

	@DisplayName("객체 생성 및 pitchTheBall 메서드 테스트")
	@Test
	void constructTest() {
		int index = 0;
		Frame frame = new Frame(index);
		frame.pitchTheBall(ONE);
		assertThat(frame).isEqualTo(new Frame(index, ONE));
	}

	@DisplayName("10개 핀이 다 쓰러진 상태에서 더 쓰러뜨리려고 하면 Exception 테스트")
	@Test
	void failPitchingTest1() {
		int index = 0;
		Frame frame = new Frame(index, TEN);
		assertThatThrownBy(() -> frame.pitchTheBall(ONE))
				.isInstanceOf(BowlingException.class)
				.hasMessage(String.format(OVER_MAX_PIN_COUNT_PER_FRAME, MAX_PIN_COUNT_PER_FRAME));
	}

	@DisplayName("최대 투구 횟수만큼 투구했는데 더 투구하려고하면 Exception 테스트")
	@Test
	void failPitchingTest2() {
		int index = 0;
		Frame frame = new Frame(index, FIVE);
		frame.pitchTheBall(ONE);
		assertThatThrownBy(() -> frame.pitchTheBall(ONE))
				.isInstanceOf(BowlingException.class)
				.hasMessage(String.format(OVER_DEFAULT_MAX_PITCH_COUNT, DEFAULT_MAX_PITCH_COUNT));
	}
}
