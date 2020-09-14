package bowling.domain.frame;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.DownedPinCount.*;
import static bowling.domain.frame.Frame.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

	@DisplayName("객체 생성 및 record 메서드 테스트")
	@Test
	void constructTest() {
		int index = 0;
		Frame frame = new Frame(index);
		frame.record(ONE);
		assertThat(frame).isEqualTo(new Frame(index, ONE));
	}

	@DisplayName("최대 투구 횟수만큼 투구했는데 더 투구하려고하면 Exception 테스트")
	@Test
	void failRecordingTest2() {
		int index = 0;
		Frame frame = new Frame(index, FIVE);
		frame.record(ONE);
		assertThatThrownBy(() -> frame.record(ONE))
				.isInstanceOf(BowlingException.class)
				.hasMessage(String.format(OVER_DEFAULT_MAX_PITCH_COUNT, DEFAULT_MAX_PITCH_COUNT));
	}

	@DisplayName("Frame이 종료되었는지 알수 있나 테스트")
	@Test
	void isFrameFinished() {
		int index = 0;
		Frame frame1 = new Frame(index, TEN);

		Frame frame2 = new Frame(index, FIVE);
		frame2.record(ZERO);

		assertThat(frame1.isFrameFinished()).isTrue();
		assertThat(frame2.isFrameFinished()).isTrue();
	}
}
