package bowling.domain.name;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NamesTest {

    @Test
    void duplicate_name() {
        Names names = Names.init();
        names.addName(new Name("TES"));
        assertThatThrownBy(() -> names.addName(new Name("TES")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 사용자와 동일한 이름을 사용할 수 없습니다.");
    }

}
