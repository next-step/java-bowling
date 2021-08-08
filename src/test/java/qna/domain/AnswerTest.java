package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.domain.answer.Answer;
import qna.domain.question.Question;
import qna.dto.DeletePipe;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    void delete_작성자가_같은_경우_성공() {
        DeletePipe deletePipe = new DeletePipe(UserTest.JAVAJIGI);

        answer.delete(deletePipe);

        assertThat(answer.isDeleted())
                .isTrue();
    }

    @Test
    void delete_작성자가_다른_경우_실패() {
        DeletePipe deletePipe = new DeletePipe(UserTest.SANJIGI);

        assertThatThrownBy(() -> answer.delete(deletePipe))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.ErrorCode.NOT_PERMISSIONS.message());
    }
}
