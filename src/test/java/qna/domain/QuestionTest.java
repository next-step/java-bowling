package qna.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question1;
    private Question question2;

    @BeforeEach
    void setup() {
        this.question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        this.question2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    }

//    @Test
//    void delete_성공() throws CannotDeleteException {
//        SoftAssertions softAssertions = new SoftAssertions();
//        softAssertions.assertThat(question1.isDeleted()).isFalse();
//
//        question1.preCheckDeletion(UserTest.JAVAJIGI);
//        question1.delete();
//
//        softAssertions.assertThat(question1.isDeleted()).isTrue();
//
//        softAssertions.assertAll();
//    }
//
//    @Test
//    void delete_다른_사람이_쓴_글() {
//        assertThatThrownBy(() -> {
//            question2.preCheckDeletion(UserTest.JAVAJIGI);
//        }).isInstanceOf(CannotDeleteException.class);
//    }
}
