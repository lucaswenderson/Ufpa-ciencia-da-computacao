{-

Trabalho Final - Disciplina de Paradigmas de linguagem de programação
Periodo: 2021.4
Aluno: Lucas Wenderson - 201904940009 

-}

-- 1) Aplicação parcial de funções. Considere as duas versões abaixo da função "potencia"/"pot":

potencia :: (Int,Float) -> Float
potencia (exp, base) =  
   if exp == 0 
   then 1.0 
   else base * potencia (exp-1,base)


pot :: Int -> Float -> Float
pot exp base =  
   if exp == 0 
   then 1.0 
   else base * pot (exp-1) base

-- Explique a diferença entre as duas DECLARAÇÕES DE TIPO das funções. Qual a consequência dessas definições em termos de seu uso?

{-

pot :: Int -> Float -> Floa
Nesse caso temos a declaração por aplicação parcial de funções na qual uma função com múltiplos argumentos pode também ser considerada como uma função que retorna outra função como resultado. Basicamente é uma função que recebe um argumento e resulta em outra função que, por sua vez, recebe outro argumento

potencia :: (Int,Float) -> Float
Nesse caso temos a declaração por Funções curried, sendo outra opção para passar vários argumentos em uma aplicação de função ao formar uma estrutura de dados com os dados desejados e passar a estrutura como argumento. Neste caso fica claro que haverá um único argumento, que é a estrutura de dados

Funções curried são mais flexíveis do que as funções com tuplas, porque muitas vezes funções úteis podem ser obtidas pela aplicação parcial de uma função curried.

-}

-- Explique o significado, e forma de uso, de: 
pot5 = pot 5
-- é possível fazer o mesmo com a função "potencia" ?

{-
Basicamente temos uma função que elevara qualquer valor inserido por 5 seguindo a logica da aplicação parcial de funções seguinte

pot5 = pot 5 
a = pot5(2)
a = 32

O mesmo não seria possivel na função "potencia" pois ela é uma função de tipo curried.
-}

-- Crie um exemplo de aplicação parcial de função envolvendo uma função aplicFuncLst, que receba como parâmetros uma função f e uma lista, e aplique f a cada elemento da lista. Na sequência, crie  um exemplo de aplicação parcial

{-
f é uma função que recebe um argumento e resulta em outra função que, por sua vez, recebe outro argumento e resulta na soma do dobro do primeiro argumento com o segundo argumento.
-}

f :: Int -> Int -> Int
f x y = 2*x + y

aplicFuncLst :: (a -> b) -> [a] -> [b]
aplicFuncLst _[] = []
aplicFuncLst a (x:xs) = a x : aplicFuncLst a xs

-- 2) Desenvolva uma função que calcule o valor do enésimo elemento da sequência de Fibonacci

-- a) usando if
fib2 :: Int -> Int
fib2 n = 
   if n <= 1 
   then n 
   else fib2(n-1) + fib2(n-2)

-- b) usando pattern matching
fib1 :: Int -> Int
fib1 0 = 0
fib1 1 = 1
fib1 n = fib1(n-1) + fib1(n-2)

-- c) usando lista infinita
fibAux :: Num n => [n]
fibAux = 0 : nxt
    where nxt = 1 : zipWith (+) fibAux nxt

fib3 :: Num n => Int -> [n]
fib3 = flip take fibAux


-- 3) Considere as funções abaixo apresentadas em aula

-- função que testa se x eh primo
ehPrimo :: Int -> Bool -- Aplicação Parcial
ehPrimo x = null [x' | x' <- [2..x-1], x `rem` x' == 0]

-- função que cria uma lista "infinita" com valores não menores que n
from :: Int -> [Int] -- Aplicação Parcial
from n = n : from (n+1)

-- função que acha o primeiro primo na lista
primPrimo :: [Int] -> Int -- Aplicação Parcial
primPrimo (n : ns) =
    if ehPrimo n then n else primPrimo ns

-- a) criar uma lista infinita de pares a partir de certo valor n (par):

listaPar :: Int -> [Int] -- Aplicação Parcial
listaPar n = [x | x <- [n..], x `mod` 2 == 0]

-- b) criar a função que forneça o primeiro valor dessa lista que seja múltiplo de 25. OBS: especificar os tipos das funções

parMultiplo :: [Int] -> Int -- Aplicação Parcial
parMultiplo (n : ns) =
    if n `mod` 25 == 0
    then n
    else parMultiplo ns

-- Cite (usando um comentário no próprio código) o conceito de Haskell que permite a criação de uma lista infinita sem produzir um ciclo infinito de chamadas. Explique esse conceito   
{-
Haskell usa um sistema de lazy evaluation que permite definir quantos termos você quiser, sabendo que o compilador só alocará aqueles que você usar em uma expressão. A lazy evaluation é um mecanismo de execução de código que adia a avaliação de uma expressão até que seu valor seja necessário. Isso é útil quando o processo de avaliação de uma expressão se torna caro ou impossível. Para que a lazy evaluation funcione, os dados necessários para um cálculo devem ser acessíveis e significativos no momento da avaliação. 
-}

-- 4) Considere a função qsort abaixo, que usa como pivô a cabeça da lista (sugestão: teste a mesma no GHC)

qsort :: [Int] -> [Int]
qsort [] = []
qsort (n : ns) =
   qsort [i | i <- ns, i < n]
   ++ [n]
   ++ qsort [i | i <- ns, i >= n]


-- Abaixo exploramos o conceito de função genérica ao invés de usarmos o operador "<" usamos uma função genérica precede  

genericSort :: (t -> t -> Bool) -> [t] -> [t]
genericSort precede =
   let
      sort [] = []
      sort (n : ns) =
         sort [i | i <- ns, i `precede` n]
            ++ [n]
            ++ sort [i | i <- ns, not (i `precede` n)]
   in sort

-- assim, para produzirmos uma função para ordenar inteiros criamos uma instância da função genérica. Nesse caso, o operador "<" tomará o lugar do "precede"

ordenaInt :: [Int] -> [Int]
ordenaInt = genericSort (<)

-- Funciona?

-- Sim, funciona.

-- 4.a)Crie outra instância de genericSort, ordenaRac para ordenar uma lista de números racionais, isto é, aqueles que são escritos em forma de fração. Por exemplo, a partir da lista:

-- [ [1,2], [1,3], [1,5], [9,10], [1,10] ]

-- Será retornada a lista ordenada abaixo (de formna ascendente)

-- [[1,10],[1,5],[1,3],[1,2],[9,10]]

-- Defina a função "menorRac", que define se o primeiro precede (é menor que) o segundo 


menorRac :: [Rational] -> [Rational] -> Bool
menorRac [a,b] [c,d] = (a / b) < (c / d)


-- agora criar a função "ordenaRac" como instância de "genericSort"

ordenaRac :: [ [Rational] ] -> [ [Rational] ]
ordenaRac = genericSort (menorRac)

-- 4.b) Agora considere uma lista que guarde as seguintes informações de n alunos: matrícula (Int), nome (String) e CRG (Float). Ex:

--  [[3,"Leôncio", 9.3],[9,"Paulina", 9.8],[4, "Bernardo", 8.9] ...]

-- Defina a função "precedeAl", que define qual aluno vem antes de acordo com sua matrícula (ord. ascendente). Defina a função ordenaAl como instância de "genericSort" para produzir a lista ordenada pela matrícula

precedeAl :: (Int,String,Float) -> (Int,String,Float) -> Bool
precedeAl (a,b,c) (d,e,f) = a < d

ordenaAl :: [ (Int,String,Float) ] -> [ (Int,String,Float) ]
ordenaAl = genericSort (precedeAl)

-- Defina a função "precedeAl2", que define qual aluno vem antes de acordo com seu CRG (ord. ascendente). Crie a função ordenaAl2 como instância de "genericSort" para produzir a lista ordenada pelo CRG

precedeAl2 :: (Int,String,Float) -> (Int,String,Float) -> Bool
precedeAl2 (a,b,c) (d,e,f) = c < f

ordenaAl2 :: [ (Int,String,Float) ] -> [ (Int,String,Float) ]
ordenaAl2 = genericSort (precedeAl2)

main :: IO ()
main =  do
        putStrLn "Lista exemplo: [4,5,8,1,8,7,6,5]"
        putStr "aplicFuncLst: "
        print(aplicFuncLst (f 2) [4,5,8,1,8,7,6,5])
        putStr "Fibonacci(IF): "
        print(fib2 11)
        putStr "Fibonacci(PM): "
        print(fib2 10)
        putStr "Fibonacci(LI): "
        print(fib3 30)
        putStr "Primeiro Primo após 10: "
        print(primPrimo (from 10))
        putStr "Primeiro Multiplo de 25 após 101: "
        print(parMultiplo (listaPar 101))
        print((3,"Leôncio", 9.3) `precedeAl` (9,"Paulina", 9.8) )
        putStr "Ordenação por matrícula: "
        print(ordenaAl [(3,"Leoncio", 9.3),(9,"Paulina", 9.8),(4, "Bernardo", 8.9)] )
        print((3,"Leôncio", 9.3) `precedeAl2` (9,"Paulina", 9.8) )
        putStr "Ordenação por CRG: "
        print(ordenaAl2 [(3,"Leoncio", 9.3),(9,"Paulina", 9.8),(4, "Bernardo", 8.9)] )
        print([1,2] `menorRac` [1,3] )
        print(ordenaRac [ [1,2], [1,3], [1,5], [9,10], [1,10] ] )
        