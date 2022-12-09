package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingsTest {

    @Test
    void 생성() {
        Bowlings bowlings = new Bowlings(Arrays.asList(Name.of("abc"), Name.of("kkk")));
        Iterator<Bowling> iterator = bowlings.iterator();
        assertThat(iterator.hasNext()).isTrue();
        iterator.next();
        assertThat(iterator.hasNext()).isTrue();
        iterator.next();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void 진행() {
        Bowlings bowlings = new Bowlings(Arrays.asList(Name.of("abc"), Name.of("kkk")));
        assertThat(bowlings.isGameNotEnd()).isTrue();
    }
}