package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.domain.answer.Answer;
import qna.domain.question.Question;
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

    @DisplayName("delete 테스트 - 작성자가 같은 경우 삭제")
    @Test
    void delete_작성자가_같은_경우_성공() {
        answer.delete(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted())
                .isTrue();
    }

    @DisplayName("delete 테스트 - 작성자가 다른 경우 실패")
    @Test
    void delete_작성자가_다른_경우_실패() {
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.ErrorCode.NOT_PERMISSIONS.message());
    }
}
