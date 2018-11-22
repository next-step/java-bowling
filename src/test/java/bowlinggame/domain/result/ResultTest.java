package bowlinggame.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import org.junit.Test;

public class ResultTest {

	@Test
	public void 처음_투구_결과_스트라이크() {
		Pin pin = Pin.from(10);

		assertThat(Result.first(pin)).isEqualTo(Strike.getInstance());
	}

	@Test
	public void 처음_투구_결과_거터() {
		Pin pin = Pin.from(0);

		assertThat(Result.first(pin)).isEqualTo(Gutter.getInstance());
	}

	@Test
	public void 처음_투구_결과_미스() {
		final int pinCount = 6;
		Pin pin = Pin.from(pinCount);

		assertThat(Result.first(pin)).isEqualTo(Miss.getInstance(pinCount));
	}
}