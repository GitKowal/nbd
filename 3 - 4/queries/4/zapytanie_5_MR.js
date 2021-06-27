//5.Średnia i łączna ilość środków na kartach kredytowych kobiet narodowości polskiej w podziale na waluty. 
printjson(db.people.mapReduce(
    function () {
        this.credit.forEach(c => emit(c.currency, parseFloat(c.balance)))
    },
    function (key, values) {
        return {
            "sum": Array.sum(values),
            "avg": Array.avg(values)
        }
    }, {
        query: {
            nationality: 'Poland',
            sex: 'Female'
        },
        out: {
            inline: 1
        }
    }

))