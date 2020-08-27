package qna.domain;

import java.time.LocalDateTime;
import java.util.List;

import qna.CannotDeleteException;

import static java.util.stream.Collectors.toList;

final class Answers {
    private final List<Answer> answers;

    Answers(List<Answer> answers) {
        this.answers = answers;
    }

    static Answers of(List<Answer> answers){

        return new Answers(answers);
    }

    void verifyAllOwner(User longinUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.verifyOwner(longinUser);
        }
    }

    private DeleteHistory toDeleteHistory(Answer answer){
        return new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
    }

    List<DeleteHistory> toDeleteHistories(){
        return answers.stream()
            .map(this::toDeleteHistory)
            .collect(toList());
    }

    void deletedAllAnswer(){
        for (Answer answer : answers) {
            answer.setDeleted(true);
        }
    }
}
