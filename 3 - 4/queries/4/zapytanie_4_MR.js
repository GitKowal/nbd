//4.Średnie, minimalne i maksymalne BMI (waga/wzrost^2) dla osób w bazie, w podziale na narodowości; 
printjson(db.people.mapReduce(
    function () {
        emit(this.nationality, {
            weight: parseFloat(this.weight),
            height: parseFloat(this.height)
        })
    },
    function (key, values) {
        return {
            "minBMI": Math.min.apply(Math, values.map(v => v["weight"] / (Math.pow(v["height"], 2)))),
            "maxBMI": Math.max.apply(Math, values.map(v => v["weight"] / (Math.pow(v["height"], 2)))),
            "avgBMI": Array.avg(values.map(v => v["weight"] / (Math.pow(v["height"], 2))))
        }
    }, {
        out: {
            inline: 1
        }
    }

))