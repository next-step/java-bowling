package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoriesTest {

    @Test
    @DisplayName("DeleteHistory 에 history들이 잘 쌓이는지 테스트")
    public void deleteHistoryCountTest() {
        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.addQuestionDeleteHistory(0, UserTest.JAVAJIGI);

        Assertions.assertThat(deleteHistories.getDeleteHistories().size()).isEqualTo(1);

        deleteHistories.addAnswerDeleteHistory(AnswerTest.A1);

        Assertions.assertThat(deleteHistories.getDeleteHistories().size()).isEqualTo(2);

        deleteHistories.addAnswerDeleteHistory(AnswerTest.A2);

        Assertions.assertThat(deleteHistories.getDeleteHistories().size()).isEqualTo(3);
    }
}