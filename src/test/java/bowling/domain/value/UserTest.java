package bowling.domain.value;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    void createTest() {
        User user = new User("LEE");

        assertThat(user).isEqualTo(new User("LEE"));
    }

    @Test
    @DisplayName("이름이 3글자를 넘어갈 때 오류 발생 검증")
    void nameExceptionTest() {
        assertThatThrownBy(() ->
                new User("TEST")).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("이름 생성할 null값이 들어가면 오류")
    @NullSource
    void nameExceptionTest(String inputData) {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new User(inputData));
    }

}
