package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @DisplayName("질문을 생성한다")
    @Test
    void questionInitTest() {
        assertThat(Q1.isDeleted()).isEqualTo(FALSE);
    }

    @DisplayName("유저는 다른 유저의 질문을 삭제할 수 없다")
    @Test
    void questionDeleteExceptionTest() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("자신의 답변이 달린 자신의 질문, 답변이 없는 질문은 삭제할 수 있다")
    @Test
    void questionDeleteTest() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isEqualTo(TRUE);
        assertThat(AnswerTest.A1.isDeleted()).isEqualTo(TRUE);
        Q2.delete(UserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isEqualTo(TRUE);
    }

    @DisplayName("타인의 답변이 달린 자신의 질문은 삭제할 수 없다")
    @Test
    void questionWitAnswerDeleteExceptionTest() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
