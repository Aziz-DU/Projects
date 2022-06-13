createdb project2 
psql project2 --	// I used "" because i was working with pgadmin at first and made everything around having ""s
CREATE TABLE "Doctors" (
    "doctor_ID" SERIAL PRIMARY KEY,
    "doctor_name" text NOT NULL,
    "doctor_phone_number" integer,
    "doctor_city" text
);

CREATE TABLE "Illnesses" (
    "illness_ID" SERIAL PRIMARY KEY,
    "illness_name" text UNIQUE
);

CREATE TABLE "Treatment" (
    "treatment_ID" SERIAL PRIMARY KEY,
    "treatment_name" text NOT NULL UNIQUE
);

CREATE TABLE "Patients" (
    "patient_ID" SERIAL PRIMARY KEY,
    "patient_Name" text NOT NULL,
    "patient_phone_number" integer,
    "patient_age" integer
);

CREATE TABLE "People" (
    "person_ID" SERIAL PRIMARY KEY,
    "patient_ID" integer REFERENCES "Patients" ("patient_ID"),
    "doctor_ID" integer REFERENCES "Doctors" ("doctor_ID"),
    "is_doctor" boolean
);

CREATE TABLE "PatientIllness" (
    "patient_ID" integer REFERENCES "Patients" ("patient_ID"),
    "illness_ID" integer REFERENCES "Illnesses" ("illness_ID")
);

CREATE TABLE "PatientTreatment" (
    "patient_ID" integer REFERENCES "Patients" ("patient_ID"),
    "treatment_ID" integer REFERENCES "Treatment" ("treatment_ID")
);