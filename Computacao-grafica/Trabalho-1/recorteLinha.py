from rasterizacao import Rasterizacao
from bresenham import *


class RecorteLinha(Rasterizacao):
  def __init__(self, p1, p2, xmin, xmax, ymin, ymax):
    super().__init__([p1, p2])
    self.xmin = xmin
    self.xmax = xmax
    self.ymin = ymin
    self.ymax = ymax

    self.cima = int('1000', 2)
    self.baixo = int('0100', 2)
    self.direita = int('0010', 2)
    self.esquerda = int('0001', 2)

    self.cohem_sutherland(list(p1), list(p2))

  def cohem_sutherland(self, p1, p2):
    c1 = self.binario(p1)
    c2 = self.binario(p2)

    while True:
      if c1 | c2 == 0:
        linha = Bresenham(p1, p2)
        self.saida = linha.saida
        break
      elif c1 & c2 != 0:
        break
      else:
        if c1 != 0:
          out = c1
        else:
          out = c2

        x1, y1 = p1[0], p1[1]
        x2, y2 = p2[0], p2[1]

        if out & self.cima:
          x = round(x1 + (x2 - x1) * (self.ymax - y1) / (y2 - y1))
          y = self.ymax
        elif out & self.baixo:
          x = round(x1 + (x2 - x1) * (self.ymin - y1) / (y2 - y1))
          y = self.ymin
        elif out & self.direita:
          y = round(y1 + (y2 - y1) * (self.xmax - x1) / (x2 - x1))
          x = self.xmax
        elif out & self.esquerda:
          y = round(y1 + (y2 - y1) * (self.xmin - x1) / (x2 - x1))
          x = self.xmin
        if out == c1:
          p1[0] = x
          p1[1] = y
          c1 = self.binario(p1)
        else:
          p2[0] = x
          p2[1] = y
          c2 = self.binario(p2)

  def binario(self, ponto):
    x = ponto[0]
    y = ponto[1]

    bit1 = self.sign(self.ymax - y)
    bit2 = self.sign(y - self.ymin)
    bit3 = self.sign(self.xmax - x)
    bit4 = self.sign(x - self.xmin)

    return int(str(bit1) + str(bit2) + str(bit3) + str(bit4), 2)

  def sign(self, num):
    if num < 0:
      return 1
    else:
      return 0
