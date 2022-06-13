//npm init -y
var mongoose = require('mongoose');
var faker = require('faker');
mongoose.connect('mongodb://nosql-mongodb/projtest');

var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));
db.once('open', function () {

	var Users_s = mongoose.Schema({
		Email_Address: String,
		First_Name: String,
		Last_Name: String,
		Phone_Number: String,
		Address_Line_1: String,
		Address_Line_2: String, City: String, State: String,
		Zip: String
	});

	var Inventory_s = mongoose.Schema({
		Ingr_name: String,
		Description: String,
		Quantity: Number,
	});

	var Recipes_s = mongoose.Schema({
		Rec_name: String,
		Description: String,
		List_of_ingredients: String,
		Cooking_instructions: String
	});

	var Orders_s = mongoose.Schema({
		User_Email: String,
		Time_Stamp: String,
		Recipe_Ingredient: String,
		Quantity_Ordered: Number
	});

	var Users = mongoose.model('Users', Users_s);
	var Inventory = mongoose.model('Inventory', Inventory_s);
	var Recipes = mongoose.model('Recipes', Recipes_s);
	var Orders = mongoose.model('Orders', Orders_s);
	var ingr_Ref = new Array(50);

	for (i = 0; i < 1000; i++) {
		var Users_insert = new Users({
			Email_Address: faker.unique(faker.internet.email),
			First_Name: faker.lorem.word(),
			Last_Name: faker.lorem.word(),
			Phone_Number: faker.unique(faker.phone.phoneNumberFormat),
			Address_Line_1: faker.lorem.words(),
			Address_Line_2: faker.address.secondaryAddress(), City: faker.lorem.words(), State: faker.address.state(),
			Zip: faker.address.zipCode()
		});

		Users_insert.save(function (err, Users_insert) { if (err) return console.error(err); })
	}

	for (i = 0; i < 50; i++) {
		ingr_Ref[i] = faker.unique(faker.lorem.word);
		var Inventory_insert = new Inventory({
			Ingr_name: ingr_Ref[i],
			Description: faker.lorem.words(),
			Quantity: faker.datatype.number()
		});

		Inventory_insert.save(function (err, Inventory_insert) { if (err) return console.error(err); })
	}

	for (i = 0; i < 50; i++) {
		var Recipes_insert = new Recipes({
			Rec_name: faker.unique(faker.internet.userName),
			Description: faker.lorem.words(),
			List_of_ingredients: ingr_Ref[i],
			Cooking_instructions: faker.lorem.sentence()
		});

		Recipes_insert.save(function (err, Recipes_insert) { if (err) return console.error(err); })
	}
	for (i = 0; i < 0; i++) {
		var Orders_insert = new Orders({
			User_Email: faker.unique(faker.internet.email),
			Time_Stamp: faker.datatype.datetime(),
			Recipe_Ingredient: faker.lorem.word(),
			Quantity_Ordered: faker.datatype.number()
		});

		Orders_insert.save(function (err, Orders_insert) { if (err) return console.error(err); })
	}

	for (i = 0; i < 5000; i++) {
		var user_e;
		var recipe_ingr;
		var current_ingr_quantity;


		Users.findOne({ Email_Address: faker.internet.email() }, function (err, obj) {
			if (obj) {
				user_e = obj.Email_Address;
			}
		});
		Recipes.findOne({ List_of_ingredients: faker.lorem.word() }, function (err, obj) {
			if (obj) {
				recipe_ingr = obj.List_of_ingredients;
			}
		});
		Inventory.findOne({ Ingr_name: faker.internet.email() }, function (err, obj) {
			if (obj) {
				current_ingr_quantity = obj.Quantity;
			}
		});

		var quantity_ordered = faker.datatype.number(5000);
		//console.log(user_e);
		if ((!user_e) || (!recipe_ingr)) {
			console.log("not found");
		}
		else {
			var Orders_insert = new Orders({
				User_Email: user_e,
				Time_Stamp: Date.now(),
				Recipe_Ingredient: recipe_ingr,
				Quantity_Ordered: quantity_ordered
			});
			var updated_quantity = current_ingr_quantity - quantity_ordered;
			Inventory.findOneAndUpdate({ Ingr_name: recipe_ingr }, { Quantity: updated_quantity });
			Orders_insert.save(function (err, Orders_insert) { if (err) return console.error(err); })
		}
	}

});