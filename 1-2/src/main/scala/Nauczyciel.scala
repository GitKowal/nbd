trait Nauczyciel extends Pracownik {
  override def podatek: Double = this.pensja * 0.10
}
