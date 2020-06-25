package qna.domain;

import org.junit.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void throwExceptionWhenNoAuthorization() {
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            Q1.validate(UserTest.SANJIGI);
        }).withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }
}
