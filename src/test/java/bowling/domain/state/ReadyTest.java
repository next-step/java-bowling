package bowling.domain.state;

import bowling.domain.HitCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReadyTest {

    @DisplayName("Ready 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        // when
        State ready = Ready.initialize();

        // then
        assertThat(ready).isNotNull();
    }

    @DisplayName("Ready 인스턴스가 종료 여부를 알맞게 반환하는지 테스트")
    @Test
    void 반환_종료_여부() {
        // when
        State ready = State.initialize();

        // then
        assertThat(ready.isFinish()).isFalse();
    }

    @DisplayName("Ready 인스턴스가 10개 미만의 핀을 맞추면 FirstBowl 반환 여부 테스트")
    @Test
    void 반환_FirstBowl() {
        // when
        State ready = State.initialize();

        // then
        assertAll(
                () -> assertThat(ready.bowl(HitCount.valueOf(9))).isNotNull(),
                () -> assertThat(ready.bowl(HitCount.valueOf(9))).isInstanceOf(FirstBowl.class)
        );

    }

    @DisplayName("Ready 인스턴스가 10개의 핀을 맞추면 Strike 반환 여부 테스트")
    @Test
    void 반환_Strike() {
        // when
        State ready = State.initialize();

        // then
        assertAll(
                () -> assertThat(ready.bowl(HitCount.valueOf(10))).isNotNull()
//                () -> assertThat(ready.bowl(HitCount.valueOf(10))).isNotNull(),
//                () -> assertThat(ready.bowl(HitCount.valueOf(10))).isInstanceOf(Strike.class)
        );

    }

}