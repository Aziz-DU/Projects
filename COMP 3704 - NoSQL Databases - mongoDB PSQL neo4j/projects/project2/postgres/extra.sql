
    --How many doctors are treating doctors?
	-- doctor_ID | is_doctor | sum
-----------+-----------+-----
 	 --      99 | t         |  67

	--67
select  PPL."doctor_ID",PPL."is_doctor",  sum(count(DISTINCT PPL."doctor_ID")) OVER (ORDER BY PPL."doctor_ID")  from "People" PPL 
where PPL."is_doctor"= true group by PPL."doctor_ID",PPL."is_doctor" order by PPL."doctor_ID" DESC LIMIT 1;
	


    --What's the count of how many patients have each kind of illness?
	--qillness_ID | count
------------+-------
     --   652 |    14
     --   273 |    19
    --     51 |    22
     --   951 |    17
		--etc
	SELECT PAT_I."illness_ID", count(PAT_I."illness_ID") from "PatientIllness" PAT_I group by PAT_I."illness_ID";



    --What's the doctor with the most patients?
--	 doctor_ID | count
-----------+-------
  --      71 |   343
	select   PPL."doctor_ID", count(PPL."patient_ID")  from "People" PPL group by PPL."doctor_ID" order by count(PPL."doctor_ID") DESC LIMIT 1;


    --Which doctor is treating the largest number of unique illnesses?
	-- patient_ID | uniqueillnesses
------------+-----------------
   --      13 |               3
	SELECT PPL."patient_ID",  count(DISTINCT PAT_I."illness_ID") as uniqueIllnesses FROM "People" PPL INNER JOIN "PatientIllness" PAT_I  ON PPL."patient_ID" = PAT_I."patient_ID" 
AND PPL."is_doctor" IS TRUE GROUP BY PPL."patient_ID" ORDER BY uniqueIllnesses DESC LIMIT 1;




    --What illness is being treated with the largest number of unique treatments?
	-- illness_ID | uniquetreatments
------------+------------------
   --      72 |               63
	SELECT PAT_I."illness_ID",  count(DISTINCT PAT_T."treatment_ID") as uniqueTreatments FROM "PatientIllness" PAT_I INNER JOIN "PatientTreatment" PAT_T ON PAT_I."patient_ID" =PAT_T."patient_ID" 
 	GROUP BY PAT_I."illness_ID" ORDER BY uniqueTreatments DESC LIMIT 1;