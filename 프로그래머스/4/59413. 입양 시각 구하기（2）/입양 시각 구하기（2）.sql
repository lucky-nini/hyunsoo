# 0시부터 23시까지, 각 시간대별로 입양이 몇 건이나 발생했는지 조회

SET @HOUR = -1;
SELECT (@HOUR := @HOUR + 1), (SELECT COUNT(ANIMAL_ID)
    FROM ANIMAL_OUTS
    WHERE HOUR(DATETIME) = @HOUR)
FROM ANIMAL_OUTS
WHERE @HOUR < 23


















# -- 코드를 입력하세요
# SET @HOUR = -1;
# SELECT (@HOUR := @HOUR +1) AS HOUR, (SELECT COUNT(HOUR(DATETIME)) FROM ANIMAL_OUTS WHERE HOUR(DATETIME)=@HOUR) AS COUNT
# FROM ANIMAL_OUTS
# WHERE @HOUR < 23;