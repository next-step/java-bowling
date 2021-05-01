package bowling.domain;

import bowling.exception.NoMoreBowlActionsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {

    @DisplayName("NormalFrame 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {
        Frame normalFrame = NormalFrame.initialize();

        assertAll(
                () -> assertThat(normalFrame).isNotNull(),
                () -> assertThat(normalFrame).isInstanceOf(NormalFrame.class)
        );

    }

    @DisplayName("NormalFrame 인스턴스가 인덱스로 생성 가능 여부 테스트")
    @Test
    void 생성_인덱스_값() {
        // given
        int index = 10;

        Frame normalFrame = NormalFrame.from(index);

        assertAll(
                () -> assertThat(normalFrame).isNotNull(),
                () -> assertThat(normalFrame).isInstanceOf(NormalFrame.class)
        );

    }

    @DisplayName("NormalFrame 인스턴스가 볼링시 자기 자신을 반환하는지 테스트")
    @Test
    void 기능_bowl_동일_인스턴스() {
        Frame normalFrame = NormalFrame.initialize();

        Frame now = normalFrame.bowl(HitCount.valueOf(5));

        assertAll(
                () -> assertThat(now).isNotNull(),
                () -> assertThat(now).isInstanceOf(NormalFrame.class),
                () -> assertThat(now).isSameAs(normalFrame),
                () -> assertThat(now).isEqualTo(normalFrame)
        );

    }

    @DisplayName("NormalFrame 인스턴스가 스트라이크시 자기 자신이 아닌 인스턴스를 반환하는지 테스트")
    @Test
    void 기능_bowl_다음_인스턴스() {
        Frame normalFrame = NormalFrame.initialize();

        Frame now = normalFrame.bowl(HitCount.valueOf(10));

        assertAll(
                () -> assertThat(now).isNotNull(),
                () -> assertThat(now).isInstanceOf(NormalFrame.class),
                () -> assertThat(now).isNotSameAs(normalFrame),
                () -> assertThat(now).isNotEqualTo(normalFrame)
        );

    }


    @DisplayName("NormalFrame 인스턴스가 종료되었는지 테스트")
    @Test
    void 반환_종료_여부() {
        // given
        Frame normalFrame = NormalFrame.initialize();

        // when
        Frame next = normalFrame.bowl(HitCount.valueOf(10));

        // then
        assertAll(
                () -> assertThat(normalFrame.isFinish()).isTrue(),
                () -> assertThat(next.isFinish()).isFalse()
        );

    }

    @DisplayName("NormalFrame 인스턴스가 완료되었는데도 볼링할 경우 예외처리 테스트")
    @Test
    void 검증_이미_종료된_상태에서의_bowl() {
        // given
        Frame normalFrame = NormalFrame.initialize();

        // when
        normalFrame.bowl(HitCount.valueOf(10));

        // then
        assertThatThrownBy(() -> normalFrame.bowl(HitCount.valueOf(10)))
                .isInstanceOf(NoMoreBowlActionsException.class)
                .hasMessage("현재 상태에서는 더 이상 투구를 할 수 없습니다.");
    }
}