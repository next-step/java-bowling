package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NameTest {

    @Test
    void 생성() {
        Name name = Name.of("nam");
        assertThat(name).isEqualTo(Name.of("nam"));
    }

    @Test
    void 생성_실패_글자수() {
        assertThatIllegalArgumentException().isThrownBy(() -> Name.of("name"));
    }

    @Test
    void 생성_실패_한글() {
        assertThatIllegalArgumentException().isThrownBy(() -> Name.of("한글"));
    }

    @Test
    void 생성_실패_숫자() {
        assertThatIllegalArgumentException().isThrownBy(() -> Name.of("123"));
    }
}