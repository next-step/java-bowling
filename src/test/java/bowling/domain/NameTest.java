package bowling.domain;

import bowling.exception.StringNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @DisplayName("Name 인스턴스 생성에 관한 테스트")
    @Test
    void 생성() {
        // given
        String stringName = "kwj";

        // when
        Name name = Name.valueOf(stringName);

        // then
        assertThat(name).isNotNull();
    }

    @DisplayName("Name 인스턴스가 null 인지  테스트")
    @Test
    void 검증_null() {
        // given
        String stringName = null;

        // when and then
        assertThatThrownBy(() -> Name.valueOf(stringName))
                .isInstanceOf(StringNullPointerException.class)
                .hasMessage("String 인스턴스가 null 입니다.");
    }


}