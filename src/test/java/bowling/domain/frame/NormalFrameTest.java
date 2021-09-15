package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.score.NormalScore;
import bowling.domain.score.Pin;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    @DisplayName("첫번째 프레임을 생성할 수 있다.")
    void createFirstFrameTest() {

        // when
        Frame result = NormalFrame.createFirstFrame();

        // then
        assertThat(result).isInstanceOf(Frame.class);
        assertThat(result).isInstanceOf(NormalFrame.class);
    }
    
    @Test
    @DisplayName("현재 프레임을 가지고 다음 프레임을 생성할 수 있다.")
    void createNextFrameTest() {
        
        // givne
        Frame first = NormalFrame.createFirstFrame();
        
        // when
        Frame result = first.createNextFrame();

        // then
        assertThat(result).isInstanceOf(Frame.class);
        assertThat(result).isInstanceOf(NormalFrame.class);
    }

    @Test
    @DisplayName("9번 프레임에서는 다음 프레임을 10번 프레임을 생성해야 한다.")
    void createFinalFrameTest() {

        // given
        Frame first = NormalFrame.createFirstFrame();
        for (int i=0; i<8; i++){
            first = first.createNextFrame();
        }

        // when
        Frame result = first.createNextFrame();

        // then
        assertThat(result).isInstanceOf(Frame.class);
        assertThat(result).isInstanceOf(FinalFrame.class);
    }

    @Test
    @DisplayName("현재 라운드의 score를 저장할 수 있다.")
    void saveNowScoreTest() {

        // given
        Frame first = NormalFrame.createFirstFrame();
        Score emptyScore = NormalScore.empty();
        Score score = emptyScore.createFirstPin(Pin.of(10));

        // when
        first.saveScore(score);

        // then
        assertThat(first).isInstanceOf(Frame.class);
        assertThat(first).isInstanceOf(NormalFrame.class);
    }

}