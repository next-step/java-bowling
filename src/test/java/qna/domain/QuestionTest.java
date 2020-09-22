package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("답변글이 없는 질문 삭")
    void deleteQuestion() throws Exception {
        // when
        Q1.deleteQuestion(UserTest.JAVAJIGI);

        // then
        assertTrue(Q1.isDeleted());
    }
}
