package bowling.domain.name;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NamesTest {

    @Test
    void duplicate_name() {
        List<Name> nameList = new ArrayList<>();
        assertThatThrownBy(() -> {
            new Names(List.of(new Name("ABC"), new Name("ABC")));
        })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("다른 사용자와 동일한 이름을 사용할 수 없습니다.");
    }

}
