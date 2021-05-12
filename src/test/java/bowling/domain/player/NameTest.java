package bowling.domain.player;

import bowling.exception.InvalidNameSizeException;
import bowling.exception.NameIncludeVariableLanguageException;
import bowling.exception.StringNullPointerException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @DisplayName("Name 인스턴스 생성에 관한 테스트")
    @Test
    void 생성() {
        // given
        String stringName = "kwj";

        // when
        Name name = new Name(stringName);

        // then
        assertThat(name).isNotNull();
    }

    @DisplayName("Name 인스턴스 생성시 들어오는 값이 null 인 경우, 예외 처리 구현 테스트")
    @Test
    void 검증_null() {
        // given
        String stringName = null;

        // when and then
        assertThatThrownBy(() -> new Name(stringName))
                .isInstanceOf(StringNullPointerException.class)
                .hasMessage("String 인스턴스가 null 입니다.");
    }

    @DisplayName("Name 인스턴스 생성시 들어오는 값이 다른 언어를 사용하는 경우 예외처리 테스트")
    @Test
    void 검증_영어_외_다른_언어_사용() {
        // given
        String stringName = "김우재";

        // when and then
        assertThatThrownBy(() -> new Name(stringName))
                .isInstanceOf(NameIncludeVariableLanguageException.class)
                .hasMessage("이름에 영어 이외에 다른 언어들이 섞여있습니다.");
    }

    @DisplayName("Name 인스턴스 생성시 들어오는 값이 빈 값이거나 3자리를 초과하는 경우 예외처리 테스트")
    @Test
    void 검증_크기() {
        // given
        String emptyName = "";
        String overSizeName = "wooji";

        // when and then
        assertThatThrownBy(() -> new Name(emptyName))
                .isInstanceOf(InvalidNameSizeException.class)
                .hasMessage("알맞는 이름의 크기가 아닙니다.");

        assertThatThrownBy(() -> new Name(overSizeName))
                .isInstanceOf(InvalidNameSizeException.class)
                .hasMessage("알맞는 이름의 크기가 아닙니다.");
    }

    @DisplayName("Name 인스턴스가 소유한 이름 값을 반환하는지 테스트")
    @Test
    void 반환_이름() {
        // given
        String expected = "kwj";

        // when
        Name name = new Name(expected);

        // then
        assertThat(name.name()).isEqualTo(expected);
    }


}