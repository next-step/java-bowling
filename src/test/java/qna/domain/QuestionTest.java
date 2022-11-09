package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(11L, UserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(12L, UserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer1);
        question.addAnswer(answer2);
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        question.getAnswers()
                .forEach(answer -> assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    void delete_질문_작성자_다름() {
        assertThrows(CannotDeleteException.class, () -> question.delete(UserTest.SANJIGI));
    }

    @Test
    void delete_답변_작성자_다름() {
        Answer answer3 = new Answer(11L, UserTest.SANJIGI, question, "Answers Contents1");
        question.addAnswer(answer3);
        assertThrows(CannotDeleteException.class, () -> question.delete(UserTest.JAVAJIGI));
    }

    @Test
    void isDeletableBy_성공() throws CannotDeleteException {
        boolean deletable = question.isDeletableBy(UserTest.JAVAJIGI);
        assertThat(deletable).isTrue();
    }

    @Test
    void isDeletableBy_질문_작성자_다름() {
        assertThrows(CannotDeleteException.class, () -> question.isDeletableBy(UserTest.SANJIGI));
    }

    @Test
    void isDeletableBy_답변_작성자_다름() {
        Answer answer3 = new Answer(11L, UserTest.SANJIGI, question, "Answers Contents1");
        question.addAnswer(answer3);
        assertThrows(CannotDeleteException.class, () -> question.isDeletableBy(UserTest.JAVAJIGI));
    }

    @Test
    void isAllAnswerOwnedBy_성공() throws CannotDeleteException {
        assertThat(question.isAllAnswerOwnedBy(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void isAllAnswerOwnedBy_실패() {
        Answer answer3 = new Answer(11L, UserTest.SANJIGI, question, "Answers Contents1");
        question.addAnswer(answer3);
        assertThrows(CannotDeleteException.class, () -> question.isDeletableBy(UserTest.JAVAJIGI));
    }
}
