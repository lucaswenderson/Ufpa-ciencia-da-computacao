import random

def cromossomosAleatorios(size): 
  return [ random.randint(1, numRainhas) for _ in range(numRainhas) ]

def aptidao(cromossomo):
  colisaoH = sum([cromossomo.count(rainha)-1 for rainha in cromossomo])/2
  colisaoD = 0

  n = len(cromossomo)
  diagonalE = [0] * 2*n
  diagonalD = [0] * 2*n
  for i in range(n):
    diagonalE[i + cromossomo[i] - 1] += 1
    diagonalD[len(cromossomo) - i + cromossomo[i] - 2] += 1

  colisaoD = 0
  for i in range(2*n-1):
    cont = 0
    if diagonalE[i] > 1:
      cont += diagonalE[i]-1
    if diagonalD[i] > 1:
      cont += diagonalD[i]-1
    colisaoD += cont / (n-abs(i-n+1))
  
  return int(aptidaoMax - (colisaoH + colisaoD)) # 28-(2+3)=23 ex de 8 rainhas

def probabilidade(cromossomo, aptidao):
  return aptidao(cromossomo) / aptidaoMax

def escolhaAleatoria(populacao, probabilidades):
  popEprob = zip(populacao, probabilidades)
  total = sum(w for c, w in popEprob)
  r = random.uniform(0, total)
  upto = 0
  for c, w in zip(populacao, probabilidades):
    if upto + w >= r:
      return c
    upto += w
  assert False, "Erro"
        
def cruzamento(x, y): 
  n = len(x)
  c = random.randint(0, n - 1)
  return x[0:c] + y[c:n]

def mutacao(x):  #muda randomicamente o valor do index de um cromossomo
  n = len(x)
  c = random.randint(0, n - 1)
  m = random.randint(1, n)
  x[c] = m
  return x

def novaPupulacao(populacao, aptidao):
  probabilidadeMutacao = 0.03
  newPop = []
  probabilidades = [probabilidade(n, aptidao) for n in populacao]
  for i in range(len(populacao)):
    x = escolhaAleatoria(populacao, probabilidades) #melhor cromossomo 1
    y = escolhaAleatoria(populacao, probabilidades) #melhor cromossomo 2
    filho = cruzamento(x, y) 
    if random.random() < probabilidadeMutacao:
      filho = mutacao(filho)
    imprimeEstado(filho)
    newPop.append(filho)
    if aptidao(filho) == aptidaoMax: break
  return newPop

def imprimeEstado(cromossomo):
  print("cromossomo = {},  aptidão = {}"
    .format(str(cromossomo), aptidao(cromossomo)))

if __name__ == "__main__":
    numRainhas = int(input("Numero de rainhas: ")) 
    aptidaoMax = (numRainhas*(numRainhas-1))/2  
    populacao = [cromossomosAleatorios(numRainhas) for _ in range(20)]
    flag = 0
    geracao = 1

    while not aptidaoMax in [aptidao(cromossomo) for cromossomo in populacao]:
      print("=== Geração {} ===".format(geracao))
      populacao = novaPupulacao(populacao, aptidao)
      print("")
      print("Aptidão Maxima = {}".format(max([aptidao(n) for n in populacao])))
      if geracao == 1000:
        flag = 1
        break
      geracao += 1
    if flag == 0 :
      chrom_out = []
      print("Resolvido na Geração {}!".format(geracao-1))
      for cromossomo in populacao:
        if aptidao(cromossomo) == aptidaoMax:
          print("");
          print("Uma das soluções: ")
          chrom_out = cromossomo
          imprimeEstado(cromossomo)

      tabuleiro = []

      for x in range(numRainhas):
        tabuleiro.append(["x"] * numRainhas)          
      for i in range(numRainhas):
        tabuleiro[numRainhas-chrom_out[i]][i]="Q"              

      def print_board(tabuleiro):
        for row in tabuleiro:
          print (" ".join(row))
                        
      print()
      print_board(tabuleiro)