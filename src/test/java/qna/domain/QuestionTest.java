package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("내가 쓰지 않은 질문을 삭제하려고 할 경우, Exception을 반환해야 한다.")
    void assertIsDeleteOtherUsersQuestion() {
        Question question = new Question();
        assertThatThrownBy(() -> {
            Q1.assertUser(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
