package step4.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.exception.GameOverException;

class LastFrameTest {
    @DisplayName("마지막 프레임 첫번째 스트라이크를 쳤을 경우라면 점수는 10점이다.")
    @Test
    void strikeScore() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(10, frames);
        int score = frame.getScore().getScore();
        assertThat(score).isEqualTo(10);
    }

    @DisplayName("마지막 프레임 첫번째 스트라이크, 두번째 스트라이크를 쳤을 경우라면 점수는 20점이다.")
    @Test
    void strikeScore2() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        int score = frame.getScore().getScore();
        assertThat(score).isEqualTo(20);
    }

    @DisplayName("마지막 프레임 세번 모두 스트라이크를 쳤을 경우라면 점수는 30점이다.")
    @Test
    void strikeScore3() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        int score = frame.getScore().getScore();
        assertThat(score).isEqualTo(30);
    }

    @DisplayName("마지막 프레임 두번 스트라이크 세 번째 9점을 쳤을 경우라면 점수는 29점이다.")
    @Test
    void strikeStrikeNormalScore() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        frame.throwBowl(9, frames);
        int score = frame.getScore().getScore();
        assertThat(score).isEqualTo(29);
    }

    @DisplayName("마지막 프레임 4점과 5점을 치게 된다면, 게임이 종료되고 점수는 9점이다.")
    @Test
    void normalNormalScore() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(4, frames);
        frame.throwBowl(5, frames);
        int score = frame.getScore().getScore();
        assertThat(score).isEqualTo(9);
    }

    @DisplayName("마지막 프레임 4점과 5점을 치게 된다면, 게임이 종료된다.")
    @Test
    void gameEndTest() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(4, frames);
        frame.throwBowl(5, frames);
        assertThat(frame.isGameEnd()).isTrue();
    }

    @DisplayName("마지막 프레임 4점만 쳤을 경우, 게임이 종료되지 않는다.")
    @Test
    void gameEndTest2() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(4, frames);
        assertThat(frame.isGameEnd()).isFalse();
    }

    @DisplayName("마지막 프레임 스트라이크 세번 쳤을 경우, 게임이 종료된다.")
    @Test
    void gameEndTest3() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        assertThat(frame.isGameEnd()).isTrue();
    }

    @DisplayName("마지막 프레임 스트라이크 두번 쳤을 경우, 게임이 종료된다.")
    @Test
    void gameEndTest4() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        assertThat(frame.isGameEnd()).isFalse();
    }

    @DisplayName("마지막 프레임 4점과 5점을 치고 1점을 추가로 친다면 오류가 발생한다.")
    @Test
    void normalNormalNormalScore() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(4, frames);
        frame.throwBowl(5, frames);
        assertThatThrownBy(() -> {
            frame.throwBowl(1, frames);
        }).isInstanceOf(GameOverException.class);
    }

    @DisplayName("마지막 프레임 4점과 5점을 치고 1점을 추가로 친다면 오류가 발생한다.")
    @Test
    void normalSpareNormalSymbol() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(4, frames);
        frame.throwBowl(6, frames);
        frame.throwBowl(5, frames);
        assertThat(frame.getSymbol()).isEqualTo("4|-|5");
    }

    @DisplayName("마지막 프레임 세번의 스트라이크를 친다면  X|X|X 심볼을 리턴한다.")
    @Test
    void strikeStrikeStrikeSymbol() {
        Frames frames = new Frames();
        Frame frame = new LastFrame(10);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        frame.throwBowl(10, frames);
        assertThat(frame.getSymbol()).isEqualTo("X|X|X");
    }
}