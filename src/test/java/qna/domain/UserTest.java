package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @ParameterizedTest
    @DisplayName("id와 pw가 일치한다면 update를 할 수 있다")
    @MethodSource
    void update(User origin, User update) {
        origin.update(origin, update);

        assertThat(origin).isEqualTo(update);
    }

    static Stream<Arguments> update() {
        return Stream.of(
                Arguments.of(
                        JAVAJIGI
                        ,new User(1L, "javajigi", "password", "updatename", "update@slipp.net")
                ),
                Arguments.of(
                        SANJIGI,
                        new User(2L, "sanjigi", "password", "change", "change@slipp.net")
                )
        );
    }

}
