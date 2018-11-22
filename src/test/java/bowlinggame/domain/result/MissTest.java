package bowlinggame.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import bowlinggame.domain.frame.Pin;
import org.junit.Before;
import org.junit.Test;

public class MissTest {

	private Miss miss;

	@Before
	public void setUp() {
		miss = Miss.getInstance(6);
	}

	@Test
	public void 동일한_객체_생성_확인() {
		assertThat(miss == Miss.getInstance(6)).isTrue();
	}

	@Test
	public void 다음_투구_결과_확인_스페어() {
		Pin nextPin = Pin.from(4);

		assertThat(miss.next(nextPin)).isEqualTo(Spare.getInstance());
	}

	@Test
	public void 다음_투구_결과_확인_거터() {
		Pin nextPin = Pin.from(0);

		assertThat(miss.next(nextPin)).isEqualTo(Gutter.getInstance());
	}

	@Test
	public void 다음_투구_결과_확인_미스() {
		final int pinCount = 2;
		Pin nextPin = Pin.from(pinCount);

		assertThat(miss.next(nextPin)).isEqualTo(Miss.getInstance(pinCount));
	}

	@Test(expected = IllegalArgumentException.class)
	public void 잘못된_핀개수() {
		Miss.getInstance(Pin.MAX_PIN_COUNT);
	}
}