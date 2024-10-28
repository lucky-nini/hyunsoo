-- 코드를 작성해주세요
SELECT i.ITEM_ID, i.ITEM_NAME
FROM ITEM_INFO i
INNER JOIN ITEM_TREE t
ON i.ITEM_ID = t.ITEM_ID
WHERE t.PARENT_ITEM_ID IS NULL
ORDER BY ITEM_ID