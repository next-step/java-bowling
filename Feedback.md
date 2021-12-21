## STEP 1

- 테스트케이스 실패 상황 발생
```
static 객체 별도로 구현
```

- Question delete() 반환 값 추가
```
DeleteHistories 객체로 반환 진행
```

- 클린코드 5장 형식 맞추기 읽어보기
  [링크](http://amazingguni.github.io/blog/2016/05/Clean-Code-5-%ED%98%95%EC%8B%9D-%EB%A7%9E%EC%B6%94%EA%B8%B0)

- 각각의 delete 메소드에서 DeleteHistory 를 반환해보는 방법
```
현재는 Question에서 삭제할 때 완료 후 한 번에 DeleteHistories를 만드는 방법.
하지만, 의견주신 방법으로 바꾸게 된다면
Answer만을 삭제할 때에 DeleteHistory를 리턴하게 되어 해당 로직을 재사용할 수 있게 된다.
```