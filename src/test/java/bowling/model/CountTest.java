package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CountTest {

	@Test
	@DisplayName("카운트를 생성한다.")
	public void createCount() {
		Count count = new Count(4);

		assertThat(count).isEqualTo(new Count(4));
	}

	@ParameterizedTest
	@DisplayName("카운트는 0보다 크고 4보다 작거나 같다.")
	@CsvSource(value = {"0", "5"})
	public void checkCountRange(int count) {
		assertThatThrownBy(() -> new Count(count))
			.isInstanceOf(IllegalArgumentException.class);
	}

}