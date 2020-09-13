package bowling.domain;

import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

import static bowling.domain.DownedPinCount.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DownedPinCountTest {

	@DisplayName("int 값에 맞는 DownedPinCount를 얻을 수 있는지 테스트")
	@Test
	void from() {
		assertThat(DownedPinCount.from(0)).isEqualTo(ZERO);
		assertThat(DownedPinCount.from(1)).isEqualTo(ONE);
		assertThat(DownedPinCount.from(10)).isEqualTo(TEN);
	}

	@DisplayName("0에서 10 사이의 값이 아닌 int value를 입력하면 Exception이 발생하는지 테스트")
	@Test
	void failFrom() {
		int given = 11;
		assertThatThrownBy(() -> DownedPinCount.from(given))
				.isInstanceOf(BowlingException.class)
				.hasMessage(String.format("쓰러진 핀 수는 0 이상 10 이하의 자연수입니다. 입력 된 수 : %d",given));
	}
}
