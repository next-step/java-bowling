package bowling.step2.domain.status;

import bowling.step2.domain.Pin;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class MissTest {

	@DisplayName(value = "미스 상태가 될 수 없는 핀 개수 입력 시 에러 발생")
	@ParameterizedTest
	@CsvSource(value = {"3:7", "10:0", "0:10"}, delimiter = ':')
	public void MissException(int first, int second) {
		assertThatIllegalArgumentException()
				.isThrownBy(() -> new Miss(new Pin(first), new Pin(second)))
				.withMessage("입력하신 핀 개수는 Miss가 아닙니다.");
	}

	@DisplayName(value = "미스 투구 결과")
	@Test
	public void MissBowling() {
		Status status = new Miss(new Pin(3), new Pin(5));
		assertThat(status).isInstanceOf(Miss.class);
		assertThat(status.isFinished()).isTrue();
		assertThat(status.getMark()).isEqualTo("3|5");
	}
}
