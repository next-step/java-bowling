package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Validator 테스트")
class ValidatorTest {

    @DisplayName("플레이어 이름 체크 - 성공")
    @ParameterizedTest
    @ValueSource(strings = {"JPA", "KOR", "kim", "jun"})
    void checkPlayerName_성공(String name) {
        assertThat(Validator.checkPlayerName(name)).isEqualTo(name.toUpperCase());
    }

    @DisplayName("플레이어 이름 체크 - 실패(글자수 초과)")
    @ParameterizedTest
    @ValueSource(strings = {"JAVA", "TEST", "kys9261"})
    void checkPlayerName_실패1(String name) {
        assertThatThrownBy(() -> Validator.checkPlayerName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 3자를 넘을 수 없습니다.");
    }

    @DisplayName("플레이어 이름 체크 - 실패(영어외 문자)")
    @ParameterizedTest
    @ValueSource(strings = {"테스트", "이름1", "이름a"})
    void checkPlayerName_실패2(String name) {
        assertThatThrownBy(() -> Validator.checkPlayerName(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름은 영어만 가능합니다.");
    }

    @DisplayName("쓰러진 볼링핀 입력값 체크 - 정상")
    @ParameterizedTest
    @ValueSource(ints = {3, 6, 8, 10, 0})
    void checkHitPinCount_성공(int hitPinCount) {
        assertThat(Validator.checkHitPinCount(hitPinCount)).isEqualTo(hitPinCount);
    }

    @DisplayName("쓰러진 볼링핀 입력값 체크 - 실패")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11, 100})
    void checkHitPinCount_실패(int hitPinCount) {
        assertThatThrownBy(() -> Validator.checkHitPinCount(hitPinCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("쓰러진 볼링핀은 0~10개중 하나만 입력 가능합니다.");
    }

    @DisplayName("프레임당 두번의 투구에서 쓰러트린 볼링핀 체크 - 성공")
    @ParameterizedTest
    @CsvSource(delimiter = ':', value = {"9:1", "10:0", "4:3"})
    void checkFrameHitPinCount_성공(int score1, int score2) {
        assertThatCode(() -> Validator.checkFrameHitPinCount(score1, score2))
                .doesNotThrowAnyException();
    }

    @DisplayName("프레임당 두번의 투구에서 쓰러트린 볼링핀 체크 - 실패")
    @ParameterizedTest
    @CsvSource(delimiter = ':', value = {"10:1", "9:3", "5:6"})
    void checkFrameHitPinCount_실패(int score1, int score2) {
        assertThatThrownBy(() -> Validator.checkFrameHitPinCount(score1, score2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("두번의 투구에서 쓰러진 볼링핀은 10개를 초과할 수 없습니다.");
    }
}
