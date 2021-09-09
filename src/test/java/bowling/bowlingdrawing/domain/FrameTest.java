package bowling.bowlingdrawing.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FrameTest {

    @Test
    @DisplayName("첫 번째 Frame 생성")
    void create_first() {
        // given
        int pins = 9;
        // when
        Frame firstFrame = Frame.first(pins);
        // then
        assertThat(firstFrame).isEqualTo(Frame.of(9));
    }

    @Test
    @DisplayName("2~10 Frame 생성")
    void create_middle() {
        // given
        Frame frame = Frame.of(7, 2);
        // when
        Frame nextFrame = frame.next(8);
        // then
        assertThat(nextFrame).isEqualTo(Frame.of(7, 2).next(8));
    }

    @Test
    @DisplayName("secondPitching 점수 추가")
    void secondPitching(){
        // given
        Frame frame = Frame.of(6);
        int pins = 3;
        // when
        frame.pitching(pins);
        // then
        assertThat(frame).isEqualTo(Frame.of(6, 3));
    }

    @Test
    @DisplayName("해당 Frame 투구 완료 유무 파악")
    void done() {
        // given
        Frame frame1 = Frame.of(7, 2);
        Frame frame2 = Frame.of(7);
        // when
        boolean done1 = frame1.done();
        boolean done2 = frame2.done();
        // then
        assertThat(done1).isTrue();
        assertThat(done2).isFalse();
    }

    @Test
    @DisplayName("Frame 총 점수 반환")
    void score() {
        // given
        Frame frame = Frame.of(7, 2);
        // when
        int score = frame.score();
        // then
        assertThat(score).isEqualTo(9);
    }

    @Test
    @DisplayName("spare 시 점수 반환")
    void score_spare() {
        // given
        Frame frame = Frame.of(7, 3);
        Frame nextFrame = frame.next(8);
        // when
        int score = frame.score();
        int scoreNext = nextFrame.score();
        // then
        assertThat(score).isEqualTo(18);
        assertThat(scoreNext).isEqualTo(8);
    }

    @Test
    @DisplayName("Strike 시 점수 반환")
    void score_strike() {
        // given
        Frame frame = Frame.of(10);
        Frame nextFrame = frame.next(8);
        nextFrame.pitching(1);
        // when
        int score = frame.score();
        int scoreNext = nextFrame.score();
        // then
        assertThat(score).isEqualTo(19);
        assertThat(scoreNext).isEqualTo(9);
    }

}