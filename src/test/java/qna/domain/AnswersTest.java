package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;


class AnswersTest {

    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("다른 사람이 작성한 답변을 삭제하면 예외 발생테스트")
    @Test
    void answersDeleteExceptionTest() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);
        assertThatThrownBy(
                () -> answers.delete(UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변들 삭제 테스")
    @Test
    void answersDeleteTest() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(A2);
        answers.add(A3);
        List<DeleteHistory> deleteHistories = answers.delete(UserTest.SANJIGI);
        assertAll(
                ()->assertThat(deleteHistories).isNotNull(),
                ()->assertThat(deleteHistories.size()).isEqualTo(2)
        );
    }


}
