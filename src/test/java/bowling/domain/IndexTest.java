package bowling.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.exception.IndexRangeException;

class IndexTest {
	@DisplayName("프레임의 Index 의 범위가 아닐 경우 예외 발생")
	@ParameterizedTest
	@ValueSource(ints = {Index.MIN_OF_INDEX - 1, Index.MAX_OF_INDEX + 1})
	void createWithInvalidName(int invalidRangeValue) {
		// when then
		assertThatExceptionOfType(IndexRangeException.class)
			.isThrownBy(() -> Index.create(invalidRangeValue));
	}
}
