from tkinter import *

import grade, bresenham, polilinha, curvas, circulo, transformacao,preenchimentoRecursivo, varredura, projecao, recorteLinha, recortePoligono

    
tela = grade.Grade(500)
roxo = '#bf00ff'
azul = '#0080ff'

'''
#EXEMPLOS:

#BRESENHAM

linha = bresenham.Bresenham((5, 4), (-7, 8))
tela.Desenhar(linha.saida, roxo)


#CIRCULO 

circulo = circulo.Circulo((-5, 5), 6)
tela.Desenhar(circulo.saida, roxo)


#CURVAS 

curva = curvas.Curvas(20, [(0, 0), (5, -5),(15,-5), (20, 20)])
tela.Desenhar(curva.saida, roxo)


#POLILINHA

pts = [(0, 8), (8, 0), (0, -8), (0, -16)]
linhas = polilinha.Polilinha(pts)
tela.Desenhar(linhas.saida, roxo)


#PREENCHIMENTO RECURSIVO

pts = [(0, 8), (8, 0), (0, -8)]
obj = polilinha.Polilinha(pts, fechar=True)
tela.Desenhar(obj.saida, roxo)
pr = preenchimentoRecursivo.Preenchimento((2,0), azul, roxo, tela)


#SCANLINE 

pts = [(-8, -8), (8,8), (-16, 16)]
obj = varredura.Varredura(pts)
tela.Desenhar(obj.saida, roxo)


#RECORTE DE LINHA

p1, p2 = (-8,-8),(16, 16)
xmin = -10
xmax = 4
ymin = -10
ymax = 4
obj = recorteLinha.RecorteLinha(p1, p2, xmin, xmax, ymin, ymax)
tela.destacarJanela(xmin, xmax, ymin, ymax)
tela.Desenhar(obj.saida, roxo)


#RECORTE DE POLIGONO

pts = [(0, 12), (12, 0), (0, -12)]
xmin = -1
xmax = 8
ymin = -8
ymax = 8
obj = recortePoligono.RecortePoligono(pts, xmin, xmax, ymin, ymax)
tela.destacarJanela(xmin, xmax, ymin, ymax)
tela.Desenhar(obj.saida, roxo)

poligonoOriginal = polilinha.Polilinha(pts, fechar=True)
tela.Desenhar(poligonoOriginal.saida, roxo)


# TRANSFORMAÇÃO 
'''
quadrado = [
  [0,0], [1,0], [2,0], [3,0],
  [3,1], [3,2], [3,3], [2,3],
  [1,3], [0,3], [0,2], [0,1]
]
obj = transformacao.Transformacao(entrada=quadrado)
#obj.translar(5,5)
#obj.escalar(6, 8)
obj.rotacionar([0, 0], 90)
tela.Desenhar(obj.saida, roxo)
'''

# PROJEÇÃO

cubo = [
    [0, 0, 0], [4, 0, 0], [4, 4, 0], [0, 4, 0],
    [0, 0, 4], [4, 0, 4], [4, 4, 4], [0, 4, 4]
]

obj = projecao.Projecao(entrada=cubo, recuo=-10)
obj.projetar()
#obj.perspectiva(dist=-20)
tela.Desenhar(obj.saida, roxo)

'''

mainloop()