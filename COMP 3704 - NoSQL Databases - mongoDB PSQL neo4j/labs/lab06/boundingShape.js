db.cities.aggregate([
  {
    $geoNear: {
      near: [51.5074, 0.1278],
      distanceField: 'dist',
      maxDistance: 1404.3141361257
    }
  },
  {
    $project: {
      _id: 0,
      name: 1
    }
  }
])
