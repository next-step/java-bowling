package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class FinalFrameTest {

	private Frame frame;

	@Before
	public void setUp() {
		frame = Frame.of(FrameNumber.last());
	}

	@Test
	public void 첫번째_투구_미스() {
		frame.roll(3);

		assertThat(frame.isCompleted()).isFalse();
	}

	@Test
	public void 첫번째_투구_스트라이크() {
		frame.roll(10);

		assertThat(frame.isCompleted()).isFalse();
	}

	@Test
	public void 두번의_투구가_미스일때_종료() {
		frame.roll(3);
		frame.roll(6);

		assertThat(frame.isCompleted()).isTrue();
	}

	@Test
	public void 두번의_투구가_스페어일때_진행() {
		frame.roll(3); // miss
		frame.roll(7); // 스페어

		assertThat(frame.isCompleted()).isFalse();
	}

	@Test
	public void 두번의_투구가_스트라이크일때_진행() {
		frame.roll(10); // 스트라이크
		assertThat(frame.isCompleted()).isFalse();

		frame.roll(10); // 스트라이크
		assertThat(frame.isCompleted()).isFalse();
	}

	@Test
	public void 세번_투구_종료() {
		frame.roll(3);
		frame.roll(7);
		frame.roll(7);

		assertThat(frame.isCompleted()).isTrue();
	}

}