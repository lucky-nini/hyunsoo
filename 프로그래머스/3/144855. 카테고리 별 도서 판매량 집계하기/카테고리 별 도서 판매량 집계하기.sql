-- 코드를 입력하세요
SELECT b.CATEGORY, SUM(s.SALES) AS TOTAL_SALES
FROM BOOK as b
JOIN (SELECT * FROM BOOK_SALES WHERE YEAR(SALES_DATE)='2022' AND MONTH(SALES_DATE)='1') as s
ON b.BOOK_ID = s.BOOK_ID
GROUP BY b.CATEGORY
ORDER BY CATEGORY;