import board.Name;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    void 생성() {
        String rawName = "PCI";
        Name name = new Name(rawName);

        assertThat(name).isEqualTo(new Name(rawName));
    }

    @Test
    void 세글자이상_이름입력시() {
        String invalidName = "CHAN";

        assertThatThrownBy(() -> new Name(invalidName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(invalidName + "은 3글자가 아닙니다.");
    }
}