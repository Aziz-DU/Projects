-- works on an earlier version of the project where i had a table that takes arrays to insert multiple doctors/illness etc...
SELECT add_patient_func1('1106',11,26,'{1106, 1106, 1106}','{trac, wow}','{yooo, illx, yas}',3,3,2);
CREATE OR REPLACE FUNCTION public.add_patient_func1(
	p_name text,
	p_ph_number integer,
	p_age integer,
	p_doc_names text[],
	p_treatments text[],
	p_illnesses text[],
	p_num_of_docs integer,
	p_num_of_illns integer,
	p_num_of_treats integer)
RETURNS BOOLEAN AS $$
DECLARE
  added_new_p BOOLEAN := false;
  doctor_n text;
  patient_id integer;
  patient_n text;
  doctor_id integer;
  illn_id integer;
  treat_id integer;
   counter integer := 0;
BEGIN
  
   SELECT Y."patient_Name" INTO patient_n
  FROM "Patients" Y WHERE Y."patient_Name"=p_name;

IF patient_n is NULL THEN
     INSERT INTO "Patients" ("patient_Name", "patient_phone_number", "patient_age")
    VALUES (p_name, p_ph_number, p_age); 	
    added_new_p := true;
	END IF;

  while counter <= p_num_of_docs loop

    SELECT U."doctor_name" INTO doctor_n
  FROM "Doctors" U WHERE U."doctor_name"=p_doc_names[counter];
      SELECT U."doctor_ID" INTO doctor_id
  FROM "Doctors" U WHERE U."doctor_name"=p_doc_names[counter];
  
  IF p_name NOT LIKE p_doc_names[counter] AND doctor_n IS NOT NULL THEN  

  
	SELECT Y."patient_ID" INTO patient_id
  	FROM "Patients" Y WHERE Y."patient_Name"=p_name;
	
   INSERT INTO "SickPatients" ("patient_ID", "doctor_ID")
    VALUES (patient_id, doctor_id);
	
	 RAISE NOTICE 'Added: %', doctor_n;
  ELSE
   RAISE NOTICE 'Assigned doctor doesnt exist or is himself';
  END IF;

	  counter := counter + 1;
   end loop;	
 counter :=0;
  while counter <= p_num_of_treats loop

    SELECT U."treatment_name" INTO treat_n
  FROM "Treatment" U WHERE U."treatment_name"=p_treatments[counter];
      SELECT U."treatment_ID" INTO treat_id
  FROM "Treatment" U WHERE U."treatment_name"=p_treatments[counter];
  
  IF treat_n IS NOT NULL THEN

   INSERT INTO "PatientTreatment" ("patient_ID", "treatment_ID")
    VALUES (patient_id, treat_id);
	
	RAISE NOTICE 'Added treatment: %', treat_n;
  ELSE
   RAISE NOTICE 'treatment not found';
  END IF;
	  counter := counter + 1;
   end loop;	

counter :=0;
  while counter <= p_num_of_illns loop

   SELECT U."illness_name" INTO illn_n
  FROM "Illnesses" U WHERE U."illness_name"=p_illnesses[counter];
      SELECT U."illness_ID" INTO illn_id
  FROM "Illnesses" U WHERE U."illness_name"=p_illnesses[counter];
  
  IF illn_n IS NOT NULL THEN

   INSERT INTO "PatientIllness" ("patient_ID", "illness_ID")
    VALUES (patient_id, illn_id);
	
	 RAISE NOTICE 'Added illness: %', illn_n;
  ELSE
   RAISE NOTICE 'illness not found ';
  END IF;
	  counter := counter + 1;
   end loop;	
 counter :=0;	

  RETURN added_new_p;
END;
$$ LANGUAGE plpgsql;
