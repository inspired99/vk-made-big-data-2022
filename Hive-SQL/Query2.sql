--Самый популярный тэг на ластфм
WITH S AS (
    SELECT artist_lastfm, SPLIT(tags_lastfm, ';') as tags, listeners_lastfm
    FROM artists
),  
T AS (
    SELECT S.artist_lastfm, tag, S.listeners_lastfm
    FROM S
    LATERAL VIEW explode(tags) t AS tag
)
SELECT T.tag, SUM(T.listeners_lastfm) as popularity 
FROM T
WHERE T.tag <> '' and T.tag IS NOT NULL
GROUP BY T.tag
ORDER BY popularity DESC
LIMIT 1;

