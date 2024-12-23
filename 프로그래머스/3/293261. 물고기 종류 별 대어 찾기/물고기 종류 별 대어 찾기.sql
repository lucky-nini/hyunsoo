-- 코드를 작성해주세요
SELECT f.ID, n.FISH_NAME, f.LENGTH
FROM (SELECT f.ID, f.FISH_TYPE, f.LENGTH
    FROM FISH_INFO f
    JOIN (SELECT FISH_TYPE, MAX(LENGTH) as MAX
        FROM FISH_INFO
        GROUP BY FISH_TYPE) j
    ON f.FISH_TYPE = j.FISH_TYPE
    WHERE f.LENGTH = j.MAX) f
JOIN FISH_NAME_INFO n
ON f.FISH_TYPE = n.FISH_TYPE
ORDER BY f.ID;