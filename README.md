# 기능 구현 목록

## step2 (볼링 게임 점수판만 구현)
### 입력
- [ ] 플레이어의 이름 입력 받기
- [ ] 각 프레임의 투구 결과 입력 받기


### 출력
- [ ] 각 상태마다의 출력 구현
  - [ ] 스트라이크 : "X"
  - [ ] 스페어 : "7|/"
  - [ ] 미스 : "8|1"
  - [ ] 거터 : "-"


### 도메인
- [ ] 플레이어 이름 예외 처리 (조건 : 3 english letters)
  - [ ] null or "" 입력 시
  - [ ] 숫자 입력 시
  - [ ] 특수문자 입력 시
  - [ ] 공백 입력 시
  - [ ] 소문자 입력 시
  - [ ] 3자를 초과할 시
- [ ] 각 프레임의 투구 결과 예외 처리 (1 ~ 10)
  - [ ] null or "" 입력 시
  - [ ] 1 ~ 10 의 범위를 벗어난 숫자 입력 시
  - [ ] 한.영 문자를 입력 시
  - [ ] 특수 문자 입력 시
  - [ ] 공백 입력 시
- [ ] 투구 후 조건에 맞는 상태 반환
  - [ ] 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  - [ ] 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  - [ ] 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
  - [ ] 거터(gutter) : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시
- [ ] 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.