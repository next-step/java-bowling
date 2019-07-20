package domain.frame;

import domain.Pins;
import domain.score.Score;
import domain.state.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    private NormalFrame lastNormalFrame;

    @BeforeEach
    void setUp() {
        lastNormalFrame = new NormalFrame(new LastFrame());
    }

    @Test
    void strike() {
        Frame frame = this.lastNormalFrame.setKnockedDownPins(Pins.ALL);
        State state = frame.getState();
        assertThat(state).isInstanceOf(Strike.class);
    }

    @Test
    void spares() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));
        State state = lastNormalFrame.getState();
        assertThat(state).isInstanceOf(Spares.class);
    }

    @Test
    void open() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));
        this.lastNormalFrame.setKnockedDownPins(Pins.of(4));
        State state = lastNormalFrame.getState();
        assertThat(state).isInstanceOf(Open.class);
    }

    @Test
    void waiting() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));
        State state = lastNormalFrame.getState();
        assertThat(state).isInstanceOf(Waiting.class);
    }

    @Test
    @DisplayName("strke -> spare -> strke")
    void score() {
        this.lastNormalFrame.setKnockedDownPins(Pins.ALL);

        Frame secondFrame = new NormalFrame(lastNormalFrame);
        secondFrame.setKnockedDownPins(Pins.of(9));
        secondFrame.setKnockedDownPins(Pins.of(1));

        Frame firstFrame = new NormalFrame(secondFrame);
        firstFrame.setKnockedDownPins(Pins.ALL);

        Score score = firstFrame.getScore();
        assertThat(score.getValue()).isEqualTo(20);
    }

    @Test
    @DisplayName("strke -> strike -> strke")
    void terkey() {
        this.lastNormalFrame.setKnockedDownPins(Pins.ALL);

        Frame secondFrame = new NormalFrame(lastNormalFrame);
        secondFrame.setKnockedDownPins(Pins.ALL);

        Frame firstFrame = new NormalFrame(secondFrame);
        firstFrame.setKnockedDownPins(Pins.ALL);

        Score score = firstFrame.getScore();
        assertThat(score.getValue()).isEqualTo(30);
    }

    @Test
    @DisplayName("strke -> strike -> open")
    void strikeOpen() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));
        this.lastNormalFrame.setKnockedDownPins(Pins.of(3));

        Frame secondFrame = new NormalFrame(lastNormalFrame);
        secondFrame.setKnockedDownPins(Pins.ALL);

        Frame firstFrame = new NormalFrame(secondFrame);
        firstFrame.setKnockedDownPins(Pins.ALL);

        Score score = firstFrame.getScore();
        assertThat(score.getValue()).isEqualTo(25);
    }

    @Test
    @DisplayName("strke -> strike -> waiting")
    void strikeStrikeWaiting() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));

        Frame secondFrame = new NormalFrame(lastNormalFrame);
        secondFrame.setKnockedDownPins(Pins.ALL);

        Frame firstFrame = new NormalFrame(secondFrame);
        firstFrame.setKnockedDownPins(Pins.ALL);

        Score score = firstFrame.getScore();
        assertThat(score.getValue()).isEqualTo(25);
    }

    @Test
    @DisplayName("strke -> waiting")
    void strikeWaiting() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));

        Frame firstFrame = new NormalFrame(lastNormalFrame);
        firstFrame.setKnockedDownPins(Pins.ALL);

        Score score = firstFrame.getScore();
        assertThat(score.getValue()).isEqualTo(-1);
    }


    @Test
    @DisplayName("spare -> strike")
    void sparesStrke() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));

        Frame secondFrame = new NormalFrame(lastNormalFrame);
        secondFrame.setKnockedDownPins(Pins.ALL);

        Frame firstFrame = new NormalFrame(secondFrame);
        firstFrame.setKnockedDownPins(Pins.of(9));
        firstFrame.setKnockedDownPins(Pins.of(1));

        Score score = firstFrame.getScore();
        assertThat(score.getValue()).isEqualTo(20);
    }

    @Test
    @DisplayName("spare -> wating")
    void sparesWaiting() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));

        Frame secondFrame = new NormalFrame(lastNormalFrame);
        secondFrame.setKnockedDownPins(Pins.of(5));

        Frame firstFrame = new NormalFrame(secondFrame);
        firstFrame.setKnockedDownPins(Pins.of(9));
        firstFrame.setKnockedDownPins(Pins.of(1));

        Score score = firstFrame.getScore();
        assertThat(score.getValue()).isEqualTo(15);
    }

    @Test
    @DisplayName("spare -> open")
    void sparesOpen() {
        this.lastNormalFrame.setKnockedDownPins(Pins.of(5));

        Frame secondFrame = new NormalFrame(lastNormalFrame);
        secondFrame.setKnockedDownPins(Pins.of(5));
        secondFrame.setKnockedDownPins(Pins.of(4));

        Frame firstFrame = new NormalFrame(secondFrame);
        firstFrame.setKnockedDownPins(Pins.of(9));
        firstFrame.setKnockedDownPins(Pins.of(1));

        Score score = firstFrame.getScore();
        assertThat(score.getValue()).isEqualTo(15);
    }



}