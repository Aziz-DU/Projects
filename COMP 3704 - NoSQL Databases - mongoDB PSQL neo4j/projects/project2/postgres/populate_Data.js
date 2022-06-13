const fs = require('fs')
var TreatmentTable = "INSERT INTO \"Treatment\" (\"treatment_name\") VALUES";
var treatments;
for (i = 1; i < 751; i++) {
   treatments = "t"+i;
TreatmentTable +=  " ('"+treatments+"'),";
} 
TreatmentTable=TreatmentTable.slice(0,-1);
TreatmentTable+=";";

var illnesses;
var IllnessesTable = "INSERT INTO \"Illnesses\" (\"illness_name\") VALUES"; 
for (i = 1; i < 1001; i++) {
  illnesses = "i"+i;
IllnessesTable +=  " ('"+illnesses+"'),";
} 
IllnessesTable=IllnessesTable.slice(0,-1);
IllnessesTable+=";";


var doctors = new Array(100);
var DoctorsTable = "INSERT INTO \"Doctors\" (\"doctor_name\",\"doctor_phone_number\",\"doctor_city\") VALUES"; 
for (i = 1; i < 101; i++) {
    doctors[i] =  "d"+i;
    var city = "d city"+i;
    var phn = i+i*i;
DoctorsTable +=  " ('"+doctors[i] +"',"+ phn+",'"+ city+"'),";
} 
DoctorsTable=DoctorsTable.slice(0,-1);
DoctorsTable+=";";

/////truncate "Patients","SickPatients","PatientTreatment","PatientIllness","People","Doctors","Treatment","Illnesses";
////ALTER SEQUENCE "Patients_patient_ID_seq" RESTART WITH 1
//ALTER SEQUENCE "Illnesses_illness_ID_seq" RESTART WITH 1
//Doctors_doctor_ID_seq People_person_ID_seq




var PatientsTable = "INSERT INTO \"Patients\" (\"patient_Name\",\"patient_phone_number\",\"patient_age\") VALUES"; 
var PeopleTable = "INSERT INTO \"People\" (\"patient_ID\",\"doctor_ID\",\"is_doctor\") VALUES"; 
var PatientIllness = "INSERT INTO \"PatientIllness\" (\"patient_ID\",\"illness_ID\") VALUES"; 
var PatientTreatment = "INSERT INTO \"PatientTreatment\" (\"patient_ID\",\"treatment_ID\") VALUES"; 
for (i = 1; i < 10001; i++) {
 p_name  = "pn"+i;
    var p_ph_number  =i+i*i;
    var p_age =  Math.floor(Math.random() * (90 - 1 + 1)) + 1;
    var p_num_of_docs =  Math.floor(Math.random() * (5 - 1 + 1)) + 1;
    var p_num_of_illnsTreat = Math.floor(Math.random() * 4); 
    var p_is_doc=false;
    var rng = Math.random();
    var doc_as_pat;
    if (rng < 0.35 && i<101){ //35% chance that a patient is a doctor, only first 100 people get to be docs
                            // because there are only 100 doctors and we were supposed to do .35 chance on them
      doc_as_pat =  Math.floor(Math.random() * (100 - 1 + 1)) + 1;
    p_name =doctors[doc_as_pat];
       p_is_doc=true;
    }
    PatientsTable +=  " ('"+p_name +"',"+ p_ph_number+",'"+ p_age+"'),";

    while(p_num_of_docs>0){
     var doc_for_doc=Math.floor(Math.random() * (100 - 1 + 1)) + 1;
     while(doc_as_pat==doc_for_doc&&p_is_doc){ ////if he's a doctor and he becomes his own doctor, loop until u get another doc
        doc_for_doc=Math.floor(Math.random() * (100 - 1 + 1)) + 1;
     }
        PeopleTable +=  " ("+i +","+ doc_for_doc+","+ p_is_doc+"),";
        p_num_of_docs--;
    }

while(p_num_of_illnsTreat>0){
    var rngIll=Math.floor(Math.random() * (1000 - 1 + 1)) + 1;
    var rngTrt=Math.floor(Math.random() * (750 - 1 + 1)) + 1;
       PatientIllness +=  " ("+i +","+ rngIll+"),";
       PatientTreatment +=  " ("+i +","+ rngTrt+"),";

       p_num_of_illnsTreat--;
}
} 
PatientsTable=PatientsTable.slice(0,-1);
PatientsTable+=";";
PeopleTable=PeopleTable.slice(0,-1);
PeopleTable+=";";
PatientIllness=PatientIllness.slice(0,-1);
PatientIllness+=";";
PatientTreatment=PatientTreatment.slice(0,-1);
PatientTreatment+=";";





fs.writeFileSync("insertTreatments.sql", TreatmentTable)
fs.writeFileSync("insertIllnesses.sql", IllnessesTable)
fs.writeFileSync("insertDoctors.sql", DoctorsTable)
fs.writeFileSync("insertPatients.sql", PatientsTable)
fs.writeFileSync("insertPeople.sql", PeopleTable)
fs.writeFileSync("insertP_I.sql", PatientIllness)
fs.writeFileSync("insertP_T.sql", PatientTreatment)