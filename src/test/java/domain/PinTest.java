package domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hspark on 22/11/2018.
 */
public class PinTest {
	@Test
	public void test_스코어생성() {
		Pin pin = Pin.of(8);
		assertThat(pin).isEqualTo(Pin.of(8));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_스코어_0보다작음() {
		Pin.of(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_스코어_10보다큼() {
		Pin.of(11);
	}
}