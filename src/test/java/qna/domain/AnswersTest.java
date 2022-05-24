package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answers answers = new Answers(List.of(A1, A2));

    @Test
    @DisplayName("답변 내역 중 답변 여부에 따른 삭제 예외 처리")
    void hasAnotherUserAnswers() {
        assertThatThrownBy(() -> answers.isAnotherUserAnswers(UserTest.JAVAJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변 삭제 처리 및 삭제 이력 추가")
    void addDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.addDeleteHistories(deleteHistories);
        assertThat(deleteHistories).hasSize(2);
    }
}
