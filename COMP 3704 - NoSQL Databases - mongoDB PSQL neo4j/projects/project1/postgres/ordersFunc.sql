CREATE OR REPLACE FUNCTION add_order(
	user_namef text,
	recipe_ingf text,
	order_quantity integer)
RETURNS BOOLEAN AS $$
DECLARE
  did_insert BOOLEAN := false;
  current_quantity integer;
  order_userE text;
  Recipe_ingredient text;
BEGIN
  SELECT U."Email_Address" INTO order_userE
  FROM "Users" U WHERE U."Email_Address"=user_namef;
    SELECT R."List_of_ingredients" INTO Recipe_ingredient
  FROM "Recipes" R WHERE R."List_of_ingredients"=recipe_ingf;
  SELECT Q."Quantity" INTO current_quantity
  FROM "Inventory" Q ;

  IF current_quantity > 0 AND current_quantity >= order_quantity AND order_userE is NOT NULL 
 AND Recipe_ingredient is NOT NULL THEN
    INSERT INTO "Orders" ("order_userE", "Time_Stamp", "Recipe_Name","Quantity_Ordered")
    VALUES (user_namef, CURRENT_TIMESTAMP, recipe_ingf, order_quantity);
    UPDATE "Inventory" SET "Quantity" = "Quantity" - order_quantity;
    did_insert := true;
  END IF;

IF order_userE is NULL OR Recipe_ingredient is NULL THEN
    RAISE NOTICE 'User or ingredient don’t exist';
  END IF;
  
  IF current_quantity <= 0 OR current_quantity < order_quantity THEN
  UPDATE "Orders" SET "Time_Stamp" = now();
    RAISE NOTICE 'Can’t make the order, ingredients aren’t available!';
  END IF;
  RETURN did_insert;
END;
$$ LANGUAGE plpgsql;
