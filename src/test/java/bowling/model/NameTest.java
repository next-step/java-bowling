package bowling.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NameTest {

	@Test
	@DisplayName("이름을 입력하면 이름이 생성된다.")
	public void createName() {
		Name name = new Name("abc");

		assertAll(
			() -> assertThat(name).isEqualTo(new Name("abc")),
			() -> assertThat(name.getName()).isEqualTo("abc")
		);
	}

	@ParameterizedTest
	@DisplayName("이름이 3글자가 아니면 예외가 발생한다.")
	@CsvSource(value = {"ab", "cora"})
	public void checkNameLength(String name) {
		assertThatThrownBy(() -> new Name(name))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("이름이 영어가 아니면 예외가 발생한다.")
	public void checkNameTypeMatch() {
		assertThatThrownBy(() -> new Name("a!ㅁ"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("이름이 빈값이면 예외가 발생한다.")
	public void checkNameEmpty() {
		assertThatThrownBy(() -> new Name(""))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("이름이 Null 이면 예외가 발생한다.")
	public void checkNameNull() {
		assertThatThrownBy(() -> new Name(null))
			.isInstanceOf(IllegalArgumentException.class);
	}

}