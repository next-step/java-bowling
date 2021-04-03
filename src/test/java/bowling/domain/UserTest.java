package bowling.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("참여자 이름 3글자 초과 에러 확인")
    public void userNameMaxLength3OverException() throws Exception {
        assertThatThrownBy(() -> new User("povi"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PHC", "POV", "D"})
    @DisplayName("이름 확인")
    public void name(String input) throws Exception {
        //given
        User user = new User(input);
        //when

        //then
        assertThat(user.name()).isEqualTo(input);
    }
}
