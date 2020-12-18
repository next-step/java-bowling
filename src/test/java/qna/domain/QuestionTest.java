package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("로그인 유저가 본인이 아닐 경우 예외 처리")
    void checkLoginUserSame() {
        Assertions.assertThrows(CannotDeleteException.class,
                () -> Q1.checkLoginUser(UserTest.SANJIGI));
    }

    @Test
    @DisplayName("delete 표시하기")
    void setDeletedTrue() {
        Question question = Q1.setDeletedTrue();
        assertThat(question.isDeleted()).isTrue();
    }
}
