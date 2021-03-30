package bowling.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import bowling.exception.UserException;
import bowling.exception.UserExceptionMesssage;

public class NameTest {

	@DisplayName("Name 만들기 성공")
	@ParameterizedTest
	@ValueSource(strings = {"ABC", "BCD", "CE", "C", "EEE", "F"})
	void name_success(String input) {
		assertThat(new Name(input))
			.isInstanceOf(Name.class);
	}

	@DisplayName("Name 만들기 영어 아닌것")
	@ParameterizedTest
	@ValueSource(strings = {"!BC", "2CD", "ㅎE", "ㅎ"})
	void name_is_not_alphabet(String input) {
		assertThatExceptionOfType(UserException.class)
			.isThrownBy(() -> new Name(input))
			.withMessageMatching(UserExceptionMesssage.NAME_EXCEPTION.getMessage());
	}

	@DisplayName("Name 만들기 영어 아닌것")
	@ParameterizedTest
	@ValueSource(strings = {"ABCD", "DDDCD", "GGGGG", "EEEE"})
	void name_check_size(String input) {
		assertThatExceptionOfType(UserException.class)
			.isThrownBy(() -> new Name(input))
			.withMessageMatching(UserExceptionMesssage.NAME_EXCEPTION.getMessage());
	}

	@DisplayName("Name 만들기 빈문자열")
	@ParameterizedTest
	@ValueSource(strings = {"", " ", "  "})
	void name_isBlank(String input) {
		assertThatExceptionOfType(UserException.class)
			.isThrownBy(() -> new Name(input))
			.withMessageMatching(UserExceptionMesssage.NAME_EXCEPTION.getMessage());
	}
}
