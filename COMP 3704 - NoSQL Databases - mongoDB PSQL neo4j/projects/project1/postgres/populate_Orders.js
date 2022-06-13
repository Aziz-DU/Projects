//npm init -y
//npm i pg
//npm install faker --save-dev
var faker = require('faker');      
const fs = require('fs')
var insertOrders;
for (i = 0; i < 50000; i++) {
var userEmail = faker.internet.email();
var recipeName = faker.lorem.word();
    var Quantity = faker.datatype.number(1000);
    insertOrders += "SELECT add_order";
insertOrders +=  " ('"+userEmail +"','"+ recipeName+"','"+ Quantity+"');\n";
} 

insertOrders=insertOrders.slice(9);


fs.writeFileSync("insertOrders.sql", insertOrders)


