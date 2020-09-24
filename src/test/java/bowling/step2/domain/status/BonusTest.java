package bowling.step2.domain.status;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BonusTest {

	@DisplayName(value = "보너스 투구 불가능한 경우에 보너스 생성 시 에러 발생")
	@Test
	public void bonusException() {
		assertThatIllegalArgumentException().isThrownBy(() -> new Bonus("3|3"))
				.withMessage("보너스 투구는 스트라이크나 스페어일 때만 가능합니다.");
	}

	@DisplayName(value = "보너스 투구 결과")
	@ParameterizedTest
	@CsvSource(value = {"X=X|X", "8|/=8|/|X"}, delimiter = '=')
	public void bonusBowling(String statusMark, String expected) {
		Status status = new Bonus(statusMark);
		status = status.bowling(10);
		assertThat(status).isInstanceOf(Bonus.class);
		assertThat(status.getMark()).isEqualTo(expected);
	}
}
