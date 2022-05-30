package bowling.domain.bowl.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlTypeTest {

    @ParameterizedTest
    @CsvSource(value = {
            "1", "2", "3", "4", "5", "6", "7", "8", "9"
    })
    void first(int first){
        BowlType bowlType = BowlType.getType(List.of(first));
        assertThat(bowlType).isEqualTo(BowlType.FIRST);
    }

    @Test
    void ready(){
        BowlType bowlType = BowlType.getType(List.of());
        assertThat(bowlType).isEqualTo(BowlType.READY);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 8",
            "0, 3",
            "4, 1",
    })
    void miss(int first, int second){
        BowlType bowlType = BowlType.getType(List.of(first, second));
        assertThat(bowlType).isEqualTo(BowlType.MISS);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1, 9",
            "0, 10",
            "2, 8",
            "3, 7",
            "4, 6",
            "5, 5"
    })
    void spare(int first, int second){
        BowlType bowlType = BowlType.getType(List.of(first, second));
        assertThat(bowlType).isEqualTo(BowlType.SPARE);
    }

    @Test
    void strike(){
        BowlType bowlType = BowlType.getType(List.of(10));
        assertThat(bowlType).isEqualTo(BowlType.STRIKE);
    }

    @Test
    void gutter(){
        BowlType bowlType = BowlType.getType(List.of(0, 0));
        assertThat(bowlType).isEqualTo(BowlType.GUTTER);
    }

}