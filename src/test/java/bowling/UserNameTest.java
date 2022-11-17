package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UserNameTest {

    @Test
    void 이름은_세글자가_아니면_에러() {
        Assertions.assertThatThrownBy(() -> new UserName("a"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
