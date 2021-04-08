package qna.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    void setup() {
        this.answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        this.answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    void delete_이전() {
        assertThat(answer1.isDeleted()).isFalse();
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        SoftAssertions softAssertions = new SoftAssertions();

        answer1.delete(UserTest.JAVAJIGI);

        softAssertions.assertThat(answer1.isDeleted()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            answer2.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
