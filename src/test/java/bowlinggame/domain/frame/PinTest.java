package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PinTest {

	@Test
	public void 생성_확인() {
		Pin pin = Pin.from(5);

		assertThat(pin == Pin.from(5)).isTrue();
	}

	@Test(expected = IllegalArgumentException.class)
	public void 최대핀개수보다_많을때() {
		Pin.from(Pin.MAX_PIN_COUNT + 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void 최소핀개수보다_적을떄() {
		Pin.from(Pin.MIN_PIN_COUNT - 1);
	}
}