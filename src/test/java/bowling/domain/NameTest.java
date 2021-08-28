package bowling.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import bowling.domain.exception.NameCreateException;

@DisplayName("이름")
class NameTest {

	@DisplayName("[성공] 생성")
	@ParameterizedTest
	@CsvSource({
		"PJS", "pjs", "pJs",
		"CBH", "cbh", "CbH",
	})
	void create(final String input) {
		// given

		// when
		final Name name = Name.of(input);

		// then
		assertThat(name).isNotNull();
	}

	@DisplayName("[실패] 생성 - 유효하지 않은 이름")
	@ParameterizedTest
	@CsvSource({
		"PJSS", "p", "PJ", "''"
	})
	void create_invalidName(final String input) {
		// given

		// when
		assertThrows(NameCreateException.class, () -> Name.of(input));

		// then
	}
}
