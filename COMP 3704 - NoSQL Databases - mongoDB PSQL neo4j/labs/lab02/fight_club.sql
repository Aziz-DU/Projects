select
    country_name
from
    countries
where
    country_code =(
        SELECT
            country_code
        FROM
            venues
            INNER JOIN events ON events.venue_id = venues.venue_id
        WHERE
            events.title = 'Fight Club'
    );