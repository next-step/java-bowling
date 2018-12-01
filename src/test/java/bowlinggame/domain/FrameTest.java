package bowlinggame.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.FinalFrame;
import bowlinggame.domain.frame.Frame;
import bowlinggame.domain.frame.FrameNumber;
import bowlinggame.domain.frame.NormalFrame;
import org.junit.Test;

public class FrameTest {

	@Test
	public void 일반_프레임_생성() {
		assertThat(Frame.first()).isInstanceOf(NormalFrame.class);
	}

	@Test
	public void 마지막_프레임_생성() {
		Frame ninthFrame = Frame.of(FrameNumber.of(9));
		assertThat(ninthFrame.next()).isInstanceOf(FinalFrame.class);
	}

	@Test(expected = IllegalStateException.class)
	public void 마지막_프레임_종료() {
		Frame finalFrame = Frame.of(FrameNumber.of(10));
		finalFrame.next();
	}
}
