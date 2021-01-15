package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest extends FrameTest {

    @BeforeEach
    void setUp(){
        frame = Frame.createFinal(10);
    }

    @DisplayName("10 번째 프래임은 LastFrame 이다")
    @Test
    void should_be_LastFrame(){
        Frame frame = Frame.createNormal(9);
        Frame last = frame.createNext();
        assertThat(last instanceof FinalFrame).isTrue();
    }

    @DisplayName("마지막 프레임의 다음 프레임은 null 을 return 한다")
    @Test
    void nextIsNull(){
        assertThat(frame.createNext()).isNull();
    }

    @DisplayName("프레임이 종료될때 까지 점수는 알 수 없다 (1 mark)")
    @Test
    void unknownScore1(){
        frame.mark(9);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("프레임이 종료될때 까지 점수는 알 수 없다 (2 mark, spare)")
    @Test
    void unknownScore2(){
        frame.mark(9);
        frame.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("프레임이 종료될때 까지 점수는 알 수 없다 (2 mark, strike)")
    @Test
    void unknownScore3(){
        frame.mark(10);
        frame.mark(9);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("프레임이 종료될때 까지 점수는 알 수 없다 (2 mark, double)")
    @Test
    void unknownScore4(){
        frame.mark(10);
        frame.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.unknown);
    }

    @DisplayName("Miss 하면 점수를 계산할 수 있다")
    @Test
    void score1(){
        frame.mark(8);
        frame.mark(1);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(9));
    }

    @DisplayName("Spare 이후 Bonus 까지 투구하면 점수를 계산할 수 있다")
    @Test
    void score2(){
        frame.mark(8);
        frame.mark(2);
        frame.mark(9);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(19));
    }

    @DisplayName("Double 이후 Bonus 까지 투구하면 점수를 계산할 수 있다")
    @Test
    void score3(){
        frame.mark(10);
        frame.mark(10);
        frame.mark(9);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(29));
    }

    @DisplayName("Turkey 면 점수를 계산할 수 있다")
    @Test
    void score4(){
        frame.mark(10);
        frame.mark(10);
        frame.mark(10);

        assertThat(frame.getScore()).isEqualTo(FrameScore.of(30));
        assertThat(frame.getScore()).isEqualTo(FrameScore.of(30));
    }
}