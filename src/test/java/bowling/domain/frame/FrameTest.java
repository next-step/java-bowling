package bowling.domain.frame;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.OneToMany;

import static bowling.domain.DownedPinCount.*;
import static bowling.domain.frame.Frame.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

	@DisplayName("객체 생성 및 record 메서드 테스트")
	@Test
	void constructTest() {
		int index = 0;
		Frame frame1 = new Frame(index);
		frame1.roll(ONE);
		Frame frame2 = new Frame(index);
		frame2.roll(ONE);
		assertThat(frame1).isEqualTo(frame2);
	}

	@DisplayName("Frame이 종료되었는지 알수 있나 테스트")
	@Test
	void isFrameFinished() {
		int index = 0;
		Frame frame1 = new Frame(index);
		frame1.roll(TEN);

		Frame frame2 = new Frame(index);
		frame2.roll(FIVE);
		frame2.roll(ZERO);

		assertThat(frame1.isFrameFinished()).isTrue();
		assertThat(frame2.isFrameFinished()).isTrue();
	}
}
