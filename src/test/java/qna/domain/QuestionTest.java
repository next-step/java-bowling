package qna.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void deleteAnswer() {
        // Given
        Answer givenA1 = AnswerTest.A1;
        Q1.addAnswer(givenA1);

        // When
        Q1.deleteAnswer(new DeleteHistories());

        // Then
        assertThat(givenA1.isDeleted()).isTrue();
    }


}
