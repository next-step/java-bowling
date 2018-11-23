package bowlinggame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.FinalFrame;
import bowlinggame.domain.frame.Frame;
import bowlinggame.domain.frame.NormalFrame;
import bowlinggame.domain.result.Spare;
import bowlinggame.domain.result.Strike;
import org.junit.Test;

public class FrameTest {

	@Test
	public void 프레임_생성_확인() {
		Frame frame = Frame.of(1);

		assertThat(frame).isEqualTo(Frame.of(1));
	}

	@Test
	public void 일반_프레임_생성() {
		Frame currentFrame = Frame.of(7);

		assertThat(currentFrame.next()).isInstanceOf(NormalFrame.class);
	}

	@Test
	public void 마지막_프레임_생성() {
		Frame currentFrame = Frame.of(9);

		assertThat(currentFrame.next()).isInstanceOf(FinalFrame.class);
	}

	@Test
	public void 투구_결과_확인_스트라이크() {
		Frame frame = Frame.of(1);
		frame.roll(10);

		assertThat(frame.getFrameResult().getResults()).first().isEqualTo(Strike.RESULT_CHARACTER);
	}

	@Test
	public void 투구_결과_확인_미스_스페어() {
		Frame frame = Frame.of(1);
		frame.roll(5);
		frame.roll(5);

		assertThat(frame.getFrameResult().getResults()).containsExactly("5", Spare.RESULT_CHARACTER);
	}
}
