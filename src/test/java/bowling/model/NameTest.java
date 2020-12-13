package bowling.model;

import bowling.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class NameTest {
    @Test
    void from_정상_이름(){
        assertThat(User.from("SHJ")).isEqualTo("SHJ");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A","AB", "ABCD"})
    void from_3글자가_아닌_이름(String errorUserName){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> User.from(errorUserName))
                .withMessage("플레이어의 이름은 3자만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"   ", "가나다", "123", "가1%"})
    void from_영어가_아닌_이름(String errorUserName){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> User.from(errorUserName))
                .withMessage("플레이어의 이름은 영어만 가능합니다.");
    }
}
