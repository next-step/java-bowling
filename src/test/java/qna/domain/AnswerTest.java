package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

public class AnswerTest {
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        answer = new Answer(1L, JAVAJIGI, Q1, "Answers Contents1");
    }

    @DisplayName("작성자와 다르면 CannotDeleteException이 발생한다")
    @Test
    public void throw_exception() {
        assertThatThrownBy(() -> answer.delete(SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제에 성공하면 DeleteHistory를 반환한다")
    @Test
    public void should_return_delete_history_when_success_delete() {
        //arrange, act
        DeleteHistory deleteHistory = answer.delete(JAVAJIGI);

        //assert
        assertThat(deleteHistory).isInstanceOf(DeleteHistory.class);
    }

    @DisplayName("삭제시 플래그 값은 TRUE이다")
    @Test
    public void should_delete_flag_is_true() {
        //arrange
        //act
        answer.delete(JAVAJIGI);

        //assert
        assertTrue(answer.isDeleted());
    }
}
