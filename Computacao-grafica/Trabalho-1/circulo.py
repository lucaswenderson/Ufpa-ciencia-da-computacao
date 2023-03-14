from rasterizacao import Rasterizacao

class Circulo(Rasterizacao):
  def __init__(self, centro, raio):
    super().__init__([centro, raio])

    self.centro = centro
    self.raio = round(raio)

    self.ponto_medio()

  def ponto_medio(self):
    x = 0
    y = self.raio
    erro = -self.raio
    self.printOctantes(x, y)

    while x <= y:
      erro = self.proximoErro(x, y, erro)
      x += 1
      if erro >= 0:
        erro = self.erroFinal(x, y, erro)
        y -= 1
      self.printOctantes(x, y)

  def proximoErro(self, x, y, erro):
    return erro + 2*x + 1

  def erroFinal(self, x, y, erro):
    return erro - 2*y + 2

  def printOctantes(self, x, y):
    self.saida.append([x + self.centro[0], y + self.centro[1]])
    self.saida.append([y + self.centro[0], x + self.centro[1]])
    self.saida.append([y + self.centro[0], -x + self.centro[1]])
    self.saida.append([x + self.centro[0], -y + self.centro[1]])
    self.saida.append([-x + self.centro[0], -y + self.centro[1]])
    self.saida.append([-y + self.centro[0], -x + self.centro[1]])
    self.saida.append([-y + self.centro[0], x + self.centro[1]])
    self.saida.append([-x + self.centro[0], y + self.centro[1]])




