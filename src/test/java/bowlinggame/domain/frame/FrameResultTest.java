package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.result.Miss;
import bowlinggame.domain.frame.result.Spare;
import org.junit.Before;
import org.junit.Test;

public class FrameResultTest {

	private FrameResult frameResult;

	@Before
	public void setUp() throws Exception {
		frameResult = new FrameResult();
	}

	@Test
	public void 쓰러진_핀_개수로_첫투구_결과기록() {
		assertThat(frameResult.record(Pin.fromKnockedPinCount(5))).isInstanceOf(Miss.class);
		assertThat(frameResult.isAllKnockedDown()).isFalse();
	}

	@Test
	public void 쓰러진_핀_개수로_두번째투구_결과기록() {
		frameResult.record(Pin.fromKnockedPinCount(3));
		assertThat(frameResult.record(Pin.fromKnockedPinCount(10))).isInstanceOf(Spare.class);
		assertThat(frameResult.isAllKnockedDown()).isTrue();
	}
}