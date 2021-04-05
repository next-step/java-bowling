package qna.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question1;
    private Question question2;
    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    void setup() {
        this.question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        this.question2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

        this.answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        this.answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    void delete_이전(){
        assertThat(question1.isDeleted()).isFalse();
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        SoftAssertions softAssertions = new SoftAssertions();

        question1.delete(UserTest.JAVAJIGI);

        softAssertions.assertThat(question1.isDeleted()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            question2.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_같은_사람이_답변한_글() throws CannotDeleteException {
        SoftAssertions softAssertions = new SoftAssertions();

        question1.addAnswer(answer1);
        question1.delete(UserTest.JAVAJIGI);

        softAssertions.assertThat(question1.isDeleted()).isTrue();
        softAssertions.assertThat(answer1.isDeleted()).isTrue();
        softAssertions.assertAll();
    }

    @Test
    void delete_다른_사람이_답변한_글() {
        question1.addAnswer(answer2);

        assertThatThrownBy(() -> {
            question1.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
