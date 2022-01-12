package bowling.domain.state.end;

import bowling.domain.Pins;
import bowling.domain.state.ThrowingState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MissTest {

    @DisplayName("미스가 아닌 두 번의 투구로 미스가 생성될 때")
    @Test
    void 예외_미스가_아닌_경우() {
        assertThatThrownBy(() -> Miss.create(new Pins(5), new Pins(5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("미스는 두번의 투구로 %d개 미만이어야 합니다.", Pins.MAX_RANGE));
    }

    @DisplayName("한 프레임에서 미스인데 투구할 때")
    @Test
    void 예외_스페어인데_투구() {
        // given
        ThrowingState state = Miss.create(new Pins(1), new Pins(3));

        // when
        assertThatThrownBy(() -> state.bowl(new Pins(1)))
                // then
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("프레임이 끝난 상태는 투구할 수 없습니다.");
    }

    @Test
    void 투구_끝_여부_확인() {
        ThrowingState state = Miss.create(new Pins(1), new Pins(4));
        assertThat(state.isEnd()).isTrue();
    }

    @TestFactory
    Stream<DynamicTest> 심볼_확인() {
        return Stream.of(
                dynamicTest("두 번의 투구 모두 쓰러트렸을 때", () -> {
                    ThrowingState state = Miss.create(new Pins(1), new Pins(4));
                    assertThat(state.symbol()).isEqualTo("1|4");
                }),
                dynamicTest("첫 번째 투구는 거터일 때", () -> {
                    ThrowingState state = Miss.create(new Pins(0), new Pins(1));
                    assertThat(state.symbol()).isEqualTo("-|1");
                }),
                dynamicTest("두 번째 투구는 거터일 때", () -> {
                    ThrowingState state = Miss.create(new Pins(1), new Pins(0));
                    assertThat(state.symbol()).isEqualTo("1|-");
                }),
                dynamicTest("모든 투구가 거터일 때", () -> {
                    ThrowingState state = Miss.create(new Pins(0), new Pins(0));
                    assertThat(state.symbol()).isEqualTo("-|-");
                })
        );
    }
}