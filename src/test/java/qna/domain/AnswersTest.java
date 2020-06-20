package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents3");

    @Test
    @DisplayName("삭제시 다른 사람이 단 답글 있을 경우 예외")
    void delete_Exception() {
        Answers answers = new Answers(Arrays.asList(A1, A2));
        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("답글 삭제 이력들 생성")
    void makeDeleteHistories() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(A2, A3));
        answers.delete(UserTest.SANJIGI);
        DeleteHistories deleteHistories = answers.makeDeleteHistories();

        assertThat(deleteHistories.getDeleteHistories()).hasSize(2);
    }
}
