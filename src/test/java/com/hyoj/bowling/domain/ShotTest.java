package com.hyoj.bowling.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShotTest {

    @Test(expected = RuntimeException.class)
    public void 투구생성_0보다_적은_넘어뜨린_핀_개수() {
        new Shot(-1);
    }

    @Test(expected = RuntimeException.class)
    public void 투구생성_10보다_많은_넘어뜨린_핀_개수() {
        new Shot(11);
    }

    @Test
    public void 모든_핀을_한번에_넘어뜨림() {
        final Shot shot = new Shot(10);
        assertThat(shot.isAllDown()).isEqualTo(true);
    }

    @Test
    public void 모든_핀을_두번에_넘어뜨림() {
        final Shot shot1 = new Shot(3);
        final Shot shot2 = new Shot(7, shot1);
        assertThat(shot2.isAllDown()).isEqualTo(true);
    }

    @Test(expected = RuntimeException.class)
    public void 두번에_넘어뜨린_핀_개수가_10개보다_많음(){
        final Shot shot1 = new Shot(9);
        final Shot shot2 = new Shot(2, shot1);
    }

    @Test
    public void 핀10개가_모두_서있음() {
        final Shot shot1 = new Shot(0);
        assertThat(shot1.isAllStanding()).isEqualTo(true);
    }

    @Test
    public void 핀10개가_모두_서있음_두번_투구() {
        final Shot shot1 = new Shot(0);
        final Shot shot2 = new Shot(0, shot1);
        assertThat(shot2.isAllStanding()).isEqualTo(true);
    }

    @Test
    public void 투구_텍스트표현() {
        assertThat(new Shot(1).toString()).isEqualTo("1");
        assertThat(new Shot(2).toString()).isEqualTo("2");
    }
}