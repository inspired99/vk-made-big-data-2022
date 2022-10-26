--Самый популярный тэг на ластфм
WITH S AS (
    SELECT artist_lastfm, SPLIT(tags_lastfm, ';') as tags
    FROM artists
),  
T AS (
    SELECT S.artist_lastfm, T.tag
    FROM S
    LATERAL VIEW explode(tags) t AS tag
)
SELECT T.tag, count(T.tag) as times_occured 
FROM T 
WHERE T.tag <> '' and T.tag IS NOT NULL
GROUP BY T.tag 
ORDER BY times_occured DESC 
LIMIT 1;
