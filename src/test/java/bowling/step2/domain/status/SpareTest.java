package bowling.step2.domain.status;

import bowling.step2.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SpareTest {

	@DisplayName(value = "스트라이크 값 입력 시 에러 발생")
	@Test
	void bowlingException() {
		assertThatIllegalArgumentException().isThrownBy(() -> new Spare(new Pin(10)))
				.withMessage("스트라이크는 Spare 상태일 수 없습니다.");
	}

	@DisplayName(value = "스페어 투구 확인")
	@Test
	void bowlingSpare() {
		Status status = new Spare(new Pin(5));
		assertThat(status).isInstanceOf(Spare.class);
		assertThat(status.getMark()).isEqualTo("5|/");
	}
}
