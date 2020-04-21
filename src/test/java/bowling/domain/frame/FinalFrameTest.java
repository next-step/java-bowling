package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinalFrameTest {

    @DisplayName("마지막 프레임은 다음 프레임을 만들 수 없다")
    @Test
    public void createNext_fail() throws Exception {
        //given
        FinalFrame finalFrame = new FinalFrame();

        //then
        assertThatThrownBy(
                () -> finalFrame.createNext()
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("마지막 프레임은 다음 프레임 생성시 null을 반환 한다")
    @Test
    public void createNext_success() throws Exception {
        //given
        FinalFrame finalFrame = new FinalFrame();

        //when
        Frame next = finalFrame.getNext();


        //then
        assertTrue(next == null);
    }

    @DisplayName("완료 상태에서 공을 굴리면 exception")
    @Test
    public void bowl_success() throws Exception {
        //given
        Frame finalFrame = new FinalFrame();

        //when
        finalFrame = finalFrame.bowl(10);
        finalFrame = finalFrame.bowl(10);

        //then
        Frame finalFrame1 = finalFrame;
        assertThatThrownBy(
                () -> finalFrame1.bowl(2)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("마지막 프레임의 점수 반환 : gutter, miss 상태")
    @ParameterizedTest
    @CsvSource(value = {"1:1:2", "0:1:1", "1:0:1"}, delimiter = ':')
    public void getScore_success(int bowl1, int bowl2, int expect) throws Exception {
        //given
        Frame finalFrame = new FinalFrame();

        //when
        finalFrame = finalFrame.bowl(bowl1);
        finalFrame = finalFrame.bowl(bowl2);

        //then
        assertThat(finalFrame.getCurrentScore()).isEqualTo(new Score(expect));
    }

    @DisplayName("마지막 프레임의 점수 반환 : strike상태")
    @ParameterizedTest
    @CsvSource(value = {"10:5:15", "10:10:20"}, delimiter = ':')
    public void getScore_success_strike(int bowl1, int bowl2, int expect) throws Exception {
        //given
        Frame finalFrame = new FinalFrame();

        //when
        finalFrame = finalFrame.bowl(bowl1);
        finalFrame = finalFrame.bowl(bowl2);

        //then
        assertThat(finalFrame.getCurrentScore()).isEqualTo(new Score(expect));
    }

    @DisplayName("마지막 프레임의 점수 반환 : spare상태")
    @ParameterizedTest
    @CsvSource(value = {"1:9:5:15", "5:5:10:20"}, delimiter = ':')
    public void getScore_success_spare(int bowl1, int bowl2, int bowl3, int expect) throws Exception {
        //given
        Frame finalFrame = new FinalFrame();

        //when
        finalFrame = finalFrame.bowl(bowl1);
        finalFrame = finalFrame.bowl(bowl2);
        finalFrame = finalFrame.bowl(bowl3);

        //then
        assertThat(finalFrame.getCurrentScore()).isEqualTo(new Score(expect));
    }
}
