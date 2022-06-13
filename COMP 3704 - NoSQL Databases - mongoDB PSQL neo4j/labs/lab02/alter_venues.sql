CREATE
OR REPLACE FUNCTION add_order(
    User_Namef text,
    Order_Timef timestamp,
    Recipe_Namef text,
    RETURNS boolean AS $$ 
    
DECLARE 
    Time_of_order timestamp;

    did_insert boolean := false;

    cur_quantity integer;

    updated_inv integer;

    Ingr_N text;

    order_quantity integer;

    cur_record record;

BEGIN 
    Time_of_order := clock_timestamp();
    Order_Timef=Time_of_order;
    FOR cur_record IN
    SELECT
        *
    FROM
        Inventory
    WHERE
        Inventory.Rec_Nm = Recipe_Namef LOOP Ingr_N := cur_record.Ingr_Name;

    order_quantity := cur_record.U_Quantity;

    SELECT
        Quantity INTO cur_quantity
    FROM
        inventory
    WHERE
        inventory.Ingr_Name = Ingr_N;

    IF cur_quantity < order_quantity THEN RAISE NOTICE 'Can’t make the order, ingredients aren’t available!';

    did_insert := false;

    EXIT;

    ELSE updated_inv := cur_quantity - order_quantity;

    UPDATE
        Inventory
    SET
        Quantity = updated_inv
    WHERE
        Ingr_Name = Ingr_N;

    END IF;

END LOOP;

IF did_insert = true THEN
INSERT INTO
    Orders (User_Name, Time_Stamp, Recipe_Name)
VALUES
    (User_Namef, Order_Timef, Recipe_Namef);

ELSE RAISE NOTICE 'Can’t make the order, ingredients aren’t available!';

END IF;

RETURN did_insert;

END;

$ $ LANGUAGE plpgsql;