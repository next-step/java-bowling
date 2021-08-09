package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("delete 테스트 - 답변이 없는 경우 성공")
    @Test
    void delete_답변이_없는_경우_성공() {
        DeletePipe deletePipe = new DeletePipe(UserTest.JAVAJIGI);

        question.delete(deletePipe);

        assertThat(question.isDeleted())
                .isTrue();
    }

    @DisplayName("delete 테스트 - 작성자가 다른 경우 실패")
    @Test
    void delete_질문자가_다른_경우_실패() {
        DeletePipe deletePipe = new DeletePipe(UserTest.SANJIGI);

        assertThatThrownBy(() -> question.delete(deletePipe))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.ErrorCode.NOT_PERMISSIONS.message());
    }

    @DisplayName("delete 테스트 - 답변자가 같은 경우 성공")
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

    @DisplayName("delete 테스트 - 달린 답변의 답변자가 다른 경우 실패")
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
