package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("본인이쓰지않은 글 삭제 불가 테스트")
    public void tryOtherUserTest() {
        assertThatThrownBy(() -> {
            Q1.checkUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("본인이 쓴글 지우는 테스트")
    public void tryUserSelfTest() throws CannotDeleteException {
        Q1.checkUser(UserTest.JAVAJIGI);
        Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();
    }
}
