package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract class ScoreSheetTest {
    ScoreSheet scoreSheet;

    @DisplayName("현재 프래임이 끝나지 않은 상태에서 다음 프래임을 진행하려고 하면 exception 을 던진다")
    @Test
    void failToNextFrame(){
        Frame current = scoreSheet.nextFrame();
        assertThatThrownBy( () -> scoreSheet.nextFrame())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("10개의 프레임까지 진행할 수 있다")
    @Test
    void isAllMarked1(){
        IntStream.range(0, 10)
                .forEach(idx -> {
                    Frame frame = scoreSheet.nextFrame();
                    while( !frame.isEnd() ) {
                        frame.mark(getCountOfFailDownPins());
                    }
                });
        assertThat(scoreSheet.isAllMarked()).isTrue();
        assertThat(scoreSheet.nextFrame()).isNull();
    }

    @DisplayName("남아 있는 프래임이 있는지 확인 할 수 있다")
    @Test
    void isAllMarked2(){
        IntStream.range(0, 5)
                .forEach(idx -> {
                    Frame frame = scoreSheet.nextFrame();
                    while( !frame.isEnd() ) {
                        frame.mark(getCountOfFailDownPins());
                    }
                });
        assertThat(scoreSheet.isAllMarked()).isFalse();
        assertThat(scoreSheet.nextFrame())
                .isNotNull()
                .hasFieldOrPropertyWithValue("frameNo", 6);

    }

    private int getCountOfFailDownPins(){
        Random random = new Random();
        return random.nextInt(6);
    }

}

class DefaultScoreSheetTest extends ScoreSheetTest {

    @BeforeEach
    void setUp(){
        scoreSheet = new DefaultScoreSheet(new Player("nio"));
    }

}