# 기능 요구 사항 정리
## `Player` 클래스
* player 의 이름은 3글자이다.

## `Frame` 클래스
* Frame 은 10개의 프레임을 갖는다.
* 1 ~ 9 프레임을 NormalFrame, 10 프레임을 FinalFrame 과 같은 구조로 구현한 후 Frame 을 추가해 중복을 제거해 본다.
* 다음 Frame 을 현재 Frame 외부에서 생성하기 보다 현재 Frame 에서 다음 Frame 을 생성하는 방식으로 구현해 보고, 어느 구현이 더 좋은지 검토해 본다.
* 10 프레임은 스트라이크이거나 스페어이면 한 번을 더 투구할 수 있다.

## `PitchingResult`
* strike, spare, miss, gutter 타입을 갖는다.
* strike : 프레임의 첫번째 투구에서 모든 핀(10개)을 쓰러트린 상태
* spare : 프레임의 두번재 투구에서 모든 핀(10개)을 쓰러트린 상태
* miss : 프레임의 두번재 투구에서도 모든 핀이 쓰러지지 않은 상태
* gutter : 핀을 하나도 쓰러트리지 못한 상태. 거터는 "-"로 표시

## `Frame` 클래스

## `NormalFrame` 클래스

## `FinalFrame` 클래스

## `Score` 클래스