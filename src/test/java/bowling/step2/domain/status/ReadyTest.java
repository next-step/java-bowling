package bowling.step2.domain.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

	@DisplayName(value = "스트라이크 투구 확인")
	@Test
	void bowlingStrike() {
		Status status = new Ready();
		assertThat(status).isInstanceOf(Ready.class);
		status = status.bowling(10);
		assertThat(status).isInstanceOf(Strike.class);
	}

	@DisplayName(value = "스트라이크 외 투구 확인")
	@Test
	void bowlingOther() {
		Status status = new Ready();
		assertThat(status).isInstanceOf(Ready.class);
		status = status.bowling(5);
		assertThat(status).isInstanceOf(Running.class);
	}
}
