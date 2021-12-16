package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {

    @Test
    @DisplayName("DeleteHistories 객체 생성")
    void create() {
        DeleteHistories dh = new DeleteHistories();
        assertThat(dh).isEqualTo(new DeleteHistories());
    }

    @Test
    @DisplayName("질문 및 답글 삭제 저장")
    void saveAll() {
        DeleteHistories histories = new DeleteHistories();
        histories.addAnswersHistory(new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)));
        histories.addQuestionHistory(QuestionTest.Q1);

        assertThat(histories.getHistories()).size().isEqualTo(3);
    }

}
