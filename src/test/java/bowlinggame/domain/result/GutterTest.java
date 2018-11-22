package bowlinggame.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import org.junit.Test;

public class GutterTest {

	@Test
	public void 다음_투구_결과_스페어() {
		Gutter gutter = Gutter.getInstance();
		Pin pin = Pin.from(10);

		assertThat(gutter.next(pin)).isEqualTo(Spare.getInstance());
	}

	@Test
	public void 다음_투구_결과_미스() {
		Gutter gutter = Gutter.getInstance();
		final int pinCount = 9;
		Pin pin = Pin.from(pinCount);

		assertThat(gutter.next(pin)).isEqualTo(Miss.getInstance(pinCount));
	}

	@Test
	public void 다음_투구_결과_거터() {
		Gutter gutter = Gutter.getInstance();
		Pin pin = Pin.from(0);

		assertThat(gutter.next(pin)).isEqualTo(Gutter.getInstance());
	}
}