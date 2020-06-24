package qna.domain;

import org.junit.BeforeClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private User loginUser;

    @BeforeClass
    void setUp() {
        loginUser = UserTest.JAVAJIGI;
    }

    @Test
    @DisplayName("질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태만 변경한다.")
    void delete_성공() {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.deleteBy(loginUser);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
