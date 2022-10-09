# 기능 구현 목록

## step2 (볼링 게임 점수판만 구현)
### 입력
- [x] 플레이어의 이름 입력 받기
- [ ] 각 프레임의 투구 결과 입력 받기


### 출력
- [ ] 각 프레임마다의 결과 출력
  - [ ] 스트라이크 : "X"
  - [ ] 스페어 : "7|/"
  - [ ] 미스 : "8|1"
  - [ ] 거터 : "-"


### 도메인
- [x] 투구 후 조건에 맞는 상태 반환
  - [x] 노멀(normal) : 프레임의 첫번째 투구에서 모든 핀을 쓰러트리지 못한 상태
  - [x] 스트라이크(strike) : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
  - [x] 스페어(spare) : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
  - [x] 미스(miss) : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
- [x] 다음 프레임으로 넘어가는 조건
  - [x] Strike
  - [x] Spare
  - [x] Miss
- [x] 10 프레임은 보너스 투구가 존재한다.
  - [x] 보너스 투구를 할 수 있는 조건
    - [x] 1번째 투구에서 스트라이크
    - [x] 2번째 투구에서 스페어
  - [x] 게임 종료 조건
    - [x] 보너스 투구 완료 시
    - [x] miss 일 경우 보너스 투구 제외
  - [x] 9프레임에서 10프레임으로 넘어갈 시 FinalFrame 반환
- [ ] 플레이어 이름 예외 처리 (조건 : 3 english letters)
  - [x] 입력 값 반환 받기
  - [x] null or "" 입력 시
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
  - [ ] 한 프레임 안에서의 점수 합이 10이 넘을 시