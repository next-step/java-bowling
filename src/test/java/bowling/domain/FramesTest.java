package bowling.domain;

import bowling.domain.frame.Frames;
import bowling.domain.state.Running;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class FramesTest {

    @Test
    @DisplayName("퍼펙트 게임")
    void perfect_game() {
        //given
        Frames frames = new Frames();

        //when
        IntStream.range(0, 12)
                .forEach(i -> {
                    frames.bowl(range -> new Pin(10));
                    if (!frames.isFinished()) {
                        frames.next();
                    }
                });

        //then
        assertAll(
                () -> assertThat(frames.isFinished()).isTrue(),
                () -> assertThat(frames.getFrameNumber()).isEqualTo(10),
                () -> assertThat(frames.getFrames().get(9).calculatePoint()).isEqualTo(300)
        );

    }

    @Test
    @DisplayName("1번 던짐")
    void bowl_one() {
        //given
        Frames frames = new Frames();
        //when
        Pin now = frames.bowl(range -> new Pin(5));
        //then
        assertAll(
                () -> assertThat(frames.isFinished()).isFalse(),
                () -> assertThat(frames.getFrameNumber()).isEqualTo(1),
                () -> assertThat(frames.getFrames().get(0).getState()).isInstanceOf(Running.class),
                () -> assertThat(frames.getFrames().get(0).calculatePoint()).isNull()
        );

    }

    @Test
    @DisplayName("스트라이크면 턴 종료")
    void end_of_turn_strike() {
        //given
        Frames frames = new Frames();
        //when
        frames.bowl( a -> new Pin(10));
        //then
        assertThat(frames.isEndOfTurn()).isTrue();
    }

    @Test
    @DisplayName("스페어면 턴 종료")
    void end_of_turn_spare() {
        //given
        Frames frames = new Frames();
        //when
        frames.bowl( a -> new Pin(1));
        frames.bowl( a -> new Pin(9));
        //then
        assertThat(frames.isEndOfTurn()).isTrue();
    }
    @Test
    @DisplayName("miss면 턴 종료")
    void end_of_turn_miss() {
        //given
        Frames frames = new Frames();
        //when
        frames.bowl( a -> new Pin(5));
        frames.bowl( a -> new Pin(3));
        //then
        assertThat(frames.isEndOfTurn()).isTrue();
    }

    @Test
    @DisplayName("한번 던지면 턴 종료 아님")
    void not_end_of_turn_one_bowl() {
        //given
        Frames frames = new Frames();
        //when
        frames.bowl( a -> new Pin(5));
        //then
        assertThat(frames.isEndOfTurn()).isFalse();
    }

}