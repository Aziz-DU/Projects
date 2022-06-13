
    SELECT * FROM crosstab(
  'SELECT extract(week from starts) as week, extract(dow from starts) as dow, count(*) FROM events 
  GROUP BY week, dow
  ORDER BY week, dow', 
	'SELECT  * from generate_series(1,7)'
  ) AS (
        week int,
        sunday int,
        monday int,
        tuesday int,
        wednesday int,
        thursday int,
        friday int,
        saturday int
);

