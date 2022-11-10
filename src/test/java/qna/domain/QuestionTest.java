package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("isOwnerOfAllAnswer: 모든 답변이 유저의 답변인지 검증")
    void isOwnerOfAllAnswer() {
        Question question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "contents2"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "contents3"));

        boolean allAnswersOwner = question.isOwnerOfAllAnswer(UserTest.JAVAJIGI);
        boolean noAnswersOwner = question.isOwnerOfAllAnswer(UserTest.SANJIGI);

        assertThat(allAnswersOwner).isTrue();
        assertThat(noAnswersOwner).isFalse();
    }

    @Test
    @DisplayName("isOwnerOfAllAnswer: 하나라도 다른 사람의 답변이면 false 반환")
    void isOwnerOfAllAnswer_false() {
        Question question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "contents2"));
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "contents3"));

        boolean allAnswersOwner = question.isOwnerOfAllAnswer(UserTest.JAVAJIGI);

        assertThat(allAnswersOwner).isFalse();
    }

    @Test
    @DisplayName("delete 검증")
    void delete() {
        Question question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "contents1"));

        DeleteHistory history = question.delete();

        assertThat(question).hasFieldOrPropertyWithValue("deleted", true);
        assertThat(history)
                .hasFieldOrPropertyWithValue("contentId", question.getId())
                .hasFieldOrPropertyWithValue("contentType", ContentType.QUESTION)
                .hasFieldOrPropertyWithValue("deletedBy", UserTest.JAVAJIGI);
    }
    @Test
    @DisplayName("deleteAnswers 검증")
    void deleteAnswers() {
        Question question = new Question("title", "contents").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "contents1"));
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "contents2"));

        List<DeleteHistory> histories = question.deleteAnswers();

        assertThat(question).hasFieldOrPropertyWithValue("deleted", false);
        assertThat(histories.size()).isEqualTo(2);
        histories.forEach(history -> assertThat(history)
                .hasFieldOrPropertyWithValue("contentType", ContentType.ANSWER)
                .hasFieldOrPropertyWithValue("deletedBy", UserTest.JAVAJIGI)
        );
    }
}
