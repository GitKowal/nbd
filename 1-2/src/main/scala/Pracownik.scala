trait Pracownik extends Osoba {
  var pensja: Double
  override def podatek: Double = this.pensja * 0.20
}
