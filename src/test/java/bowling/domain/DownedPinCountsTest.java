package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.DownedPinCount.*;
import static bowling.domain.DownedPinCounts.MAX_PIN_COUNT;
import static bowling.domain.DownedPinCounts.OVER_MAX_PIN_COUNT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DownedPinCountsTest {

	@Test
	void construct() {
		DownedPinCounts downedPinCounts = new DownedPinCounts();
		downedPinCounts.add(DownedPinCount.ONE);
		assertThat(downedPinCounts).isEqualTo(new DownedPinCounts(DownedPinCount.ONE));
	}

	@DisplayName("10개 핀이 다 쓰러진 상태에서 더 쓰러뜨리려고 하면 Exception 테스트")
	@Test
	void failAddTest() {
		DownedPinCounts downedPinCounts = new DownedPinCounts(TEN);
		assertThatThrownBy(() -> downedPinCounts.add(ONE))
				.isInstanceOf(BowlingException.class)
				.hasMessage(String.format(OVER_MAX_PIN_COUNT, MAX_PIN_COUNT));
	}

	@DisplayName("쓰러진 핀의 합계를 계산하는 테스트")
	@Test
	void sumTest() {
		DownedPinCounts downedPinCounts = new DownedPinCounts(FIVE);
		downedPinCounts.add(FOUR);
		assertThat(downedPinCounts.getDownedPinCountSum()).isEqualTo(9);
	}

	@DisplayName("10개의 핀이 다 쓰러졌으면 true를 반환")
	@Test
	void isAllPinDowned() {
		DownedPinCounts downedPinCounts1 = new DownedPinCounts(TEN);
		DownedPinCounts downedPinCounts2 = new DownedPinCounts(FIVE);
		downedPinCounts2.add(FIVE);
		assertThat(downedPinCounts1.isAllPinDowned()).isTrue();
		assertThat(downedPinCounts2.isAllPinDowned()).isTrue();
	}

}
