package com.seok2.bowling.frame.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    @DisplayName("READY 상태의 점수는 0점 / 추가 횟수 3을 가진다.")
    void ofReady() {
        assertThat(Score.ofReady()).isEqualTo(Score.of(0,Remaining.of(3)));
    }

    @Test
    @DisplayName("READY 상태의 점수는 0점 / 추가 횟수 0을 가진다.")
    void ofGutter() {
        assertThat(Score.ofGutter()).isEqualTo(Score.of(0,Remaining.of(0)));
    }

    @Test
    @DisplayName("Spare 상태의 점수는 10점 / 추가 횟수 1을 가진다.")
    void ofSpare() {
        assertThat(Score.ofSpare()).isEqualTo(Score.of(10,Remaining.of(1)));
    }

    @Test
    @DisplayName("Strike 상태의 점수는 10점 / 추가 횟수 2을 가진다.")
    void ofStrike() {
        assertThat(Score.ofStrike()).isEqualTo(Score.of(10,Remaining.of(2)));
    }

    @Test
    @DisplayName("Pending 상태의 점수는 0점 / 추가 횟수 3을 가진다.")
    void ofPending() {
        assertThat(Score.ofPending()).isEqualTo(Score.of(0,Remaining.of(3)));
    }

    @Test
    @DisplayName("단순 덧셈 테스트")
    void add() {
        assertThat(Score.of(10, Remaining.of(1)).add(Score.of(5))).isEqualTo(Score.of(15));
    }

    @Test
    @DisplayName("단순 덧셈 테스트 추가 횟수가 없는경우 기존 값을 리턴한다.")
    void addWithRemainingZero() {
        assertThat(Score.of(10, Remaining.of(0)).add(Score.of(5))).isEqualTo(Score.of(10));
    }

    @Test
    @DisplayName("추가 횟수가 남아 있는 경우 참 아닐 경우 거짓")
    void isPending() {
        assertThat(Score.of(10, Remaining.of(1)).isPending()).isTrue();
        assertThat(Score.of(10, Remaining.of(0)).isPending()).isFalse();
    }
}