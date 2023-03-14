from rasterizacao import Rasterizacao
from grade import Grade


class Preenchimento(Rasterizacao):
  def __init__(self, ponto, cor, cor_borda, tela: Grade):
    super().__init__([ponto, cor, tela])
    self.tela = tela
    self.cor = cor
    self.cor_borda = cor_borda

    self.recursao(ponto)

  def recursao(self, ponto):
    ponto_atual = ponto
    print(ponto_atual)
    cor_atual = self.tela.checarMatriz(ponto_atual[0], ponto_atual[1])
    print(cor_atual)

    if cor_atual != self.cor and cor_atual != self.cor_borda:
      self.tela.DesenharPixel(ponto_atual[0], ponto_atual[1], self.cor)

      self.recursao((ponto_atual[0] + 1, ponto_atual[1]))
      self.recursao((ponto_atual[0], ponto_atual[1] + 1))
      self.recursao((ponto_atual[0] - 1, ponto_atual[1]))
      self.recursao((ponto_atual[0], ponto_atual[1] - 1))

