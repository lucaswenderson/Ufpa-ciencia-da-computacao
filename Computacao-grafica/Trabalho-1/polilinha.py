from rasterizacao import Rasterizacao
from bresenham import Bresenham


class Polilinha(Rasterizacao):
  def __init__(self, pontos:list, fechar=False):
    super().__init__(pontos)

    if fechar:
      pontos.append(pontos[0])
    
    for x in range(len(pontos)-1):
      linha = Bresenham(pontos[x], pontos[x+1])
      
      for ponto in linha.saida:
        self.saida.append(ponto)
            
        
        