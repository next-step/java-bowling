package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    private Question q1;
    private Question q2;

    @BeforeEach
    void init() {
        q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

    @Test
    void deleteTest() {
        q1.delete(UserTest.JAVAJIGI);

        assertThat(q1.isDeleted()).isTrue();

    }

    @Test
    @DisplayName("질문의 저자인 경우, True를 반환한다.")
    void isOwnerTest() {
        assertThat(q1.isOwner(UserTest.JAVAJIGI)).isTrue();
        assertThat(q1.isOwner(UserTest.SANJIGI)).isFalse();
    }

    @Test
    @DisplayName("질문의 저자가 아닌경우, True를 반환한다.")
    void isNotOwnerTest() {
        assertThat(q1.isNotOwner(UserTest.JAVAJIGI)).isFalse();
        assertThat(q1.isNotOwner(UserTest.SANJIGI)).isTrue();
    }
}
