package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalFrameTest {

    @Test
    @DisplayName("스트라이크일 때, 프레임 생성")
    void strike() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch = Pitch.from(10);
        normalFrame.playPitch(pitch);
        assertThat(normalFrame.getPitchSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("스트라이크가 아닐 때, 스페어 처리하는 프레임 생성")
    void spare() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(9);
        Pitch pitch2 = Pitch.from(1);
        normalFrame.playPitch(pitch1);
        normalFrame.playPitch(pitch2);
        assertThat(normalFrame.getPitchSize()).isEqualTo(2);
    }

    @Test
    @DisplayName("스페어 처리시, 총 점수가 10점을 넘을 때 예외 처리")
    void spareException() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(9);
        Pitch pitch2 = Pitch.from(2);
        normalFrame.playPitch(pitch1);
        assertThrows(IllegalArgumentException.class,
                () -> normalFrame.playPitch(pitch2));
    }

    @Test
    @DisplayName("스트라이크일 때, 프레임 종료 확인")
    void strikeFinished() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch = Pitch.from(10);
        normalFrame.playPitch(pitch);

        assertThat(normalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("스페어 처리할 때, 프레임 종료 확인")
    void spareFinished() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(2);
        normalFrame.playPitch(pitch1);
        normalFrame.playPitch(pitch2);

        assertThat(normalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("2번 투구했을 때, 프레임 종료 확인")
    void justFinished() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(1);
        normalFrame.playPitch(pitch1);
        normalFrame.playPitch(pitch2);

        assertThat(normalFrame.isFinish()).isTrue();
    }

    @Test
    @DisplayName("스트라이크가 아니고, 1번 투구했을 때, 종료되지 않음")
    void notFinished() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        normalFrame.playPitch(pitch1);

        assertThat(normalFrame.isFinish()).isFalse();
    }

    @Test
    @DisplayName("첫번째 투구 반환")
    void getFirstPitch() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        normalFrame.playPitch(pitch1);

        assertThat(normalFrame.getFirstScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("두번째 투구 반환")
    void getSecondPitch() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(2);
        normalFrame.playPitch(pitch1);
        normalFrame.playPitch(pitch2);

        assertThat(normalFrame.getSecondScore()).isEqualTo(2);
    }

    @Test
    @DisplayName("스페어처리인지 확인")
    void isSpare() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(2);
        normalFrame.playPitch(pitch1);
        normalFrame.playPitch(pitch2);

        assertThat(normalFrame.isSpare()).isTrue();
    }

    @Test
    @DisplayName("프레임 점수 확인1")
    void getFrameScorePitch1() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        normalFrame.playPitch(pitch1);

        assertThat(normalFrame.getFrameScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("프레임 점수 확인2")
    void getFrameScorePitch2() {
        Frame normalFrame = NormalFrame.init();

        Pitch pitch1 = Pitch.from(8);
        Pitch pitch2 = Pitch.from(1);
        normalFrame.playPitch(pitch1);
        normalFrame.playPitch(pitch2);

        assertThat(normalFrame.getFrameScore()).isEqualTo(9);
    }

//    @Test
//    @DisplayName("첫번째 투구 스트라이크")
//    void firstStrike() {
//        Pitch pitch = Pitch.from(10);
//
//        Frame normalFrame = NormalFrame.init();
//        normalFrame.playPitch(pitch);
//
//        assertThat(normalFrame.getFirstSymbol()).isEqualTo("X");
//    }
//
//    @Test
//    @DisplayName("첫번째 투구 거터")
//    void firstGutter() {
//        Pitch pitch = Pitch.from(0);
//
//        Frame normalFrame = NormalFrame.init();
//        normalFrame.playPitch(pitch);
//
//        assertThat(normalFrame.getFirstSymbol()).isEqualTo("-");
//    }
//
//    @Test
//    @DisplayName("첫번째 투구 일반 숫자")
//    void firstNumber() {
//        Pitch pitch = Pitch.from(2);
//
//        Frame normalFrame = NormalFrame.init();
//        normalFrame.playPitch(pitch);
//
//        assertThat(normalFrame.getFirstSymbol()).isEqualTo("2");
//    }
//
//    @Test
//    @DisplayName("두번째 투구 거터")
//    void secondGutter() {
//        Pitch pitch1 = Pitch.from(5);
//        Pitch pitch2 = Pitch.from(0);
//
//        Frame normalFrame = NormalFrame.init();
//        normalFrame.playPitch(pitch1);
//        normalFrame.playPitch(pitch2);
//
//        assertThat(normalFrame.getSecondSymbol()).isEqualTo("-");
//    }
//
//    @Test
//    @DisplayName("두번째 투구 스페어")
//    void secondSpare() {
//        Pitch pitch1 = Pitch.from(5);
//        Pitch pitch2 = Pitch.from(5);
//
//        Frame normalFrame = NormalFrame.init();
//        normalFrame.playPitch(pitch1);
//        normalFrame.playPitch(pitch2);
//
//        assertThat(normalFrame.getSecondSymbol()).isEqualTo("/");
//    }
//
//    @Test
//    @DisplayName("두번째 투구 미스")
//    void secondMiss() {
//        Pitch pitch1 = Pitch.from(5);
//        Pitch pitch2 = Pitch.from(4);
//
//        Frame normalFrame = NormalFrame.init();
//        normalFrame.playPitch(pitch1);
//        normalFrame.playPitch(pitch2);
//
//        assertThat(normalFrame.getSecondSymbol()).isEqualTo("4");
//    }

}