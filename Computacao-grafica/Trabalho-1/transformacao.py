from rasterizacao import Rasterizacao
from polilinha import Polilinha
from math import sin, cos, radians
import numpy as np


class Transformacao(Rasterizacao):
    def __init__(self, entrada):
        super().__init__(entrada)
        self.saida = entrada

    def translar(self, trans_x, trans_y):
        self.saida = []
        for ponto in self.entrada:
            ponto[0] += trans_x
            ponto[1] += trans_y
            self.saida.append(ponto)

    def escalar(self, esc_x, esc_y):
        self.saida = []
        for ponto in self.entrada:
            ponto[0] *= esc_x
            ponto[1] *= esc_y
            self.saida.append(ponto)
        self.saida.append(self.saida[0])

        self.saida = Polilinha(self.saida).saida

    def rotacionar(self, pivo, angulo):
        self.saida = []
        trans_x = 0 - pivo[0]
        trans_y = 0 - pivo[1]
        translacao = Transformacao(self.entrada)
        translacao.translar(trans_x, trans_y)
        self.entrada = translacao.saida

        angulo_rad = radians(angulo)

        matriz_rotacao = [[cos(angulo_rad), -sin(angulo_rad)],                                     [sin(angulo_rad), cos(angulo_rad)]]

        for ponto in self.entrada:
            self.saida.append([round(x) for x in np.dot(matriz_rotacao, ponto)])

        translacao = Transformacao(self.saida)
        translacao.translar(-trans_x, -trans_y)
        self.saida = translacao.saida
