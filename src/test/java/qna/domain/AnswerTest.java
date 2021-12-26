package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.QuestionTest.Q1;

import org.junit.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");

    @Test
    public void delete_성공_질문자_답변자_같음() {
        Q1.deleteByUser(UserTest.JAVAJIGI);
        Q1.getAnswers().forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    public void delete_실패_질문자_답변자_다름() {
        Q1.getAnswers().forEach(answer ->
            assertThatThrownBy(
                () -> answer.deleteByUser(UserTest.SANJIGI)
            ).isInstanceOf(CannotDeleteException.class)
        );
    }
}
