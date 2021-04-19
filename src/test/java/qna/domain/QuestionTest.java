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
    @DisplayName("사용자를 인자로 받아 질문을 삭제한다.")
    public void delete() {
        //given
        Question question = new Question("test", "content").writeBy(UserTest.JAVAJIGI);

        //when
        question.delete(UserTest.JAVAJIGI);
        boolean deleted = question.isDeleted();

        then(deleted).isTrue();
    }

    @Test
    @DisplayName("삭제를 시도한 로그인 사용자가 질문한 사람이 아닐 경우 예외가 발생한다.")
    public void delete_error() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
