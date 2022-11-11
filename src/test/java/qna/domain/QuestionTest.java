package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.Question.newQuestion;
import static qna.domain.Question.newQuestionWithDeleted;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {

    public static final Question Q1 = newQuestion(1L,"title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = newQuestion(2L,"title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question deleteQuestion;

    @BeforeEach
    public void setUpAnswer() throws Exception {
        deleteQuestion = newQuestionWithDeleted(1L, "title1", "contents1", true).writeBy(JAVAJIGI);
    }
    @Test
    @DisplayName("질문자와 로그인한 사람이 일치하지 않으면 예외 반환")
    void isOwnerException() {
        assertThatThrownBy(() -> Q1.validOwner(SANJIGI))
            .isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> Q2.validOwner(JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("논리 삭제 구현")
    void deleted() throws CannotDeleteException {
        Q1.delete(JAVAJIGI);
        assertThat(Q1).isEqualTo(deleteQuestion);
    }

}
