package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(
        UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deleteTest() {

    }

    @Test
    @DisplayName("질문의 저자인 경우, True를 반환한다.")
    void isOwnerTest() {
        assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isTrue();
        assertThat(Q1.isOwner(UserTest.SANJIGI)).isFalse();
    }

    @Test
    @DisplayName("질문의 저자가 아닌경우, True를 반환한다.")
    void isNotOwnerTest() {
        assertThat(Q1.isNotOwner(UserTest.JAVAJIGI)).isFalse();
        assertThat(Q1.isNotOwner(UserTest.SANJIGI)).isTrue();
    }
}
