from rasterizacao import Rasterizacao
import sys


class PontoCritico:
    def __init__(self, index, dir, x_interseccao, inv_slope):
        self.index = index
        self.dir = dir
        self.x_interseccao = x_interseccao
        self.inv_slope = inv_slope

    def __lt__(self, outro):
        return self.x_interseccao < outro.x_interseccao


class Varredura(Rasterizacao):
    def __init__(self, poligono):
        super().__init__(poligono)

        ymax = -sys.maxsize
        ymin = sys.maxsize

        pts_criticos = []

        for i in range(len(poligono)):
            if poligono[i][1] < ymin:
                ymin = poligono[i][1]
            if poligono[i][1] > ymax:
                ymax = poligono[i][1]

            ponto_aux = poligono[(i+1)%len(poligono)]

            if poligono[i][1] < ponto_aux[1]:
                c = PontoCritico(i, dir=1, x_interseccao=poligono[i][0],
                                 inv_slope=(ponto_aux[0] - poligono[i][0])/(ponto_aux[1] - poligono[i][1]))
                pts_criticos.append(c)

            ponto_aux = poligono[(i-1)]

            if poligono[i][1] < ponto_aux[1]:
                c = PontoCritico(i, dir=-1, x_interseccao=poligono[i][0],
                                 inv_slope=(ponto_aux[0] - poligono[i][0]) / (ponto_aux[1] - poligono[i][1]))
                pts_criticos.append(c)

        ativos = []

        for y in range(ymin, ymax+1):
            for ponto in ativos:
                ponto.x_interseccao += ponto.inv_slope

            for pt in pts_criticos:
                if poligono[pt.index][1] == y:
                    ativos.append(pt)

            for i in range(len(ativos)-1, -1, -1):
                c = ativos[i]
                p_max = poligono[(c.index + c.dir + len(poligono))%len(poligono)]
                if p_max[1] == y:
                    ativos.pop(i)

            ativos.sort()

            for i in range(0, len(ativos), 2):
                xmin = round(ativos[i].x_interseccao)
                xmax = round(ativos[i+1].x_interseccao)
                for x in range(xmin, xmax):
                    self.saida.append([x,y])


