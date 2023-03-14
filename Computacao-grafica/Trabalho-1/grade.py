from tkinter import *

class Grade:
  def __init__(self, tamanhoTela):
    self.tamanhoTela = tamanhoTela
    self.Matriz = []
    self.tamanhoMatriz = 50
    self.tamanhoPixel = int(self.tamanhoTela / self.tamanhoMatriz)

    for i in range(self.tamanhoMatriz):
        linha = []
        for j in range(self.tamanhoMatriz):
          linha.append(0)
        self.Matriz.append(linha)

    self.master = Tk()
    self.tela = Canvas(self.master,
                       width=self.tamanhoTela,
                       height=self.tamanhoTela)
    self.tela.pack()
    self.CriarTemplate()

  def CriarTemplate(self):
    aux = int(self.tamanhoTela / 2) + (self.tamanhoPixel / 2)

    for x in range(0, self.tamanhoTela, self.tamanhoPixel):
      self.tela.create_line(x, 0, x, self.tamanhoTela, fill='#DCDCDC')

    for y in range(0, self.tamanhoTela, self.tamanhoPixel):  
      self.tela.create_line(0, y, self.tamanhoTela, y, fill='#DCDCDC')

    self.tela.create_line(0,
                          aux - self.tamanhoPixel,
                          self.tamanhoTela,
                          aux - self.tamanhoPixel,
                          fill="#f00")  
    self.tela.create_line(aux, 0, aux, self.tamanhoTela,
                          fill="#f00") 

  def ConverterCoordenadas(self, x, y):
    real_x = int((self.tamanhoPixel * x) + (self.tamanhoTela / 2))
    real_y = int((self.tamanhoTela / 2) - (self.tamanhoPixel * y))

    return real_x, real_y

  def ConverterCoordenadasMatriz(self, x, y):
    coluna = int(x + (self.tamanhoMatriz / 2))
    linha = int((self.tamanhoMatriz / 2) - y) - 1

    return linha, coluna

  def DesenharPixel(self, x, y, cor):
    x1, y1 = self.ConverterCoordenadas(x, y)
    self.tela.create_rectangle(x1,
                               y1,
                               x1 + self.tamanhoPixel,
                               y1 - self.tamanhoPixel,
                               fill=cor)

    l, c = self.ConverterCoordenadasMatriz(x, y)
    self.Matriz[l][c] = cor

  def Desenhar(self, objeto: list, cor):
    for p in objeto:
      self.DesenharPixel(p[0], p[1], cor)

  def printMatriz(self):
    for linha in self.Matriz:
      print(linha)

  def checarMatriz(self, x, y):
    l, c = self.ConverterCoordenadasMatriz(x, y)
    return self.Matriz[l][c]

  def destacarJanela(self, xmin, xmax, ymin, ymax):
    xmin, ymin = self.ConverterCoordenadas(xmin, ymin)
    xmax, ymax = self.ConverterCoordenadas(xmax + 1, ymax + 1)
    self.tela.create_rectangle(xmin, ymin, xmax, ymax, outline='green')