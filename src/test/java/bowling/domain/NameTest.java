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

    @DisplayName("Name 인스턴스가 null 인 경우, 예외 처리 구현 테스트")
    @Test
    void 검증_null() {
        // given
        String stringName = null;

        // when and then
        assertThatThrownBy(() -> Name.valueOf(stringName))
                .isInstanceOf(StringNullPointerException.class)
                .hasMessage("String 인스턴스가 null 입니다.");
    }

    @DisplayName("Name 인스턴스가 다른 언어를 사용하는 경우 예외처리 테스트")
    @Test
    void 검증_영어_외_다른_언어_사용() {
        // given
        String stringName = "김우재";

        // when and then
        assertThatThrownBy(() -> Name.valueOf(stringName))
                .isInstanceOf(NameIncludeVariableLanguage.class)
                .hasMessage("이름에 영어 외 다른 언어들이 섞여있습니다.");
    }

}