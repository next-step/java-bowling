package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswersTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

    @Test
    @DisplayName("Answer 추가")
    void add() {
        // given
        Answers actual = new Answers();
        Answer answer = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");

        // when
        actual.add(answer);

        // then
        Answers expected = new Answers(Arrays.asList(answer));
        assertEquals(expected, actual);
    }
}
