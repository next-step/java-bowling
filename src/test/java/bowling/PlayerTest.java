package bowling;

import bowling.domain.Player;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class PlayerTest {

    @Test
    void 소문자_대문자_변환() {
        assertThat(new Player("bd")).isEqualTo(new Player("BD"));
    }

    @TestFactory
    Stream<DynamicTest> 예외_이름_최대_최소_글자_수() {
        String exception_message = "이름은 %d글자 이상 %d글자 이하만 가능합니다.";
        return Stream.of(
                dynamicTest("최소 1글자 : 빈 값", () ->
                        assertThatThrownBy(() -> new Player(""))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage(String.format(exception_message, Player.MIN_LENGTH, Player.MAX_LENGTH))
                ),
                dynamicTest("최소 1글자 : 공백", () ->
                            assertThatThrownBy(() -> new Player(" "))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage(String.format(exception_message, Player.MIN_LENGTH, Player.MAX_LENGTH))
                ),
                dynamicTest("최소 1글자 : null", () ->
                            assertThatThrownBy(() -> new Player(null))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage(String.format(exception_message, Player.MIN_LENGTH, Player.MAX_LENGTH))
                )
        );
    }

    @TestFactory
    Stream<DynamicTest> 예외_이름_영문_이외() {
        return Stream.of(
                dynamicTest("특수 문자", () ->
                            assertThatThrownBy(() -> new Player("%"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage(Player.EXCEPTION_MESSAGE_ENGLISH_NAME)
                ),
                dynamicTest("숫자", () ->
                            assertThatThrownBy(() -> new Player("1"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage(Player.EXCEPTION_MESSAGE_ENGLISH_NAME)
                ),
                dynamicTest("한글", () ->
                            assertThatThrownBy(() -> new Player("덕"))
                            .isInstanceOf(IllegalArgumentException.class)
                            .hasMessage(Player.EXCEPTION_MESSAGE_ENGLISH_NAME)
                )
        );
    }
}