package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class DeleteHistoriesGroupTest {

    @DisplayName("객체 정상 생성")
    @Test
    public void makeDeleteHistoriesGroup_정상() {
        assertThatCode(() -> {
            new DeleteHistoriesGroup();
        }).doesNotThrowAnyException();
    }

    @DisplayName("addQuestionHistory 작업 요청을 받응면 내부 리스트에 Question 삭제 이력을 추가함")
    @Test
    public void addDeleteHistory_정상() {
        DeleteHistoriesGroup deleteHistoriesGroup = new DeleteHistoriesGroup();
        List<DeleteHistory> deleteHistories = deleteHistoriesGroup.getDeleteHistories();

        assertThat(deleteHistories.size()).isEqualTo(0);

        deleteHistoriesGroup.addQuestionHistory(QuestionTest.Q1, 1);

        assertThat(deleteHistories.size()).isEqualTo(1);
    }
}
