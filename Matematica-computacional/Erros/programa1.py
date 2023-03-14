import numpy as np

#Exemplo 1:
def presicaoMaquina(func = float):
  epsilon = func(1)
  while func(1)+func(epsilon) != func(1):
    epsilonUltimo = epsilon
    epsilon = func(epsilon) / func(2)
  return epsilonUltimo
 
print ('Precisão Dupla -->', presicaoMaquina(float))
print ('Precisão Simples -->', presicaoMaquina(np.float32))
print ('')

#Exemplo 2:
print ('Precisão Dupla, 1.0 -->', np.spacing(1.0))
print ('Precisão Dupla, 1e-12 -->', np.spacing(1e-12))
print ('')
print ('Precisão Simples, 1.0 -->', np.spacing(np.float32(1.0)))
print ('Precisão Simples, 1e-12 -->', np.spacing(np.float32(1e-12)))

#Exemplo 3:
print("\nExemplo para qualquer valor")
while(True):
  x = float(input("Insira o valor: "))
  print ('Precisão Dupla, ',x,' -->', np.spacing(x))
  print ('Precisão Simples, ',x,' -->', np.spacing(np.float32(x)),"\n")
