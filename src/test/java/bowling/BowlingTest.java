package bowling;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingTest {

    @Test
    void createTest(){
        Bowling bowling = Bowling.start();

        assertThat(bowling.isLastFrame()).isEqualTo(false);
        assertThat(bowling.isTerminated()).isEqualTo(false);
        assertThat(bowling.getCurrentFrameIndex()).isEqualTo(0);
    }

    @Test
    void createTest2_10점(){
        Bowling bowling = Bowling.start();
        bowling.bowl(10);

        assertThat(bowling.isLastFrame()).isEqualTo(false);
        assertThat(bowling.isTerminated()).isEqualTo(false);
        assertThat(bowling.getCurrentFrameIndex()).isEqualTo(1);
    }

    @Test
    void createTest3_miss(){
        Bowling bowling = Bowling.start();
        bowling.bowl(0);
        bowling.bowl(0);

        assertThat(bowling.isLastFrame()).isEqualTo(false);
        assertThat(bowling.isTerminated()).isEqualTo(false);
        assertThat(bowling.getCurrentFrameIndex()).isEqualTo(1);
    }

    @Test
    void createTest4_lastFrame(){
        Bowling bowling = Bowling.start();
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(10);
        bowling.bowl(9);
        bowling.bowl(0);

        assertThat(bowling.isLastFrame()).isEqualTo(true);
        assertThat(bowling.isTerminated()).isEqualTo(true);
        assertThat(bowling.getCurrentFrameIndex()).isEqualTo(9);
    }
}