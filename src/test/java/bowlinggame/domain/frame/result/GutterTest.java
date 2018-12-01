package bowlinggame.domain.frame.result;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import org.junit.Before;
import org.junit.Test;

public class GutterTest {

	private Result gutter;

	@Before
	public void setUp() throws Exception {
		gutter = Gutter.getInstance();
	}

	@Test
	public void 다음_투구_결과_스페어() {
		assertThat(gutter.next(Pin.fromKnockedPinCount(10))).isInstanceOf(Spare.class);
	}

	@Test
	public void 다음_투구_결과_미스() {
		assertThat(gutter.next(Pin.fromKnockedPinCount(5))).isInstanceOf(Miss.class);
	}

	@Test
	public void 다음_투구_결과_거터() {
		assertThat(gutter.next(Pin.fromKnockedPinCount(0))).isInstanceOf(Gutter.class);
	}
}