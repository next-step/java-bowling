package bowling.domain.player;

import bowling.domain.player.exception.NameLengthException;
import bowling.domain.player.exception.NameEngException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {

    @ParameterizedTest(name = "이름 입력 예외 처리")
    @MethodSource("nameLengthExceptionCondition")
    void nameLengthException(String name, Class<RuntimeException> clazz) {
        assertThatThrownBy(() -> Player.of(name)).isExactlyInstanceOf(clazz);
    }

    private static Stream<Arguments> nameLengthExceptionCondition() {
        return Stream.of(
                Arguments.of("abcd", NameLengthException.class),
                Arguments.of("ㄱㄴㄷ", NameEngException.class)
        );
    }
}
