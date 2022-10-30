package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void setUp() {
        this.question = new Question(1L, "title1", "content1").writeBy(UserTest.JAVAJIGI);
    }

    @DisplayName("질문의 작성자가 만들고, 답변이 없는 질문을 삭제하면, 삭제 내역을 반환해야 한다.")
    @Test
    void deleteBy() {
        assertThat(question.deleteBy(UserTest.JAVAJIGI))
                .containsExactly(new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("질문의 작성자가 만들지 않은 질문을 삭제하면, 예외가 발생해야 한다.")
    @Test
    void deleteBy_whenNotOwned() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.deleteBy(UserTest.SANJIGI));
    }

    @DisplayName("질문의 작성자가 만들고, 질문의 작성자가 작성한 답변만 존재할 때 삭제하면, 삭제 내역을 반환해야 한다.")
    @Test
    void deleteBy_whenAllOwnedAnswers() {
        question.addAnswer(new Answer(1L, UserTest.JAVAJIGI, question, "answer1"));
        question.addAnswer(new Answer(2L, UserTest.JAVAJIGI, question, "answer2"));

        List<DeleteHistory> deleteHistories = question.deleteBy(UserTest.JAVAJIGI);
        assertThat(deleteHistories)
                .containsExactly(
                        new DeleteHistory(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, 2L, UserTest.JAVAJIGI, LocalDateTime.now())
                );
    }

    @DisplayName("질문의 작성자가 작성하지 않자 답변이 있는 질문을 삭제하면, 예외가 발생해야 한다.")
    @Test
    void deleteBy_whenNotOwnedAnswerExists() {
        question.addAnswer(new Answer(1L, UserTest.JAVAJIGI, question, "answer1"));
        question.addAnswer(new Answer(2L, UserTest.SANJIGI, question, "answer2"));

        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> question.deleteBy(UserTest.JAVAJIGI));
    }

}
