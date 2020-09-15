package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class NormalRollingResultTest {


    @Test
    void strikeDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(10);
        assertThat(normalRollingResult.desc()).isEqualTo("X");
    }

    @Test
    void spareDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(8);
        normalRollingResult.bowl(2);
        assertThat(normalRollingResult.desc()).isEqualTo("8|/");
    }

    @Test
    void missDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(5);
        normalRollingResult.bowl(2);
        assertThat(normalRollingResult.desc()).isEqualTo("5|2");
    }

    @Test
    void runDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(5);
        assertThat(normalRollingResult.desc()).isEqualTo("5");
    }

    @Test
    void gutterDesc() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(0);
        normalRollingResult.bowl(2);
        assertThat(normalRollingResult.desc()).isEqualTo("-|2");
    }

    @Test
    void isStrikeTest() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(10);

        assertThat(normalRollingResult.status()).isEqualTo(Status.STRIKE);
    }

    @Test
    void isSpareTest() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(2);
        normalRollingResult.bowl(8);

        assertThat(normalRollingResult.status()).isEqualTo(Status.SPARE);
    }

    @Test
    void isMISSTest() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(2);
        normalRollingResult.bowl(5);

        assertThat(normalRollingResult.status()).isEqualTo(Status.MISS);
    }

    @Test
    void isRunTest() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();
        normalRollingResult.bowl(2);

        assertThat(normalRollingResult.status()).isEqualTo(Status.ING);
    }

    @Test
    void countOfAllPinsOverTenTest() {
        assertThatThrownBy(() -> {
            NormalRollingResult normalRollingResult = new NormalRollingResult();
            normalRollingResult.bowl(5);
            normalRollingResult.bowl(6);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void isFinish_Test() {
        NormalRollingResult normalRollingResult = new NormalRollingResult();

        normalRollingResult.bowl(10);

        assertTrue(normalRollingResult.isFinish());
    }

}
