package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class RecordsTest {
    private static final Integer TEST_INDEX = 0;
    private static final String TEST_NAME = "ABC";

    private static Records getRecords() {
        Record record1 = new Record(new Player(TEST_NAME));
        Record record2 = new Record(new Player("DEF"));
        return new Records(Arrays.asList(record1, record2));
    }

    @Test
    void getPlayerNameTest() {
        Records records = getRecords();
        assertThat(records.getPlayerName(TEST_INDEX)).isEqualTo(TEST_NAME);
    }

    @Test
    void isEndPlayerFrameTest() {
        Records records = getRecords();

        records.record(0, RuleConfig.NUMBER_OF_PIN);

        assertThat(records.isEndPlayerFrame(0, 0)).isTrue();
        assertThat(records.isEndPlayerFrame(0, 1)).isFalse();
    }

    @Test
    void isEndFramesTest_TRUE() {
        Records records = getRecords();

        records.record(0, RuleConfig.NUMBER_OF_PIN);
        records.record(1, RuleConfig.NUMBER_OF_PIN);

        assertThat(records.isEndFrames(0)).isTrue();
    }

    @Test
    void isEndFramesTest_FALSE() {
        Records records = getRecords();

        records.record(0, RuleConfig.NUMBER_OF_PIN);

        assertThat(records.isEndFrames(0)).isFalse();
    }

    @Test
    void isEndRecordsTest() {
        Records records = getRecords();

        for (int index = 0; index < RuleConfig.NUMBER_OF_FRAME; index++) {
            records.record(0, 10);
            records.record(1, 10);
        }
        assertThat(records.isEndRecords()).isFalse();

        records.record(0, 10);
        records.record(1, 10);
        assertThat(records.isEndRecords()).isTrue();
    }

}