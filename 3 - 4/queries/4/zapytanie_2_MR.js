//2.Łączną ilość środków pozostałych na kartach kredytowych osób w bazie, w podziale na waluty; 
printjson(db.people.mapReduce(
    function () {
        this.credit.forEach(c => emit(c.currency, parseFloat(c.balance)))
    },
    function (key, values) {
        return {
            "sum": Array.sum(values)
        }
    }, {
        out: {
            inline: 1
        }
    }

))