object Zad2 {

  def main(args: Array[String]): Unit = {
    // 1.	Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String. Dla stringów odpowiadających nazwom
    // dni tygodnia funkcja ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych), dla pozostałych napisów „Nie ma takiego dnia”.
    println("1: Poniedziałek: " + kindOfDay("Poniedziałek"))
    println("alamakota: " + kindOfDay("alamakota"))

    // 2.	Zdefiniuj klasę KontoBankowe z metodami wplata i wyplata oraz własnością stanKonta - własność ma być tylko do odczytu.
    // Klasa powinna udostępniać konstruktor przyjmujący początkowy stan konta oraz drugi, ustawiający początkowy stan konta na 0.
    val kontoBankowe = new KontoBankowe()
    println("2: wpłata: " + kontoBankowe.wplata(15.34))
    println("stan konta: " + kontoBankowe.getStanKonta)
    println("wypłata: " + kontoBankowe.wyplata(12.12))

    // 3.	Zdefiniuj klasę Osoba z własnościami imie i nazwisko. Stwórz kilka instancji tej klasy. Zdefiniuj funkcję,
    // która przyjmuje obiekt klasy osoba i przy pomocy Pattern Matching wybiera i zwraca napis zawierający przywitanie
    // danej osoby. Zdefiniuj 2-3 różne przywitania dla konkretnych osób (z określonym imionami lub nazwiskami) oraz jedno domyślne.
    val o1 = Osoba("Paweł", "Kowal")
    val o2 = Osoba("Jan", "Kowalski")
    val o3 = Osoba("Zuzanna", "Iksińska")
    println("3: " + getGreeting(o1))
    println(getGreeting(o2))
    println(getGreeting(o3))

    // 4.	Zdefiniuj funkcję przyjmującą dwa parametry - wartość całkowitą i funkcję operującą na wartości całkowitej.
    // Zastosuj przekazaną jako parametr funkcję trzykrotnie do wartości całkowitej i zwróć wynik.
    val startValue = 2
    def pow(value: Int): Int = value*2
    println("4: " + threeTimes(startValue, pow))

    // 5.	Zdefiniuj klasę Osoba i trzy traity: Student, Nauczyciel, Pracownik. Osoba powinna mieć własności read only:
    // imie, nazwisko, podatek. Pracownik powinien mieć własność pensja (z getterem i seterem). Student i Pracownik powinni
    // przesłaniać własność podatek – dla Studenta zwracamy 0, dla Pracownika 20% pensji. Nauczyciel powinien dziedziczyć
    // z Pracownika, dla niego podatek zwraca 10% pensji. Stwórz obiekty z każdym z traitów, pokaż jak podatek działa dla
    // każdego z nich. Stwórz obiekty z traitami Student i Pracownik, pokaż jak podatek zadziała w zależności od kolejności
    // w jakiej te traity zostały dodane przy tworzeniu obiektu.
    val o4 = new Osoba("Paweł", "Kowal") with Student
    println(s"5: Podatek studenta wynosi: ${o4.podatek}")

    val o5 = new Osoba("Paweł", "Kowal") with Pracownik {
      override var pensja: Double = 3000
    }
    println(s"Podatek pracownika zarabiającego ${o5.pensja} wynosi: ${o5.podatek}")

    val o6 = new Osoba("Paweł", "Kowal") with Nauczyciel {
      override var pensja: Double = 5500
    }
    println(s"Podatek nauczyciela zarabiającego ${o6.pensja} wynosi: ${o6.podatek}")

    val o7 = new Osoba("Paweł", "Kowal") with Student with Pracownik {
      override var pensja: Double = 2000
    }
    println(s"Podatek studenta-pracownika zarabiającego ${o7.pensja} wynosi: ${o7.podatek}")

    val o8 = new Osoba("Paweł", "Kowal") with Pracownik with Student {
      override var pensja: Double = 2000
    }
    println(s"Podatek pracownika-studenta zarabiającego ${o8.pensja} wynosi: ${o8.podatek}")
  }

  def threeTimes(startValue: Int, function: Int => Int): Int = {
    function(function(function(startValue)))
  }

  def getGreeting(osoba: Osoba): String = {
    osoba match {
      case Osoba("Paweł", "Kowal") => "Cześć, to ja robiłem te zadanka ;)"
      case Osoba("Jan", "Kowalski") => "Witam serdecznie to ja - ten Pan od listów"
      case _ => s"Hej! Jestem ${osoba.imie} ${osoba.nazwisko}"
    }
  }

  def kindOfDay(day: String): String = {
    day.toLowerCase().trim() match {
      case "poniedziałek" => "Praca"
      case "wtorek" => "Praca"
      case "środa" => "Praca"
      case "czwartek" => "Praca"
      case "piątek" => "Praca"
      case "sobota" => "Weekend"
      case "niedziela" => "Weekend"
      case _ => "Nie ma takiego dnia"
    }
  }

}
