package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @DisplayName("Name 인스턴스 생성에 관한 테스트")
    @Test
    void 생성() {
        String stringName = "kwj";

        Name name = Name.valueOf(stringName);

        assertThat(name).isNotNull();
    }
}