package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.result.Gutter;
import bowlinggame.domain.result.Miss;
import bowlinggame.domain.result.Spare;
import bowlinggame.domain.result.Strike;
import org.junit.Before;
import org.junit.Test;

public class FrameResultTest {

	private FrameResult frameResult;

	@Before
	public void setUp() {
		frameResult = new FrameResult();
	}

	@Test
	public void 첫_투구_미스() {
		final int pinCount = 9;
		Pin pin = Pin.from(pinCount);

		assertThat(frameResult.record(pin)).isEqualTo(Miss.getInstance(pinCount));
	}

	@Test
	public void 첫_투구_스트라이크() {
		final int pinCount = 10;
		Pin pin = Pin.from(pinCount);

		assertThat(frameResult.record(pin)).isEqualTo(Strike.getInstance());
	}

	@Test
	public void 첫_투구_거터() {
		final int pinCount = 0;
		Pin pin = Pin.from(pinCount);

		assertThat(frameResult.record(pin)).isEqualTo(Gutter.getInstance());
	}

	@Test
	public void 다음_투구_미스_미스() {
		frameResult.record(Pin.from(5));

		assertThat(frameResult.record(Pin.from(3))).isEqualTo(Miss.getInstance(3));
	}

	@Test
	public void 다음_투구_미스_스페어() {
		frameResult.record(Pin.from(5));

		assertThat(frameResult.record(Pin.from(5))).isEqualTo(Spare.getInstance());
	}

	@Test
	public void 다음_투구_미스_거터() {
		frameResult.record(Pin.from(5));

		assertThat(frameResult.record(Pin.from(0))).isEqualTo(Gutter.getInstance());
	}
}