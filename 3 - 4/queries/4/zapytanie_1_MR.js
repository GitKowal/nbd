//1.Średnią wagę i wzrost osób w bazie z podziałem na płeć (tzn. osobno mężczyzn, osobno kobiet); 
printjson(db.people.mapReduce(
    function () {
        emit(this.sex, {
            weight: parseFloat(this.weight),
            height: parseFloat(this.height)
        })
    },
    function (key, values) {
        return {
            "avgHeight": Array.avg(values.map(v => v["height"])),
            "avgWeight": Array.avg(values.map(v => v["weight"])),
        }
    }, {
        out: {
            inline: 1
        }
    }

))