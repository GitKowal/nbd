//4.Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości; 
printjson(db.people.aggregate(
  [{
    $group: {
      _id: "$nationality",
      minBMI: {
        $min: {
          $divide: [{
              $toDouble: '$weight'
            },
            {
              $pow: [{
                $toDouble: '$height'
              }, 2]
            }
          ]
        }
      },
      maxBMI: {
        $max: {
          $divide: [{
              $toDouble: '$weight'
            },
            {
              $pow: [{
                $toDouble: '$height'
              }, 2]
            }
          ]
        }
      },
      avgBMI: {
        $avg: {
          $divide: [{
              $toDouble: '$weight'
            },
            {
              $pow: [{
                $toDouble: '$height'
              }, 2]
            }
          ]
        }
      }
    }
  }]
).toArray())