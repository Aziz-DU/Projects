SELECT
    relname
FROM
    pg_class
WHERE
    relname = 'countries'
    or relname = 'cities'
    or relname = 'venues'
    or relname = 'events';