package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NameTest {


    @Test
    @DisplayName("플레이어 생성시 이름의 길이가 3을 초과하면 IllegalArgumentException 이 발생한다.")
    void validateLengthOver() {
        String name = "harr";
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessage("이름의 길이가 1이상 3이하로 입력해야 합니다. 이름 : " + name);
    }

    @Test
    @DisplayName("플레이어 생성시 이름의 길이가 1보다 작으면 IllegalArgumentException 이 발생한다.")
    void validateLengthUnder() {
        String name = "";
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessage("이름의 길이가 1이상 3이하로 입력해야 합니다. 이름 : " + name);
    }

}