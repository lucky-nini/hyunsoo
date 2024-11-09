-- Front End 스킬을 가진 개발자의 정보를 조회
--  개발자의 ID, 이메일, 이름, 성을 조회
SELECT DISTINCT d.ID, d.EMAIL, d.FIRST_NAME, d.LAST_NAME
FROM DEVELOPERS d
JOIN SKILLCODES s
ON d.SKILL_CODE & s.CODE = s.CODE
WHERE s.CATEGORY = 'Front End'
ORDER BY d.Id;













# -- 코드를 작성해주세요
# SELECT DISTINCT d.ID, d.EMAIL, d.FIRST_NAME, d.LAST_NAME
# FROM DEVELOPERS d
# JOIN SKILLCODES s
# ON d.SKILL_CODE & s.CODE = s.CODE
# WHERE s.CATEGORY = 'Front End'
# ORDER BY d.ID;