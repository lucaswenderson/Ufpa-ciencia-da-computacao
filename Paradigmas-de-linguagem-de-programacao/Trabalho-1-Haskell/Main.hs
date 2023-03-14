{-

Trabalho 1 Haskell - Disciplina de Paradigmas de linguagem de programação
Periodo: 2021.4
Aluno: Lucas Wenderson - 201904940009 

-}

menor [x] = x
menor (x:xs) = if (x < menor xs)
               then x
               else menor xs

remover n (x:xs) = if (n == x)
                   then xs
                   else  [x] ++ (remover n xs) 

ordena [] = []
ordena ns = [menor ns] ++ ordena ys
            where
              ys = remover (menor ns) ns

fatorial 0 = 1
fatorial n = n * fatorial (n - 1)

inverter [] = []
inverter (x:xs) = inverter xs ++ [x]

inserir n 0 xs = n:xs   
inserir n k (x:xs) = [x] ++ (inserir n (k - 1) xs)

inserirfim n [ ] = [n]
inserirfim n (l:ls) = [l] ++ (inserirfim n ls)

insfim n [ ] = [n]
insfim n (l:ls) = l:ys 
                    where
                      ys = insfim n ls

potencia :: (Int,Float) -> Float
potencia (exp, base) =  
   if exp == 0 
   then 1.0 
   else base * potencia (exp-1,base)

main :: IO ()
main =  do
        putStrLn "Lista exemplo: [4,5,8,1,8,7,6,5]"
        putStr"Menor valor = "
        print(menor [4,5,8,1,8,7,6,5])
        putStr"Remove valor 4 = "
        print(remover 4 [4,5,8,1,8,7,6,5])
        putStr"Ordena a lista = "
        print(ordena [4,5,8,1,8,7,6,5])
        putStr"Fatorial de 6 = "
        print(fatorial 6)
        putStr"Inverte a lista = "
        print(inverter [4,5,8,1,8,7,6,5])
        putStr"Insere o valor 10 na posicao 4 = "
        print(inserir 10 4 [4,5,8,1,8,7,6,5])
        putStr"Insere valor 10 no fim = "
        print(inserirfim 10 [4,5,8,1,8,7,6,5])
        putStr"Insere valor 9 no fim (where) = "
        print(insfim 9 [4,5,8,1,8,7,6,5])
        print(potencia(2,4))
