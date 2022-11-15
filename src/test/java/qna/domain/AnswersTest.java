package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AnswersTest {
    public static Answers ANSWERS1 = new Answers();

    @Test
    public void addAnswer() {
        ANSWERS1.add(AnswerTest.A1);
        ANSWERS1.add(AnswerTest.A2);
    }

    @Test
    public void addDeleteHistories() {
        List<DeleteHistory> histories = new ArrayList<>();
        ANSWERS1.addDeleteHistories(histories);
        assertThat(histories.size()).isEqualTo(2);
    }

    @Test
    public void deleteAll() {
        ANSWERS1.deleteAll();
    }

    @Test
    public void isAllOwners() {
        assertThat(ANSWERS1.isAllOwner(UserTest.JAVAJIGI)).isEqualTo(false);
        assertThat(ANSWERS1.isAllOwner(UserTest.SANJIGI)).isEqualTo(false);

        Answer a1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer a2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents12");

        Answers answers = new Answers(List.of(a1, a2));
        assertThat(answers.isAllOwner(UserTest.JAVAJIGI)).isEqualTo(true);
    }
}
