package bowling.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

	@DisplayName("Index 의 최대값인지 검증")
	@Test
	void isMax() {
		// given
		Index first = Index.first();
		Index maxIndex = Index.create(Index.MAX_OF_INDEX);

		// when
		boolean firstResult = first.isMax();
		boolean maxIndexResult = maxIndex.isMax();

		// then
		assertAll(
			() -> assertThat(firstResult).isFalse(),
			() -> assertThat(maxIndexResult).isTrue()
		);
	}

	@DisplayName("next 호출시 최대값을 넘을 경우 예외 발생")
	@Test
	void nextInvalid() {
		// given
		Index maxIndex = Index.create(Index.MAX_OF_INDEX);

		// when then
		assertThatIllegalStateException().isThrownBy(maxIndex::next);
	}

	@DisplayName("next 호출시 반환되는 Index 검증")
	@Test
	void next() {
		// given
		Index first = Index.first();

		// when
		Index next = first.next();

		// then
		assertThat(next).isEqualTo(Index.create(Index.MIN_OF_INDEX + 1));
	}
}
