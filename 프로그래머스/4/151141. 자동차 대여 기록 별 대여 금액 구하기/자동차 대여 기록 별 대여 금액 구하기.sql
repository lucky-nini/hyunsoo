-- 코드를 입력하세요
-- 자동차 종류가 '트럭'인 자동차의 대여 기록에 대해서 대여 기록 별로 대여 금액(컬럼명: FEE)을 구하여 대여 기록 ID와 대여 금액 리스트를 출력
-- 대여 금액을 기준으로 내림차순 정렬하고, 대여 금액이 같은 경우 대여 기록 ID를 기준으로 내림차순 정렬

-- 대여 금액 구해보기


-- 1. 일단 daily_fee랑 기간
# SELECT c.CAR_TYPE, c.DAILY_FEE, 
#     CASE
#         WHEN TIMESTAMPDIFF(day, h.START_DATE, h.END_DATE) >= 90 THEN '90일 이상'
#         WHEN TIMESTAMPDIFF(day, h.START_DATE, h.END_DATE) >= 30 THEN '30일 이상'
#         WHEN TIMESTAMPDIFF(day, h.START_DATE, h.END_DATE) >= 7 THEN '7일 이상'
#         ELSE '7일 미만'
#     END AS DURATION_TYPE
# FROM CAR_RENTAL_COMPANY_CAR c
# JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY h
# ON c.CAR_ID = h.CAR_ID

SELECT o.HISTORY_ID, ROUND(o.DAILY_FEE * (1-0.01*IFNULL(p.DISCOUNT_RATE, 0)) * o.DURATION , 0) AS FEE
FROM (SELECT h.HISTORY_ID, c.CAR_TYPE, c.DAILY_FEE, 
        CASE
            WHEN TIMESTAMPDIFF(day, h.START_DATE, h.END_DATE) + 1 >= 90 THEN '90일 이상'
            WHEN TIMESTAMPDIFF(day, h.START_DATE, h.END_DATE) + 1 >= 30 THEN '30일 이상'
            WHEN TIMESTAMPDIFF(day, h.START_DATE, h.END_DATE) + 1 >= 7 THEN '7일 이상'
            ELSE '7일 미만'
        END AS DURATION_TYPE, TIMESTAMPDIFF(day, h.START_DATE, h.END_DATE) + 1 AS DURATION
    FROM CAR_RENTAL_COMPANY_CAR c
    JOIN CAR_RENTAL_COMPANY_RENTAL_HISTORY h
    ON c.CAR_ID = h.CAR_ID) o
LEFT JOIN CAR_RENTAL_COMPANY_DISCOUNT_PLAN p
ON o.CAR_TYPE = p.CAR_TYPE AND o.DURATION_TYPE = p.DURATION_TYPE
WHERE o.CAR_TYPE='트럭'
ORDER BY FEE DESC, HISTORY_ID DESC;