package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 작성자가 로그인 사용자와 같을 경우 삭제")
    @Test
    void deleteTest() throws Exception {
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문 작성자가 로그인 사용자와 다를 경우 예외 발생")
    @Test
    void cannotDeleteTest() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
            .isExactlyInstanceOf(CannotDeleteException.class)
            .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문 작성자와 답변 작성자가 다른 사람일 경우 예외 발생")
    @Test
    void cannotDeleteDifferentWriter() {
        Q2.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI))
            .isExactlyInstanceOf(CannotDeleteException.class);
    }
}
