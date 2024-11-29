# 환전 요청 프로그램

### 한국 원을 기준으로 특정 통화 환전이 가능한 프로그램

## 기능
### 1. 사용자 테이블, 환전 테이블 등록, 사용자 삭제
####  * 이름, 이메일 등록
####  * 통화 이름, 환율, 상징 등록
####  * 사용자 삭제 시, userId를 외래키로 가진 테이블의 데이터도 모두 삭제
----
### 2. 환전 요청
#### * 요청 userId, 한국 돈, CurrencyId 입력 시, 환전 요청 전송(status = "normal") - default
----
### 3. 특정 사용자 환전 요청 건 조회
#### * userId 조회 시, 해당 사용자의 환전 요청 데이터 모두 조회
----
### 4. 특정 환전 상태 값 수정
#### * exchangeId를 기준으로 해당 status의 값을 "normal" -> "cancel"로 수정함.

## ERD
![image](https://github.com/user-attachments/assets/07dc41b7-6dd3-4047-9e3b-fdd77ebd91aa)

## API 명세서
![image](https://github.com/user-attachments/assets/fc4d6fee-ee71-4949-9b2e-f711839fc196)

## 트러블슈팅 TIL
#### URL : https://redbull97.tistory.com/23

