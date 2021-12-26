package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class IndexTest {
	@DisplayName("isMax함수는 해당 Index가 마지막인지 boolean을 반환한다.")
	@Test
	void isMax() {
		// given
		Index first = Index.first();
		Index maxIndex = Index.of(Index.MAX_INDEX);

		// when
		boolean firstResult = first.max();
		boolean maxIndexResult = maxIndex.max();

		// then
		assertAll(
			() -> assertThat(firstResult).isFalse(),
			() -> assertThat(maxIndexResult).isTrue()
		);
	}
}
