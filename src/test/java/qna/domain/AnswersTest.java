package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {
    @DisplayName("답변 게시물 삭제 권한 테스트 - 삭제 불가")
    @Test
    void canNotDeleteAnswer() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        Assertions.assertThatThrownBy(() -> {answers.checkDeleteAutorization(UserTest.SANJIGI);})
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 게시물 삭제 권한 테스트 - 삭제 가능")
    @Test
    void canDeleteAnswer() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A1);
        answers.checkDeleteAutorization(UserTest.JAVAJIGI);
    }
}