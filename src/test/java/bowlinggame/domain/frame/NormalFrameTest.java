package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.result.Score;
import bowlinggame.domain.frame.result.ScoreTest;
import org.junit.Before;
import org.junit.Test;

public class NormalFrameTest {

	private Frame frame;

	@Before
	public void setUp() throws Exception {
		frame = Frame.first();
	}

	@Test
	public void 스트라이크_종료_확인() {
		frame.roll(10);
		assertThat(frame.isCompleted()).isTrue();
	}

	@Test
	public void 첫투구_미스_진행() {
		frame.roll(3);
		assertThat(frame.isCompleted()).isFalse();
	}

	@Test
	public void 스페어_종료() {
		frame.roll(3); // first rolling
		frame.roll(7);
		assertThat(frame.isCompleted()).isTrue();
	}

	@Test
	public void 두번_투구_종료() {
		frame.roll(3); // first rolling
		frame.roll(0);
		assertThat(frame.isCompleted()).isTrue();
	}

	@Test
	public void 스트라이크_점수_계산() {
		frame.roll(10);
		frame.roll(8);
		frame.roll(2);

		assertThat(frame.getScore()).isEqualTo(Score.of(20));
	}

	@Test
	public void 스페어_점수_계산() {
		frame.roll(2);
		frame.roll(8);
		frame.roll(5);

		assertThat(frame.getScore()).isEqualTo(Score.of(15));
	}

	@Test
	public void 보너스_없는_점수_계산() {
		frame.roll(3);
		frame.roll(5);

		assertThat(frame.getScore()).isEqualTo(Score.of(8));
	}

	@Test
	public void 보너스_점수가_남았을때() {
		frame.roll(10);

		assertThat(frame.getScore()).isEqualTo(ScoreTest.STRIKE);
	}
}