package bowlinggame.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class FrameNumberTest {

	@Test
	public void 동일_객체_확인() {
		assertThat(FrameNumber.of(1) == FrameNumber.of(1)).isTrue();
	}

	@Test(expected = IllegalArgumentException.class)
	public void 최소_잘못된_프레임번호() {
		FrameNumber.of(FrameNumber.FIRST - 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void 최대_잘못된_프레임번호() {
		FrameNumber.of(FrameNumber.LAST + 1);
	}

	@Test
	public void 프레임증가() {
		FrameNumber first = FrameNumber.first();
		assertThat(first.next()).isEqualTo(FrameNumber.of(2));
	}

	@Test
	public void 마지막_프레임은_번호가_증가하지않음() {
		FrameNumber last = FrameNumber.last();
		assertThat(last.next()).isEqualTo(last);
	}
}