package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class NameTest {

    @Test
    @DisplayName("3자리 알파벳을 인자로 받아 이름을 생성한다.")
    public void create() throws Exception {
        Name name = new Name("KSB");
        assertThat(name).isEqualTo(new Name("KSB"));
    }

    @ParameterizedTest()
    @ValueSource(strings = {"성빈", "123"})
    @DisplayName("영문 이름이 아닐 경우 예외가 발생한다.")
    public void checkAlphabet(String name) throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessage("영문 이름인지 확인해주세요.");

    }

    @ParameterizedTest
    @ValueSource(strings = {"ab", "abcd"})
    @DisplayName("이름이 3자리가 아닐 경우 예외가 발생한다.")
    public void checkLength(String name) throws Exception {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessage("이름이 3자리인지 확인해주세요.");
    }
}
