package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("삭제 유저정보 미입력")
    void delete_userIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Q1.delete(null))
                .withMessageMatching("유저 정보를 입력해 주세요.");
    }

    @Test
    @DisplayName("질문을 삭제할 권한은 작성자만 가짐")
    void deletePermission() {
        Assertions.assertThrows(CannotDeleteException.class, () -> Q1.delete(UserTest.SANJIGI), "질문을 삭제할 권한이 없습니다.");
    }
}
