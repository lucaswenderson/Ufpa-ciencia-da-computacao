def sistema(num):
  expo = 0
  if(num < 0.1):
    while(num < 0.1):
      num = num * 10
      expo = expo + 1
    print(num,"* 10^-",expo)
  elif(num > 0):
    while(num > 1):
      num = num / 10
      expo = expo + 1
    print(num,"* 10^",expo)
  return num, expo

def arred(num):
  return round(num, 4)

def trunc(num):
  num = int(num * (10**4))/(10**4)
  return float(num)

def erroRelTrunc(valorOrig, expo):
  if(-3 <= expo <= 3):
    erro = (abs( ( valorOrig - trunc(valorOrig) ) / trunc(valorOrig) ) ) * (10**expo)
    return erro
  else:
    print("Valor do expoente ultapassou o limite do sistema")

def erroRelArred(valorOrig, expo):
  if(-3 <= expo <= 3):
    erro = (abs( ( valorOrig - arred(valorOrig) ) / arred(valorOrig) ) ) * (10**expo)
    return erro
  else:
    print("Valor do expoente ultapassou o limite do sistema")

def principal(num1, num2):
  num1Sist, expo1 = sistema(num1)
  num2Sist, expo2 = sistema(num2)
  print("O erro relativo de truncamento do numero",num1Sist,"* 10^",expo1,"é igual a:",erroRelTrunc(num1Sist, expo1))
  print("O erro relativo de arredondamento do numero",num1Sist,"* 10^",expo1,"é igual a:",erroRelArred(num1Sist, expo1))
  print(" ")
  print("O erro relativo de truncamento do numero",num2Sist,"* 10^",expo1,"é igual a:",erroRelTrunc(num2Sist, expo2))
  print("O erro relativo de arredondamento do numero",num2Sist,"* 10^",expo1,"é igual a:",erroRelArred(num2Sist, expo2))

num1 = float(input("Insira o numero 1: "))
num2 = float(input("Insira o numero 2: "))
principal(num1, num2)