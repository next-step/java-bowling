package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Pitch: 각 프레임 별 점수")
class PitchTest {

    @DisplayName("넘긴 핀 수에 따라 남은 핀수가 감소한다")
    @ParameterizedTest
    @MethodSource("source_add_DecreaseRemain")
    public void add_DecreaseRemain(Pin pin, int expected) {
        Pitch pitch = new Pitch();
        pitch.add(pin);
        assertThat(pitch.getRemain()).isEqualTo(expected);
    }

    public static Stream<Arguments> source_add_DecreaseRemain() {
        return Stream.of(
                Arguments.of(new Pin(1), 9),
                Arguments.of(new Pin(5), 5),
                Arguments.of(new Pin(9), 1),
                Arguments.of(new Pin(10), 0));
    }

    @DisplayName("최대 횟수 이상 투구하면 예외 발생")
    @Test
    public void add_WithPinOverMaxThowCount_ExceptionThrown() {
        Pitch pitch = new Pitch();
        pitch.add(new Pin(5));
        pitch.add(new Pin(5));
        assertThatThrownBy(() -> {
            pitch.add(new Pin(5));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("남은 핀 수보다 많이 넘기면 예외 발생")
    @Test
    public void add_WithPinOverRemain_ExceptionThrown() {
        Pitch pitch = new Pitch();
        pitch.add(new Pin(5));
        assertThatThrownBy(() -> {
            pitch.add(new Pin(10));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1번 던져서 넘긴 핀이 10개면 Strike 이력 추가")
    @Test
    public void getShotHistory_OneTime_ReturnStrike() {
        Pitch pitch = new Pitch();
        pitch.add(new Pin(10));
        assertThat(pitch.getShotHistory().contains(Shot.STRIKE)).isTrue();
    }

    @DisplayName("1번 던져서 넘긴 핀이 10개 미만 Miss 이력 추가가")
    @Test
    public void getShotHistory_OneTime_ReturnMiss() {
        Pitch pitch = new Pitch();
        pitch.add(new Pin(5));
        assertThat(pitch.getShotHistory().contains(Shot.FIVE)).isTrue();
    }

    @DisplayName("2번 던져서 넘긴 핀이 10개면 Miss,Spare 이력 추가")
    @Test
    public void getShotHistory_TwoTime_ReturnMissAndSpare() {
        Pitch pitch = new Pitch();
        pitch.add(new Pin(5));
        pitch.add(new Pin(5));
        assertThat(pitch.getShotHistory().contains(Shot.FIVE)).isTrue();
        assertThat(pitch.getShotHistory().contains(Shot.SPARE)).isTrue();
    }

    @DisplayName("2번 던져서 넘긴 핀이 10개 미만 Miss,Miss 이력 추가")
    @Test
    public void getShotHistory_TwoTime_ReturnMissAndMis() {
        Pitch pitch = new Pitch();
        pitch.add(new Pin(5));
        pitch.add(new Pin(4));
        assertThat(pitch.getShotHistory().contains(Shot.FIVE)).isTrue();
        assertThat(pitch.getShotHistory().contains(Shot.FOUR)).isTrue();
    }

    @DisplayName("스코어 추가 시 마다 모든 공을 처리한건지 여부를 반환")
    @Test
    public void add_ReturnIsPitchEnd() {
        Pitch pitch = new Pitch();

        pitch.add(new Pin(5));
        assertThat(pitch.isFinish()).isFalse();

        pitch.add(new Pin(5));
        assertThat(pitch.isFinish()).isTrue();
    }
}
