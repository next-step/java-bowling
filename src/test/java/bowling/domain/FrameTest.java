package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameTest {

    @Test
    public void Empty_Score_생성() {
        Frame frame = new Frame(1);
        assertThat(frame.getScore()).isEqualTo(new EmptyScore());
    }

    @Test
    public void 스트라이크_생성() {
        Frame frame = new Frame(1);
        frame.bowl(10);
        StrikeScore score = new StrikeScore(Arrays.asList(10), Arrays.asList(Symbol.STRIKE));
        assertThat(frame.getScore()).isEqualTo(score);
    }


    @Test
    public void Normal_생성() {
        Frame frame = new Frame(1);
        frame.bowl(6);
        NormalScore score = new NormalScore(Arrays.asList(6), Arrays.asList(Symbol.SIX));
        assertThat(frame.getScore()).isEqualTo(score);
    }

    @Test
    public void Spare_생성() {
        Frame frame = new Frame(1);
        frame.bowl(6);
        frame.bowl(4);
        SpareScore score = new SpareScore(Arrays.asList(6, 4), Arrays.asList(Symbol.SIX, Symbol.SPARE));
        assertThat(frame.getScore()).isEqualTo(score);
    }

    @Test
    public void Miss_생성() {
        Frame frame = new Frame(1);
        frame.bowl(6);
        frame.bowl(3);
        MissScore score = new MissScore(Arrays.asList(6, 3), Arrays.asList(Symbol.SIX, Symbol.THREE));
        assertThat(frame.getScore()).isEqualTo(score);
    }

    @Test
    public void Final_생성(){
        Frame frame = new Frame(10);
        assertThat(frame.getScore()).isEqualTo(new FinalScore());
    }

    @Test
    public void Strike_추가공_실패() {
        Frame frame = new Frame(1);
        frame.bowl(10);
        assertThatThrownBy(() -> {
            frame.bowl(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void Spare_추가공_실패() {
        Frame frame = new Frame(1);
        frame.bowl(6);
        frame.bowl(4);
        assertThatThrownBy(() -> {
            frame.bowl(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void Miss_추가공_실패() {
        Frame frame = new Frame(1);
        frame.bowl(6);
        frame.bowl(3);
        assertThatThrownBy(() -> {
            frame.bowl(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}