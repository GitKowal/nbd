class KontoBankowe(stanPocz: Double) {

  private var stanKonta: Double = stanPocz

  def this() = {
    this(0)
  }

  def wplata(wartosc: Double) = {
    this.stanKonta = Math.floor((this.stanKonta + wartosc) * 100) / 100;
    this.stanKonta
  }

  def wyplata(wartosc: Double) = {
    this.stanKonta = Math.floor((this.stanKonta - wartosc) * 100) / 100;
    this.stanKonta
  }

  def getStanKonta: Double = {
    this.stanKonta
  }
}
