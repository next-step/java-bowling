package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("노멀프레임 객체 생성")
    void create() {
        NormalFrame normalFrame = new NormalFrame(1, 10);
        assertThat(normalFrame).isEqualTo(new NormalFrame(1, 10));
    }

    @Test
    @DisplayName("현재프레임 - 1번째 시도, 카운트 == 1")
    void firstTry() {
        NormalFrame normalFrame = new NormalFrame(0, 10);
        assertThat(normalFrame.plusCount()).isEqualTo(new Pitching(1));
    }

    @Test
    @DisplayName("현재프레임 - 2번째 시도, 카운트 == 2")
    void secondTry() {
        NormalFrame currentFrame = new NormalFrame(1, 10);
        assertThat(currentFrame.plusCount()).isEqualTo(new Pitching(2));
    }

    @Test
    @DisplayName("첫번째 시도에 핀을 넘어뜨려 남은 핀 반환")
    void firstDownPins() {
        NormalFrame normalFrame = new NormalFrame(0, 10);
        BowlingPins upPins =  normalFrame.downPins(7);
        assertThat(upPins).isEqualTo(new BowlingPins(3));
    }

    @Test
    @DisplayName("두번째 시도에 핀을 넘어뜨려 남은 핀 반환")
    void secondDownPins() {
        NormalFrame normalFrame = new NormalFrame(1, 3);
        Pitching currentPitching = normalFrame.plusCount();
        assertThat(currentPitching).isEqualTo(new Pitching(2));
        assertThat(normalFrame.downPins(3)).isEqualTo(new BowlingPins(0));
    }

    @Test
    @DisplayName("두번째 시도 후, 다음 새로운 객체 프레임 생성 - 프레임 수 증가, 카운트 0, 핀 개수 10개")
    void createNextFrame() {
        NormalFrame normalFrame = new NormalFrame(1, 3);
        Pitching currentPitching = normalFrame.plusCount();

        NormalFrame newNextFrame = normalFrame.createNextFrame(currentPitching);
        assertThat(newNextFrame.getCountOfPitching()).isEqualTo(new Pitching(0));
        assertThat(newNextFrame.getUpPins()).isEqualTo(new BowlingPins(10));
        assertThat(newNextFrame.getFrame()).isEqualTo(new Frame(1));
    }


}