package qna.domain;

import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class DomainBuilder {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    protected static Question buildQuestionAndAnotherPeopleAnswers() {
        List<Answer> answers = Arrays.asList(AnswerTest.A1, AnswerTest.A2);
        Question question = new Question(1L, "title1", "contents1", answers).writeBy(UserTest.JAVAJIGI);

        return question;
    }

    protected static Question buildQuestionAndMyselfAnswers() {
        List<Answer> answers = Arrays.asList(AnswerTest.A1, AnswerTest.A1);
        Question question = new Question(1L,"title1", "contents1", answers).writeBy(UserTest.JAVAJIGI);
        return question;
    }


    protected static Stream<Arguments> createQuestionInstanceHasNotAnswers() {
        return Stream.of(
                Arguments.of(new Question(11L, "title1", "contents1", Collections.emptyList()).writeBy(UserTest.JAVAJIGI), UserTest.JAVAJIGI),
                Arguments.of(new Question(12L, "title2", "contents2", Collections.emptyList()).writeBy(UserTest.SANJIGI), UserTest.SANJIGI)
        );
    }

    protected static Stream<Arguments> createQuestionFailCaseInstance() {
        return Stream.of(
                Arguments.of(Q1, UserTest.SANJIGI),
                Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }

}
