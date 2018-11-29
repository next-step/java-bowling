package bowlinggame.domain.frame.result;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import org.junit.Before;
import org.junit.Test;

public class MissTest {

	private Miss miss;

	@Before
	public void setUp() throws Exception {
		miss = Miss.getInstance(5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void 잘못된_핀개수() {
		Miss.getInstance(10);
	}

	@Test
	public void 다음_투구_결과_확인_스페어() {
		Pin pin = getRemainingPin();
		assertThat(miss.next(pin.knockDown(5))).isInstanceOf(Spare.class);
	}

	@Test
	public void 다음_투구_결과_확인_거터() {
		Pin pin = getRemainingPin();
		assertThat(miss.next(pin.knockDown(0))).isInstanceOf(Gutter.class);
	}

	@Test
	public void 다음_투구_결과_확인_미스() {
		Pin pin = getRemainingPin();
		Result result = miss.next(pin.knockDown(3));
		assertThat(result).isEqualTo(Miss.getInstance(3));
	}

	private Pin getRemainingPin() {
		return Pin.fromKnockedPinCount(miss.getKnockedDownPinCount());
	}
}