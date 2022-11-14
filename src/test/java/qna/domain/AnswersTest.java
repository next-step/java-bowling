package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.context.annotation.PropertySource;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @ParameterizedTest
    @DisplayName("isOwnerOfAll: 모든 답변이 유저의 답변인지 검증")
    @ArgumentsSource(UserArgumentsProvider.class)
    void isOwnerOfAll(User user, boolean result) {
        Answers answers = new Answers();
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents1"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents2"));
        answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "contents3"));

        boolean answersOwner = answers.isOwnerOfAll(user);

        assertThat(answersOwner).isEqualTo(result);
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
