package game.bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by yusik on 2019/11/20.
 */
class NormalFrameTest {

    @DisplayName("스트라이크")
    @Test
    void strike() {

        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.bowl(10);
        System.out.println(normalFrame.getScore());

        // then
        assertThat(normalFrame.isFinish()).isTrue();
        assertThat(normalFrame.getScore().getFirstScore()).isEqualTo(10);
    }

    @DisplayName("두번째 던져야 하는 상태")
    @Test
    void secondThrow() {

        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.bowl(0);
        System.out.println(normalFrame.getScore());

        // then
        assertThat(normalFrame.isFinish()).isFalse();
    }

    @DisplayName("스페어")
    @Test
    void spare() {

        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.bowl(1);
        normalFrame.bowl(9);
        System.out.println(normalFrame.getScore());

        // then
        assertThat(normalFrame.isFinish()).isTrue();
    }

    @DisplayName("프레임 오픈 상태")
    @Test
    void miss() {

        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.bowl(1);
        normalFrame.bowl(1);
        System.out.println(normalFrame.getScore());

        // then
        assertThat(normalFrame.isFinish()).isTrue();
    }

    @DisplayName("아무 핀도 못쓰러뜨린 상태")
    @Test
    void gutter() {

        // given
        NormalFrame normalFrame = NormalFrame.first();

        // when
        normalFrame.bowl(0);
        normalFrame.bowl(0);
        System.out.println(normalFrame.getScore());

        // then
        assertThat(normalFrame.isFinish()).isTrue();
    }
}