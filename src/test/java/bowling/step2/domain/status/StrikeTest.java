package bowling.step2.domain.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {

	@DisplayName(value = "스트라이크 투구 확인")
	@Test
	void bowlingStrike() {
		Status status = new Strike();
		assertThat(status).isInstanceOf(Strike.class);
		assertThat(status.getMark()).isEqualTo("X");
	}
}
