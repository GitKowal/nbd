//6.Dodaj siebie do bazy, zgodnie z formatem danych użytych dla innych osób (dane dotyczące karty kredytowej, adresu zamieszkania i wagi mogą być fikcyjne); 
printjson(db.people.insertOne({
    "sex": "Male",
    "first_name": "Paweł",
    "last_name": "Kowal",
    "job": "Junior Java Dev",
    "email": "wfields0@diigo.com",
    "location": {
        "city": "Oyo",
        "address": {
            "streetname": "Beilfuss",
            "streetnumber": "860"
        }
    },
    "description": "vulputate justo in blandit ultrices enim lorem ipsum dolor sit amet consectetuer adipiscing elit proin interdum mauris",
    "height": "152.38",
    "weight": "66.81",
    "birth_date": "1990-02-21T02:55:03Z",
    "nationality": "Nigeria",
    "credit": [{
        "type": "switch",
        "number": "6759888939100098699",
        "currency": "COP",
        "balance": "5117.06"
    }]
}))