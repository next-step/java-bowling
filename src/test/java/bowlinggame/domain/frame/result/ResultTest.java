package bowlinggame.domain.frame.result;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import org.junit.Test;

public class ResultTest {

	@Test
	public void 처음_투구_결과_스트라이크() {
		assertThat(Result.first(Pin.fromKnockedPinCount(10))).isInstanceOf(Strike.class);
	}

	@Test
	public void 처음_투구_결과_거터() {
		assertThat(Result.first(Pin.fromKnockedPinCount(0))).isInstanceOf(Gutter.class);
	}

	@Test
	public void 처음_투구_결과_미스() {
		assertThat(Result.first(Pin.fromKnockedPinCount(5))).isInstanceOf(Miss.class);
	}
}