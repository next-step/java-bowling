package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers(Answer... answers) {
        this(Arrays.asList(answers));
    }

    public void validateIfHasOthersAnswer(User loginUser) throws CannotDeleteException {
        Optional<Answer> othersAnswer = answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny();
        if (othersAnswer.isPresent()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public Answers deleteAll() {
        List<Answer> result = answers.stream().map(Answer::delete).collect(Collectors.toUnmodifiableList());
        return new Answers(result);
    }

    public List<DeleteHistory> generateDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            Answer deletedAnswer = answer.delete();
            deleteHistories.add(deletedAnswer.generateDeleteHistory());
        }
        return deleteHistories;
    }
}
