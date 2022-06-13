db.towns.find({name: {$regex:"e"}, $or: [{famousFor:{$regex: 'food'}}, {famousFor:{$regex: 'beer'}}]})
