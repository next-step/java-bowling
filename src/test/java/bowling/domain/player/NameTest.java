package bowling.domain.player;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @Test
    @DisplayName("객체 생성")
    void construct() {
        //given
        String name = "ABC";

        //when
        Name actual = new Name(name);

        //then
        assertThat(actual).isEqualTo(new Name(name));

    }

    @ParameterizedTest(name = "이름 길이 {index} [{arguments}]")
    @CsvSource(value = {
            "ABCD,4",
            ",0"
    })
    @DisplayName("이름 길이 초과 또는 미달")
    void length_exception(String name, int lengthOfName) {
        //given

        //when
        ThrowableAssert.ThrowingCallable actual = () -> new Name(name);

        //then
        assertThatThrownBy(actual).isInstanceOf(NameLengthException.class)
                .hasMessage("이름의 길이는 3자만 가능합니다. 현재 이름 길이 : " + lengthOfName);

    }

    @Test
    @DisplayName("이름 영문만 허용")
    void letters_exception() {
        //given
        String name = "홍길동";

        //when
        ThrowableAssert.ThrowingCallable actual = () -> new Name(name);

        //then
        assertThatThrownBy(actual).isInstanceOf(NameLettersException.class)
                .hasMessage("이름은 영문만 가능합니다.");

    }

}
