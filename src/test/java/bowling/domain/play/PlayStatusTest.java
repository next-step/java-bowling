package bowling.domain.play;

import bowling.domain.Roll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlayStatusTest {
    private PlayStatus status;

    @BeforeEach
    void setUp() {
        status = new PlayStatus();
    }

    @ParameterizedTest
    @DisplayName("initial 에는 항상 true 이다.")
    @ValueSource(ints = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14})
    void initial(int frameNo) {
        assertThat(status.isContinue(frameNo))
                .isTrue();
    }

    @Test
    @DisplayName("strike 일 경우에는 false 이다.")
    void strike() {
        int frameNo = 3;
        assertAll(
                () -> {
                    assertThat(status.isContinue(frameNo))
                            .isTrue();
                    status.addRoll(Roll.of(10));
                },
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse()
        );
    }

    @ParameterizedTest
    @DisplayName("strike 가 아닌 경우에는 true 이다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void not_strike(int roll) {
        int frameNo = 3;
        assertAll(
                () -> {
                    assertThat(status.isContinue(frameNo))
                            .isTrue();
                    status.addRoll(Roll.of(roll));
                },
                () -> assertThat(status.isContinue(frameNo))
                        .isTrue()
        );
    }

    @Test
    @DisplayName("마지막이 strike 이면 두번 연속까지만 true 이다.")
    void last_strike() {
        int frameNo = 10;
        assertAll(
                () -> {
                    assertThat(status.isContinue(frameNo))
                            .isTrue();
                    status.addRoll(Roll.of(10));
                },
                () -> assertThat(status.isContinue(frameNo))
                        .isTrue(),
                () -> assertThat(status.isContinue(frameNo))
                        .isTrue(),
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse(),
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse()
        );
    }

    @Test
    @DisplayName("마지막이 spare 이면 한번 연속까지만 true 이다.")
    void last_spare() {
        int frameNo = 10;
        assertAll(
                () -> assertThat(status.isContinue(frameNo))
                        .isTrue(),
                () -> {
                    status.addRoll(Roll.of(5));
                    assertThat(status.isContinue(frameNo))
                            .isTrue();
                    status.addRoll(Roll.of(5));
                },
                () -> assertThat(status.isContinue(frameNo))
                        .isTrue(),
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse(),
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse(),
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse()
        );
    }

    @Test
    @DisplayName("마지막이 miss 이면 계속 false 이다.")
    void last_miss() {
        int frameNo = 10;
        assertAll(
                () -> assertThat(status.isContinue(frameNo))
                        .isTrue(),
                () -> {
                    status.addRoll(Roll.of(3));
                    assertThat(status.isContinue(frameNo))
                            .isTrue();
                    status.addRoll(Roll.of(2));
                },
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse(),
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse(),
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse(),
                () -> assertThat(status.isContinue(frameNo))
                        .isFalse()
        );
    }
}
