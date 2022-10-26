--Инсайт: топ 10 самых популярных рэперов из Америки
WITH S AS (
    SELECT artist_lastfm, country_mb, SPLIT(tags_lastfm, ';') AS tags, listeners_lastfm
    FROM artists
),  
T AS (
    SELECT S.artist_lastfm, T.tag AS tag, S.listeners_lastfm, S.country_mb
    FROM S
    LATERAL VIEW explode(tags) t AS tag
),
R AS (
    SELECT T.artist_lastfm AS artist,  MAX(T.listeners_lastfm) AS listeners
    FROM T 
    WHERE T.tag LIKE '%rap%' and T.country_mb LIKE '%United States%'
    GROUP BY T.artist_lastfm
    ORDER BY listeners 
    DESC 
    LIMIT 10
) 
SELECT * FROM R;
