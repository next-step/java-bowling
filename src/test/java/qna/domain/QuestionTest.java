package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문을 삭제할 권한이 있는지 채크한다. 없으면 exception 발생")
    @Test
    void validateOwner() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            Q1.validateOwner(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);

    }

    @DisplayName("질문을 삭제하라는 메세지를 주고 삭제 할 수 있는 객체인지 확인 해본다.")
    @Test
    void deleteQuestion() {
        Q1.deleteQuestion();
        boolean deleted = Q1.isDeleted();
        assertThat(deleted).isTrue();
    }
}
