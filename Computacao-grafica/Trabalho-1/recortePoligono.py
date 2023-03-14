from rasterizacao import Rasterizacao
from polilinha import *


class RecortePoligono(Rasterizacao):
  def __init__(self, pts_poligono: list, xmin, xmax, ymin, ymax):
    super().__init__([pts_poligono, xmin, xmax, ymin, ymax])
    self.xmin = xmin
    self.xmax = xmax
    self.ymin = ymin
    self.ymax = ymax

    esq = self.sutherland_hodgman_esq(pts_poligono)
    cima = self.sutherland_hodgman_cima(esq)
    dir = self.sutherland_hodgman_dir(cima)
    baixo = self.sutherland_hodgman_baixo(dir)

    novo_poligono_vertices = baixo

    poligono = Polilinha(novo_poligono_vertices, fechar=True)
    self.saida = poligono.saida

  def sutherland_hodgman_esq(self, pts):
    novo_poligono = []
    for i in range(len(pts)):
      p1 = pts[i]
      p2 = pts[(i+1) % len(pts)]

      x1, y1 = p1[0], p1[1]
      x2, y2 = p2[0], p2[1]

      if x1 >= self.xmin:
        if x2 >= self.xmin:
          novo_poligono.append(list(p2))
        else:
          novo_poligono.append([self.xmin, round(y1 + (y2 - y1) * (self.xmin - x1) / (x2 - x1))])
      else:
        if x2 >= self.xmin:
          novo_poligono.append([self.xmin, round(y1 + (y2 - y1) * (self.xmin - x1) / (x2 - x1))])
          novo_poligono.append(p2)
    return novo_poligono

  def sutherland_hodgman_dir(self, pts):
    novo_poligono = []
    for i in range(len(pts)):
      p1 = pts[i]
      p2 = pts[(i+1) % len(pts)]

      x1, y1 = p1[0], p1[1]
      x2, y2 = p2[0], p2[1]

      if x1 <= self.xmax:
        if x2 <= self.xmax:
          novo_poligono.append(list(p2))
        else:
          novo_poligono.append([self.xmax, round(y1 + (y2 - y1) * (self.xmax - x1) / (x2 - x1))])
      else:
        if x2 <= self.xmax:
          novo_poligono.append([self.xmax, round(y1 + (y2 - y1) * (self.xmax - x1) / (x2 - x1))])
          novo_poligono.append(p2)
    return novo_poligono


  def sutherland_hodgman_baixo(self, pts):
    novo_poligono = []

    for i in range(len(pts)):
      p1 = pts[i]
      p2 = pts[(i+1) % len(pts)]

      x1, y1 = p1[0], p1[1]
      x2, y2 = p2[0], p2[1]

      if y1 >= self.ymin:
        if y2 >= self.ymin:
          novo_poligono.append(list(p2))
        else:
          novo_poligono.append([round(x1 + (x2 - x1) * (self.ymin - y1) / (y2 - y1)), self.ymin])
      else:
        if y2 >= self.ymin:
          novo_poligono.append([round(x1 + (x2 - x1) * (self.ymin - y1) / (y2 - y1)), self.ymin])
          novo_poligono.append(p2)
    return novo_poligono

  def sutherland_hodgman_cima(self, pts):
    novo_poligono = []

    for i in range(len(pts)):
      p1 = pts[i]
      p2 = pts[(i+1) % len(pts)]

      x1, y1 = p1[0], p1[1]
      x2, y2 = p2[0], p2[1]

      if y1 <= self.ymax:
        if y2 <= self.ymax:
          novo_poligono.append(list(p2))
        else:
          novo_poligono.append([round(x1 + (x2 - x1) * (self.ymax - y1) / (y2 - y1)), self.ymax])
      else:
        if y2 <= self.ymax:
          novo_poligono.append([round(x1 + (x2 - x1) * (self.ymax - y1) / (y2 - y1)), self.ymax])
          novo_poligono.append(p2)

    return novo_poligono
