--Самые популярные исполнители 10 самых популярных тегов ластфм
WITH S AS (
    SELECT artist_lastfm, SPLIT(tags_lastfm, ';') as tags, listeners_lastfm
    FROM artists
),  
T AS (
    SELECT S.artist_lastfm, tag, S.listeners_lastfm
    FROM S
    LATERAL VIEW explode(tags) t AS tag
),
R AS (
    SELECT T.tag AS Tag, SUM(T.listeners_lastfm) as popularity 
    FROM T
    WHERE T.tag <> '' and T.tag IS NOT NULL
    GROUP BY T.tag
    ORDER BY popularity DESC
    LIMIT 10
),
M AS (
    SELECT T.artist_lastfm, T.tag, T.listeners_lastfm, R.popularity
    FROM R 
    JOIN T 
    ON T.tag = R.tag
), 
L AS (
    SELECT M.tag AS tag, MAX(M.listeners_lastfm) AS max_listeners
    FROM M
    GROUP BY M.tag
)
SELECT M.tag AS tag, M.artist_lastfm AS artist, M.popularity AS tag_listeners
FROM M
JOIN L 
ON L.tag = M.tag and L.max_listeners = M.listeners_lastfm
ORDER BY tag_listeners
DESC;
