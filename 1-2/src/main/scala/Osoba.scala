case class Osoba(imie: String, nazwisko: String) extends AbstractOsoba {
  override def podatek(): Double = 5
}
