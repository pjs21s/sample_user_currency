-- 사용자 테이블: user
CREATE TABLE user
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,                              -- 사용자 고유 ID (자동 증가)
    email       VARCHAR(100) NOT NULL UNIQUE,                                   -- 이메일 (고유 값, 중복 불가)
    name        VARCHAR(50)  NOT NULL,                                          -- 사용자 이름
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                            -- 생성일자 (기본값: 현재 시간)
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정일자 (수정 시 자동 갱신)
);

-- 통화 테이블: currency
CREATE TABLE currency
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,                              -- 통화 고유 ID(자동 증가)
    exchange_rate BITDECIMAL(10, 2) NOT NULL,                                        -- 환율 (소수점 2자리까지 저장)
    currency_name VARCHAR(50)    NOT NULL,                                        -- 통화 이름 (예: USD 등)
    symbol        VARCHAR(5)     NOT NULL,                                        -- 통화 기호 (예: $ 등)
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                            -- 생성일자 (기본값: 현재 시간)
    modified_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정일자 (수정 시 자동 갱신)
);

-- 교환 요청 테이블: exchangeRequest
CREATE TABLE exchange_claim
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,                               -- 통화 고유 ID (자동 증가)
    user_id     BIGINT      NOT NULL,                                            -- 고객 ID (User 테이블의 외래 키)
    currency_id BIGINT      NOT NULL,                                            -- 통화 ID (Currency 테이블의 외래 키)
    amount_in_krw Double NOT NULL,                                               -- 원화 기준 환전 금액 (소수점 2자리까지 저장)
    amount_after_exchange Double NOT NULL,                                       -- 환율 적용 후 금액 (소수점 2자리까지 저장)
    status      VARCHAR(20) NOT NULL,                                            -- 환전 요청 상태 (ex: normal, cancelled)
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,                             -- 생성 일자 (기본값: 현재 시간)
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 일자 (수정 시 자동 갱신)
    FOREIGN KEY (user_id) REFERENCES user (id),                                  -- user id는 User 테이블의 id를 참조
    FOREIGN KEY (currency_id) REFERENCES currency (id)                           -- currency_id는 Currency 테이블의 id를 참조
);
