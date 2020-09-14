package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문이 로그인 된 사용자에 의해 작성 됐는지 검증")
    void validateOwnerWhenDeleting() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문의 답변들이 로그인 된 사용자에 의해 작성 됐는지 검증")
    void validateAnswersWhenDeleting() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문을 삭제하는 메소드 검증")
    void delete() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI);

        then(Q1.isDeleted()).isTrue();
        then(AnswerTest.A1.isDeleted()).isTrue();
    }
}
