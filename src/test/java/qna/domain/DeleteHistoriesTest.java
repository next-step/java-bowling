package qna.domain;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class DeleteHistoriesTest {
    @Test
    void create() {
        // when
        final DeleteHistories deleteHistories = DeleteHistories.of();
        
        // then
        assertThat(deleteHistories).isNotNull();
    }
}
