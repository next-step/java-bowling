package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("프레임 투구 객체 테스트")
public class FrameBowlTest {

    @DisplayName("생성 테스트")
    @Test
    public void create() {
        FrameBowl frameBowl = new FrameBowl();
        // TODO: 생성 테스트 추가, 없으면 삭제
    }

    @DisplayName("투구 테스트")
    @Test
    public void bowl() {
        FrameBowl frameBowl = new FrameBowl();
        frameBowl.bowl(5);
        // TODO: 투구 테스트 추가, 없으면 삭제
    }

    @DisplayName("투구 유효성 테스트: 0보다 작거나 10보다 큰 수 입력")
    @ParameterizedTest
    @ValueSource(ints = { -5, 12 })
    public void validate_bowl(int numberOfPins) {
        FrameBowl frameBowl = new FrameBowl();
        assertThatIllegalArgumentException().isThrownBy(() -> frameBowl.bowl(numberOfPins));
    }

    @DisplayName("투구 수 테스트")
    @Test
    public void getBowlCount() {
        FrameBowl frameBowl = new FrameBowl();
        frameBowl.bowl(5);
        assertThat(frameBowl.getBowlCount()).isEqualTo(1);
    }

    @DisplayName("전체 핀 갯수 테스트")
    @Test
    public void getTotalNumberOfPins() {
        FrameBowl frameBowl = new FrameBowl();
        frameBowl.bowl(5);
        frameBowl.bowl(3);
        assertThat(frameBowl.getTotalNumberOfPins()).isEqualTo(8);
    }

}
