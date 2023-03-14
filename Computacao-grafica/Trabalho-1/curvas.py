from rasterizacao import Rasterizacao
from bresenham import Bresenham

class Curvas(Rasterizacao):
  def __init__(self, nPontos, pontosControle: list):
    super().__init__([pontosControle])

    self.pontos = []
    passo = 1 / nPontos  
    t = 0.0

    for d in range(nPontos + 1):
      self.pontos.append(self.casteljau(t, pontosControle))
      t += passo

    self.juntar_pontos()

  def casteljau(self, t, pontos_controle):
      pts = []
      for p in pontos_controle:
          pts.append(list(p))

      for i in range(1, len(pts)):
          for j in range(len(pts)-i):
              pts[j][0] = (1 - t) * pts[j][0] + t * pts[j+1][0]
              pts[j][1] = (1 - t) * pts[j][1] + t * pts[j + 1][1]

      return [int(pts[0][0]), int(pts[0][1])]

  def juntar_pontos(self):
      for x in range(len(self.pontos) - 1):
          linha = Bresenham(self.pontos[x], self.pontos[x + 1])

          for pt in linha.saida:
              self.saida.append(pt)
