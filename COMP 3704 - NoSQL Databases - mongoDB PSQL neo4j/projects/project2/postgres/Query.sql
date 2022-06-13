SELECT PAT."patient_Name",PPL."patient_ID",PPL."doctor_ID",PPL."is_doctor",PAT_I."illness_ID",PAT_T."treatment_ID" FROM "People" PPL INNER JOIN "Patients" PAT
	ON PPL."patient_ID" = PAT."patient_ID" INNER JOIN "PatientIllness" PAT_I
	ON PPL."patient_ID" = PAT_I."patient_ID" INNER JOIN "PatientTreatment" PAT_T  --joining the tables to get a query that shows relations
	ON PPL."patient_ID" = PAT_T."patient_ID";									--because we joined the tables we can get everything in one query

 --patient_Name | patient_ID | doctor_ID | is_doctor | illness_ID | treatment_ID
--------------+------------+-----------+-----------+------------+--------------
-- pn2          |          2 |        26 | f         |        859 |          437
 --pn2          |          2 |        54 | f         |        859 |          437
 --pn2          |          2 |        62 | f         |        859 |          437
 --pn3          |          3 |        60 | f         |        232 |          412
 --pn3          |          3 |        25 | f         |        232 |          412
 --pn5          |          5 |        23 | f         |        382 |          468
 --pn5          |          5 |        23 | f         |        234 |          468
 --pn5          |          5 |        23 | f         |        743 |          468
--etc

select   PPL."doctor_ID", COUNT(PPL."patient_ID")  as numPats from "People" PPL group by PPL."doctor_ID" order by numPats DESC LIMIT 10;-- top 10 doctors with most patients
--  doctor_ID | numpats
-- -----------+---------
--         71 |     343
--         31 |     339
--         68 |     339
--         88 |     333
--          1 |     332
--         27 |     331
--         76 |     329
--         93 |     328
--          7 |     327
--         24 |     327


select   PPL."patient_ID", COUNT(PPL."doctor_ID") as numDocs from "People" PPL group by PPL."patient_ID" order by PPL."patient_ID";--how many doctors each patient has 
-- patient_ID | numdocs
-- ------------+---------
--           1 |       3
--           2 |       3
--           3 |       2
--           4 |       5
--           5 |       5
--           6 |       1
--           7 |       3
--           8 |       2
--           9 |       3
--          10 |       3
--          11 |       4
--          12 |       4
--          13 |       1
-- 		 etc