package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class BowlingUserTest {

    @DisplayName("유저 정상 생성 테스트")
    @Test
    void create() {
        // given
        String name = "KHS";

        // when
        BowlingUser bowlingUser = BowlingUser.from(name);

        // then
        assertThat(bowlingUser.getName()).isEqualTo(name);
    }

    @DisplayName("유저 생성 실패 - 이름이 영어가 아닌경우")
    @ParameterizedTest
    @ValueSource(strings = {"s20", "김형석", "&^%"})
    void create_fail_not_eng(String name) {
        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> BowlingUser.from(name));
    }

    @DisplayName("유저 생성 실패 - 이름이 3자를 초과하는 경우")
    @ParameterizedTest
    @ValueSource(strings = {"ssss", "kimhs", "testtest"})
    void create_fail_exceed_length(String name) {
        // when & then
        assertThatIllegalArgumentException().isThrownBy(() -> BowlingUser.from(name));
    }

    @DisplayName("유저 생성시 소문자입력을 대문자로 바꿔주는지 테스트")
    @ParameterizedTest
    @MethodSource("provideLowerCaseAndResult")
    void convert_upper_case(String name, String result) {
        // when
        BowlingUser bowlingUser = BowlingUser.from(name);

        // then
        assertThat(bowlingUser.getName()).isEqualTo(result);
    }

    private static Stream<Arguments> provideLowerCaseAndResult() {
        return Stream.of(
                Arguments.of("khs", "KHS"),
                Arguments.of("kHs", "KHS"),
                Arguments.of("Khs", "KHS")
        );
    }
}