def verifica_primo(p):
  if p<=2:
    if p==2:
      return True
    return False
  for n in range(2,p):
    if p%n==0:
      return False
    return True

def form(n):
  return 2*n**2+11

n = int(input("digite um numero entre 0 e 100: ")) #intervalo entre 0 e 10
for i in range(0,n):
  print(form(i), "|", verifica_primo(form(i)))