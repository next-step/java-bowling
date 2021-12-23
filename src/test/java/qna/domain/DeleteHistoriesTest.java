package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static qna.domain.DeleteHistoryTest.DH1;
import static qna.domain.DeleteHistoryTest.DH2;

public class DeleteHistoriesTest {
    public static final DeleteHistories DELETE_HISTORIES = new DeleteHistories(Arrays.asList(DH1, DH2));

    @Test
    @DisplayName("Answers 생성자 테스트")
    void constructor() {
        assertThat(DELETE_HISTORIES)
                .isEqualTo(new DeleteHistories(Arrays.asList(DH1, DH2)));
    }
}
