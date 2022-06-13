select
    street_address -- our query is searching for the street address of a venue that starts with 2199, we used the % wildcard 
from
    venues
where
    street_address ILIKE '2199%';