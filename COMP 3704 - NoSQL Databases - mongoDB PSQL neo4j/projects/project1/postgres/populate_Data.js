//npm init -y
//npm i pg
//npm install faker --save-dev
var faker = require('faker');
const fs = require('fs')
var inventoryTable = "INSERT INTO public.\"Inventory\" (\"Ingr_Name\",\"Description\",\"Quantity\") VALUES";
var ingr = new Array(50);
for (i = 0; i < 50; i++) {
     ingr[i] = faker.unique(faker.lorem.word);
    var desc = faker.lorem.words();
    var nm = faker.datatype.number();
inventoryTable +=  " ('"+ingr[i] +"','"+ desc+"','"+ nm+"'),";
} 
inventoryTable=inventoryTable.slice(0,-1);
inventoryTable+=";";

var insertRecipe = "INSERT INTO public.\"Recipes\" (\"Rec_Name\",\"Description\",\"List_of_ingredients\",\"Cooking_instructions\") VALUES";
for (i = 0; i < 50; i++) {
    var recip = faker.unique(faker.internet.userName);
    var desc = faker.lorem.words();
    var instruc = faker.lorem.sentence();
insertRecipe +=  " ('"+recip +"','"+ desc+"','"+ ingr[i]+"','"+ instruc+"'),";
} 
insertRecipe=insertRecipe.slice(0,-1);
insertRecipe+=";";

var insertUsers = "insert into public.\"Users\" (\"Email_Address\",\"First_Name\",\"Last_Name\",\"Phone_Number\",\"Address_Line_1\",\"Address_Line_2\",\"City\",\"State\",\"Zip\") values";
for (i = 0; i < 50000; i++) {
    var email = faker.unique(faker.internet.email);
    var fn = faker.lorem.word();
    var ln = faker.lorem.word();
    var phn = faker.unique(faker.phone.phoneNumberFormat);
    var adrOne = faker.lorem.words();
    var adrTwo = faker.address.secondaryAddress();
    var city = faker.lorem.words();
    var state = faker.address.state();
    var zip = faker.address.zipCode();
insertUsers +=  " ('"+email +"','"+ fn+"','"+ ln+"','"+phn +"','"+ adrOne+"','"+ adrTwo+"','"+city +"','"+ state+"','"+ zip+"'),";
} 
insertUsers=insertUsers.slice(0,-1);
insertUsers+=";";


fs.writeFileSync("insertInventory.sql", inventoryTable)
fs.writeFileSync("insertRecipe.sql", insertRecipe)
fs.writeFileSync("insertUsers.sql", insertUsers)
