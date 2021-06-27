import scala.annotation.tailrec

object Zad1 {

  def main(args: Array[String]): Unit = {

    // 1.	Stwórz 7 elementową listę zawierającą nazwy dni tygodnia.
    val weekDays = List("poniedziałek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziela")

    // Napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
    // a. Pętli for
    println("1a: " + joinStrings(weekDays))

    // b.	Pętli for wypisując tylko dni z nazwami zaczynającymi się na „P”
    println("1b: " + joinStrings(weekDays.filter(_.charAt(0) == 'p')))

    // c. Pętli while
    println("1c: " + joinStrings2(weekDays))

    // 2.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
    // a.	Funkcji rekurencyjnej
    println("2a: " + joinStringsRec(weekDays))
    // b.	Funkcji rekurencyjnej wypisując elementy listy od końca
    println("2b: " + joinStringsRecRev(weekDays))

    // 3.	Stwórz funkcję korzystającą z rekurencji ogonowej do zwrócenia oddzielonego przecinkami stringa zawierającego
    // elementy listy z ćwiczenia 1
    println("3a: " + joinStringsTailRec(weekDays))

    // 4.	Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
    // a.	Metody foldl
    println("4a: " + joinStringsFoldl(weekDays))
    // b.	Metody foldr
    println("4b: " + joinStringsFoldr(weekDays))
    // c.	Metody foldr wypisując tylko dni z nazwami zaczynającymi się na „P”
    println("4c: " + joinStringsFoldl(weekDays.filter(_.charAt(0) == 'p')))

    // 5.	Stwórz mapę z nazwami produktów i cenami. Na jej podstawie wygeneruj drugą, z 10% obniżką cen. Wykorzystaj mechanizm
    // mapowania kolekcji.
    val saleMap = Map("Mleko" -> 2.80, "Cukier" -> 2.70).map(mapItem => {
      (mapItem._1, mapItem._2 * 0.9)
    })
    println("5: " + saleMap)

    // 6.	Zdefiniuj funkcję przyjmującą krotkę z 3 wartościami różnych typów i wypisującą je
    val t = ("alamakota", 15, 15.02F)
    printTuple(t)

    // 7.	Zaprezentuj działanie Option na dowolnym przykładzie (np. mapy, w której wyszukujemy wartości po kluczu)
    val notExistingProductPrice = saleMap.get("Kawa")
    val existingProductPrice = saleMap.get("Mleko")
    println("7: notExistingProduct: " + notExistingProductPrice.getOrElse(0D))
    println("existingProduct: " + existingProductPrice.getOrElse(0D))

    // 8.	Napisz funkcję usuwającą zera z listy wartości całkowitych (tzn. zwracającą listę elementów mających wartości różne od 0).
    // Wykorzystaj rekurencję.
    println("8: " + deleteZeros(List(0,1,0,5,0,0,1,2)))

    // 9.	Zdefiniuj funkcję, przyjmującą listę liczb całkowitych i zwracającą wygenerowaną na jej podstawie listę, w której wszystkie liczby
    // zostały zwiększone o 1. Wykorzystaj mechanizm mapowania kolekcji.
    println("9: " + addOneToEach(List(0,1,2,3,15,-8)))

    // 10.	Stwórz funkcję przyjmującą listę liczb rzeczywistych i zwracającą stworzoną na jej podstawie listę zawierającą
    // wartości bezwzględne elementów z oryginalnej listy należących do przedziału <-5,12>. Wykorzystaj filtrowanie.
    println("10: " + absInRange(List(1.25, -2.1212, -99.12, 12.0001, -4.999999)))
  }

  def absInRange(values: List[Double]): List[Double] = {
    values.filter(value => value >= -5 && value <= 12).map(va => va.abs)
  }

  def addOneToEach(intList: List[Int]): List[Int] = {
    intList.map(i => i + 1)
  }

  def deleteZeros(intList: List[Int]): List[Int] = {
    val listSize = intList.size

    def deleteZero(index: Int, integers: List[Int]): List[Int] = {
      if (index.equals(listSize)) integers
      else if (intList(index) == 0) deleteZero(index + 1, integers)
      else deleteZero(index + 1, integers ++ List(intList(index)))
    }
    deleteZero(0, List())
  }

  def printTuple(t: (String, Int, Float)): Unit = {
    println(String.format("6: %s, %d, %f", t._1, t._2, t._3))
  }

  def joinStrings(weekDays: List[String]): String = {
    val sb = new StringBuilder()
    var isFirst = true
    for (d <- weekDays) {
      if (isFirst) {
        sb.++=(d)
        isFirst = false
      }
      else sb.++=(s", $d")
    }
    sb.toString()
  }

  def joinStrings2(weekDays: List[String]): String = {
    val sb = new StringBuilder()
    val iterator = weekDays.iterator
    var isFirst = true
    while (iterator.hasNext)
      if (isFirst) {
        sb.++=(iterator.next())
        isFirst = false
      }
      else sb.++=(", " + iterator.next())
    sb.toString()
  }

  def joinStringsRec(weekDays: List[String]): String = {
    def joinString(i: Int): String = {
      if (i == weekDays.length - 1) weekDays(i)
      else weekDays(i) + ", " + joinString(i + 1)
    }

    joinString(0)
  }

  def joinStringsRecRev(weekDays: List[String]): String = {
    def joinString(i: Int): String = {
      if (i <= 0) weekDays(i)
      else weekDays(i) + ", " + joinString(i - 1)
    }

    joinString(weekDays.length - 1)
  }

  def joinStringsTailRec(weekDays: List[String]): String = {
    @tailrec
    def joinString(i: Int, concatString: String): String = {
      if (i == weekDays.length - 1) return concatString + weekDays(i)
      joinString(i + 1, concatString + weekDays(i) + ", ")
    }

    joinString(0, "")
  }

  def joinStringsFoldl(weekDays: List[String]): String = {
    weekDays.foldLeft("")(_ + _ + ", ").dropRight(2)
  }

  def joinStringsFoldr(weekDays: List[String]): String = {
    weekDays.foldRight("")(", " + _ + _).substring(2)
  }
}
