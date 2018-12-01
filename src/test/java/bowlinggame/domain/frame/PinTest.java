package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class PinTest {

	@Test
	public void 동일_객체_확인() {
		assertThat(Pin.of(5) == Pin.of(5)).isTrue();
	}

	@Test
	public void 넘어진_개수로_핀생성() {
		assertThat(Pin.fromKnockedPinCount(7)).isEqualTo(Pin.of(3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void 잘못된_핀생성_최대() {
		Pin pin = Pin.of(Pin.MAX_COUNT + 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void 잘못된_핀생성_최소() {
		Pin pin = Pin.of(Pin.MIN_COUNT - 1);
	}

	@Test
	public void 사용자가_넘어트린_핀반영() {
		Pin pin = Pin.ready();

		assertThat(pin.knockDown(3)).isEqualTo(Pin.of(7));
	}

	@Test
	public void 모든핀을_쓰러트렸을때() {
		Pin remainingPin = Pin.ready().knockDown(Pin.MAX_COUNT);

		assertThat(remainingPin.isAllKnockedDown()).isTrue();
	}

	@Test
	public void 모든핀이_서있을때() {
		Pin remainingPin = Pin.ready().knockDown(Pin.MIN_COUNT);

		assertThat(remainingPin.isAllStanding()).isTrue();
	}

	@Test(expected = IllegalArgumentException.class)
	public void 남은핀보다_더많은핀을_쓰러트린경우() {
		Pin.ready().knockDown(Pin.MAX_COUNT + 1);
	}
}