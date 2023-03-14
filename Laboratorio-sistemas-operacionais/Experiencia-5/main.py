rrtempo = []
rrnome = []
nome = []
pnome = []
ptempo = []
tempo = []
prioridade = []
cont = 0
cont2 = 0
copiatempo = []
copianome = []
ftempo = []
fnome = []

timeslice = int(input("Time Slice: "))
quant = int(input("Processos a serem inseridos: ")) 

for i in range (quant):
  lnome, ltempo, lprioridade = input(" Digite o nome do processo, tempo de CPU e prioridade: \n").split(",")
  nome.append(lnome)
  tempo.append(int(ltempo))
  prioridade.append(int(lprioridade))

copiatempo = tempo.copy()
copianome = nome.copy()

print("CPU Fila unica: ")
cont=0

for i in range (0,10): #Escalonador FIFO
  for j in range (len(prioridade)): 
    if i == prioridade[j]:
      for h in range (len(prioridade)):
        if j == h:
          ftempo.append(tempo[h])
          fnome.append(nome[h])

while True:
  i = 0
  cont += 1
  cont2 += 1 #processo passa pela CPU
  ftempo[i] -= timeslice
  for i in range(len(fnome)):
    if i == 0:
      nguarda = fnome[i]
      tguarda = ftempo[i]
    else:
      fnome[i-1] = fnome[i]
      ftempo[i-1] = ftempo[i]
  fnome[len(fnome)-1] = nguarda
  ftempo[len(ftempo)-1] = tguarda

  if (ftempo[i] > 0) and (ftempo[i] != 10): #printar os processoS
    print("{}: {}".format(fnome[i], timeslice), end = ' ')
  elif (ftempo[i] < 0) or (ftempo[i] == 10):
    if ftempo[i] < 0:
      ts = ftempo[i] + timeslice
      print("{}: {}*".format(fnome[i], ts), end=' ')
    else:
      print("{}: {}*".format(fnome[i], timeslice), end=' ')
    del(ftempo[i])
    del(fnome[i])
    cont2 -= 1
  else:
    del(ftempo[i])
    del(fnome[i])
    cont -= 1
  if len(ftempo) == cont2:
    print(" ")
    cont2 = 0
  if len(ftempo) == 0:
    break

print("Processos finalizados")
print("\n---------------------\n")

print("CPU Round Robin: ") #Escalonador Round Robin
for i in range(0,10):
  for j in range(len(prioridade)):
    if i == prioridade[j]:
      for h in range(len(prioridade)):
        if j == h:
          rrtempo.append(copiatempo[h])
          rrnome.append(copianome[h])
  cont = 0
  cont2 = 0
  if len(rrtempo) > 0:
    print("Fila de prioridade {}:".format(i))
  while len(rrtempo) > 0:
    i=0
    cont3 = len(rrtempo)
    cont += 1
    cont2 += 1
    
    rrtempo[i] -= timeslice #processo passa pela CPU
    for i in range(len(rrtempo)):
      if i == 0:
        nguarda = rrnome[i]
        tguarda = rrtempo[i]
      else:
        rrnome[i-1] = rrnome[i]
        rrtempo[i-1] = rrtempo[i]
    rrnome[len(rrnome)-1] = nguarda
    rrtempo[len(rrtempo)-1] = tguarda

    if (rrtempo[i] > 0) and (rrtempo[i] != 10): #printar os processos
      print("{}: {}".format(rrnome[i], timeslice), end = ' ')
    elif (rrtempo[i] < 0) or (rrtempo[i] == 10):
      if rrtempo[i] < 0:
        ts = rrtempo[i] + timeslice
        print("{}: {}*".format(rrnome[i], ts), end=' ')
      else:
        print("{}: {}*".format(rrnome[i], timeslice), end=' ')
      del(rrtempo[i])
      del(rrnome[i])
      cont2 -= 1
    else:
      del(rrtempo[i])
      del(nome[i])
      cont2 -= 1
    if len(rrtempo) == cont2:
      print(" ")
      cont2 = 0
print("Fim")