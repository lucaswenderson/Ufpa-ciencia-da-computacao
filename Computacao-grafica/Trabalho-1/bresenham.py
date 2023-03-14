from rasterizacao import Rasterizacao

class Bresenham(Rasterizacao):
  def __init__(self, ponto1, ponto2):
    super().__init__([ponto1, ponto2])
    
    self.xInicial = ponto1[0]
    self.yInicial = ponto1[1]    
    self.xFinal = ponto2[0]
    self.yFinal = ponto2[1]
    
    self.pontosFinais = []    
    self.trocaX = False
    self.trocaY = False
    self.trocaXY = False
    
    if ponto1 == ponto2:
      self.saida = [[self.xInicial, self.yInicial]]
      return
     
    self.octante()
    
    auxX = self.xInicial
    auxY = self.yInicial
    
    deltaX = self.xFinal - self.xInicial
    deltaY = self.yFinal - self.yInicial
    
    m = deltaY/deltaX
    erro = m - (1/2)
    
    self.pontosFinais.append([auxX, auxY])
    
    while auxX < self.xFinal:
      if erro >= 0:
        auxY += 1
        erro -= 1
      auxX += 1
      erro += m
      self.pontosFinais.append([auxX, auxY])
        
    self.reflexao(self.pontosFinais)    
    self.saida = self.pontosFinais
          
  def octante(self):      
    deltaX = self.xFinal - self.xInicial
    deltaY = self.yFinal - self.yInicial
    
    if deltaX != 0:
        m = deltaY/deltaX
    else:
        m = 2
    
    if m > 1 or m < -1:
      [self.xInicial, self.yInicial] = [self.yInicial, self.xInicial]
      [self.xFinal, self.yFinal] = [self.yFinal, self.xFinal]
      self.trocaXY = True
        
    if self.xInicial > self.xFinal:
      self.xInicial = -self.xInicial
      self.xFinal = -self.xFinal
      self.trocaX = True
        
    if self.yInicial > self.yFinal:
      self.yInicial = -self.yInicial
      self.yFinal = -self.yFinal
      self.trocaY = True    
  
  def reflexao(self, pts: list):
    if self.trocaY:
      for ponto in self.pontosFinais:
        ponto[1] = -ponto[1]
            
    if self.trocaX:
      for ponto in self.pontosFinais:
        ponto[0] = -ponto[0]
            
    if self.trocaXY:
      for ponto in self.pontosFinais:
        [ponto[0], ponto[1]] = [ponto[1], ponto[0]]
                
                