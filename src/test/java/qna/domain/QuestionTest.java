package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {

    public static Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

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

    @DisplayName("Question에 answer가 제대로 추가되는지")
    @Test
    void addAnswer() {
        // Given
        Answer givenA1 = AnswerTest.A1;
        Q1.addAnswer(givenA1);

        // When && Then
        assertThat(givenA1.question()).isEqualTo(Q1);
        assertThat(Q1.answers()).isEqualTo(new Answers(Collections.singletonList(givenA1)));
    }

}
