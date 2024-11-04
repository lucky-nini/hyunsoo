-- 코드를 입력하세요
SELECT o.ANIMAL_ID, o.ANIMAL_TYPE, o.NAME
FROM ANIMAL_OUTS o
JOIN ANIMAL_INS i
ON o.ANIMAL_ID = i.ANIMAL_ID
WHERE i.SEX_UPON_INTAKE LIKE 'Intact%' AND (o.SEX_UPON_OUTCOME LIKE 'Spayed%' OR o.SEX_UPON_OUTCOME LIKE 'Neutered%')
ORDER BY ANIMAL_ID;