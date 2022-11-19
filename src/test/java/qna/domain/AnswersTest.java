package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AnswersTest {
    public static Answers ANSWERS1;

    @BeforeAll
    static void setUp() {
        Answer a1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer a2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents12");

        ANSWERS1 = new Answers(Lists.newArrayList(a1, a2));
    }

    @Test
    public void when_addAnswer() {
        ANSWERS1.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3"));
    }

    @Test
    public void when_addDeleteHistories() {
        List<DeleteHistory> histories = new ArrayList<>();
        ANSWERS1.addDeleteHistories(histories);
        assertThat(histories.size()).isEqualTo(2);
    }

    @Test
    public void when_deleteAll_then_isAllDeleted() {
        ANSWERS1.deleteAll();
        assertThat(ANSWERS1.isAllDeleted()).isEqualTo(true);
    }

    @Test
    public void when_isAllOwners_given_user() {
        Answer a1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer a2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents12");

        Answers answers = new Answers(List.of(a1, a2));
        assertThat(answers.isAllOwner(UserTest.JAVAJIGI)).isEqualTo(true);
    }
}
