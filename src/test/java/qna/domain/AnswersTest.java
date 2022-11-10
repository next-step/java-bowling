package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    @DisplayName("isOwnerOfAll: 모든 답변이 유저의 답변인지 검증")
    void isOwnerOfAll() {
        Answers answers = new Answers();
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents1"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents2"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents3"));

        boolean allAnswersOwner = answers.isOwnerOfAll(UserTest.JAVAJIGI);
        boolean noAnswersOwner = answers.isOwnerOfAll(UserTest.SANJIGI);

        assertThat(allAnswersOwner).isTrue();
        assertThat(noAnswersOwner).isFalse();
    }

    @Test
    @DisplayName("isOwnerOfAll: 하나라도 다른 사람의 답변이면 false 반환")
    void isOwnerOfAll_false() {
        Answers answers = new Answers();
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents1"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents2"));
        answers.add(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "contents3"));

        boolean allAnswersOwner = answers.isOwnerOfAll(UserTest.JAVAJIGI);

        assertThat(allAnswersOwner).isFalse();
    }
}
