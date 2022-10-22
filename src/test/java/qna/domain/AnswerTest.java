package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void createFail_whenWriterNull() {
        assertThatThrownBy(() -> new Answer(null, null, null))
                .isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void createFail_whenQuestionNull() {
        assertThatThrownBy(() -> new Answer(UserTest.JAVAJIGI, null, null))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void delete() throws CannotDeleteException {
        Answer answer = answer();
        DeleteHistory result = answer.delete(UserTest.JAVAJIGI);

        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, null);
        assertAll(
                () -> assertThat(result).isEqualTo(expected),
                () -> assertThat(answer.isDeleted()).isTrue()
        );
    }

    @DisplayName("작성자가 아니면 답변을 삭제시 예외 발생")
    @Test
    void deleteFail_whenNotOwner() {
        assertThatThrownBy(() -> answer().delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    public static Answer answer() {
        return (Answer) new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1").setId(1L);
    }
}
