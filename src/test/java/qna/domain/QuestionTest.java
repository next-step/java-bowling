package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Question Q3 = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);

    @DisplayName("질문 생성 테스트")
    @Test
    void created_질문_생성테스트() {
        // when
        Question expected = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        // then
        assertThat(Q1).isEqualTo(expected);
    }
}
