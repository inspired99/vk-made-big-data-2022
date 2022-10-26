--Исполнитель с максимальным числом скробблов 
WITH S AS (
    SELECT MAX(scrobbles_lastfm) as scrobbles_lastfm
    FROM artists
)
SELECT artists.mbid, artists.artist_mb, artists.scrobbles_lastfm
FROM artists
JOIN S 
ON artists.scrobbles_lastfm = S.scrobbles_lastfm;

