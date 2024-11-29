-- 사용자 입력
insert into currency_user.user (name, email)
values('kim', 'kim@naver.com');

-- 환전테이블 등록
insert into currency_user.currency(currency_name, exchange_rate, symbol)
values ('doller', 1000, '$');

-- 환전 요청
insert into currency_user.exchange( user_id, to_currency_id, amount_in_krw, status, exchange_rate, amount_after_exchange )
    (SELECT 7                       AS user_id,          -- 입력된 user_id
            c.id                    AS to_currency_id,   -- 입력된 currency_id
            10000                   AS amount_in_krw,    -- 입력된 금액
            c.currency_name,                             -- currency 테이블에서 가져온 통화 이름
            c.exchange_rate,                             -- currency 테이블에서 가져온 환율
            10000 * c.exchange_rate AS calculated_amount -- 계산된 환전 금액
     FROM currency_user.currency c
     WHERE c.id = 7-- 입력된 to_currency_id와 일치하는 값
);


-- 특정 사용자 환전 요청 조회
select *
from currency_user.exchange
where user_id = 7;

-- 환전 요청 건 상태값 변경
update currency_user.exchange
set status = 'cancel'
where user_id = 1;

-- 사용자 삭제
delete from currency_user.user where id = 1;
