from tkinter import *

tamanhoTela = 600
tamanhoPixel = int(tamanhoTela / 50)

master = Tk()
tela = Canvas(master, width=tamanhoTela, height= tamanhoTela)
tela.pack()


def CriarTemplate():   
  aux = int(tamanhoTela / 2)

  for x in range(0, tamanhoTela, tamanhoPixel):
    tela.create_line(x, 0, x, tamanhoTela, fill="#808080")

  for y in range(0, tamanhoTela,tamanhoPixel):
    tela.create_line(0, y, tamanhoTela, y, fill="#808080")

  # linhas centrais 0, 0 
  tela.create_line(0, aux - tamanhoPixel, tamanhoTela, aux - tamanhoPixel, fill="#f00") # horizontal
  tela.create_line(aux, 0, aux, tamanhoTela, fill="#f00") #vertical

CriarTemplate()