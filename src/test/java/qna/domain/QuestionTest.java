package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.domain.answer.Answer;
import qna.exception.CannotDeleteException;
import qna.domain.question.Question;
import qna.dto.DeletePipe;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

    private Question question;

    @BeforeEach
    public void setUp() {
        question = new Question("title1", "title2").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    void delete_답변이_없는_경우_성공() {
        DeletePipe deletePipe = new DeletePipe(UserTest.JAVAJIGI);

        question.delete(deletePipe);

        assertThat(question.isDeleted())
                .isTrue();
    }

    @Test
    void delete_질문자가_다른_경우_실패() {
        DeletePipe deletePipe = new DeletePipe(UserTest.SANJIGI);

        assertThatThrownBy(() -> question.delete(deletePipe))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.ErrorCode.NOT_PERMISSIONS.message());
    }

    @Test
    void delete_답변자가_같은_경우_성공() {
        DeletePipe deletePipe = new DeletePipe(UserTest.JAVAJIGI);

        question.addAnswer(
                new Answer(UserTest.JAVAJIGI, question, "Answers Contents1")
        );
        question.delete(deletePipe);

        assertThat(question.isDeleted())
                .isTrue();
    }

    @Test
    void delete_답변자가_다른_경우_실패() {
        DeletePipe deletePipe = new DeletePipe(UserTest.JAVAJIGI);

        question.addAnswer(
                new Answer(UserTest.SANJIGI, question, "Answers Contents1")
        );

        assertThatThrownBy(() -> question.delete(deletePipe))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.ErrorCode.EXISTS_ANSWER.message());
    }
}
