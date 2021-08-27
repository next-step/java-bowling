package bowling.composer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RegexComposerTest {

	@ParameterizedTest
	@DisplayName("해당 문자가 영문인지 아닌지 판별한다.")
	@CsvSource(value = {"kim,true", "김,false", "!!@k,false"})
	public void isMatched(String name, boolean matched) {
		assertThat(RegexComposer.isMatched(name)).isEqualTo(matched);
	}
}