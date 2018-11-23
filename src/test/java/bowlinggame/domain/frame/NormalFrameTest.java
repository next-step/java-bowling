package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.result.Gutter;
import bowlinggame.domain.result.Spare;
import bowlinggame.domain.result.Strike;
import org.junit.Before;
import org.junit.Test;

public class NormalFrameTest {

	private Frame frame;

	@Before
	public void setUp() {
		frame = Frame.of(1);
	}

	@Test
	public void 스트라이크_종료_확인() {
		frame.roll(10);

		assertThat(frame.getFrameResult().getResults()).containsExactly(Strike.RESULT_CHARACTER);
		assertThat(frame.isCompleted()).isTrue();
	}

	@Test
	public void 첫투구_미스_진행() {
		frame.roll(3);

		assertThat(frame.getFrameResult().getResults()).containsExactly("3");
		assertThat(frame.isCompleted()).isFalse();
	}

	@Test
	public void 스페어_종료() {
		frame.roll(3);
		frame.roll(7);

		assertThat(frame.getFrameResult().getResults()).containsExactly("3", Spare.RESULT_CHARACTER);
		assertThat(frame.isCompleted()).isTrue();
	}

	@Test
	public void 두번_투구_종료() {
		frame.roll(3);
		frame.roll(0);

		assertThat(frame.getFrameResult().getResults()).containsExactly("3", Gutter.RESULT_CHARACTER);
		assertThat(frame.isCompleted()).isTrue();
	}
}