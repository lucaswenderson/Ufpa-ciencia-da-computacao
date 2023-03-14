// Autor: Lucas Wenderson Silva De Souza - 201904940009
// Obs: caso deseje executar online segue o link: https://replit.com/@LucasWenderson/CaminhoMinimo#main.cpp

// Tarefa solicidada:
//    Achar a solução do menor curso - Concluido
//    Evitar os recálculos - Concluido
//    Mostrar o caminho de menor custo - Concluido

#include <iostream>
using namespace std;

#define M 7
#define N 7

//Função responsavel pela impressão do caminho minimo após processamento na função principal
void imprimeCaminho(int i, int j, pair<int,int> matrizCaminho[M][N], int matrizCusto[M][N]) {
  if (!i && !j) {
    cout <<"\n" << matrizCusto[i][j] << "("<< i <<" "<< j <<") " << endl;
    return;
  }
  imprimeCaminho(matrizCaminho[i][j].first, matrizCaminho[i][j].second, matrizCaminho, matrizCusto);
  cout << matrizCusto[i][j] << "("<< i <<" "<< j <<") " << endl;
}

// Função iterativa para encontrar o custo mínimo para atravessar a matriz a partir do primeiro elemento(0,0) até um elemento selecionado(xFinal,yFinal).
int custoMinimo(int matrizCusto[M][N], int xFinal, int yFinal) {
  
  xFinal += 1;
  yFinal += 1;

  //matrizTemp[i][j] armazena o custo mínimo para evitar o recalculo
  int matrizTemp[xFinal][yFinal];
  pair<int,int> matrizCaminho[M][N];

  //Prenche a matriz com uma estrategia bottom-up.
  for (int i = 0; i < xFinal; i++) {
    for (int j = 0; j < yFinal; j++) {
      
			matrizTemp[i][j] = matrizCusto[i][j];

      //Preencha a primeira linha
			if (i == 0 && j > 0) {
				matrizTemp[0][j] += matrizTemp[0][j - 1];
				matrizCaminho[0][j] = make_pair(0, j - 1);
			}

      // Preencha a primeira coluna
			else if (j == 0 && i > 0) {
				matrizTemp[i][0] += matrizTemp[i - 1][0];
				matrizCaminho[i][0] = make_pair(i - 1, 0);
			}

      //Preencha o resto com a matriz
			else if (i > 0 && j > 0) {
				if (matrizTemp[i - 1][j] < matrizTemp[i][j - 1]) {
					matrizTemp[i][j] += matrizTemp[i - 1][j];
					matrizCaminho[i][j] = make_pair(i - 1, j);
				} else {
					matrizTemp[i][j] += matrizTemp[i][j - 1];
					matrizCaminho[i][j] = make_pair(i, j - 1);
				}
			}
    }
  }
  
  imprimeCaminho(xFinal - 1, yFinal - 1, matrizCaminho, matrizCusto);
  cout << "Custo Minimo: ";
  
  //O último elemento da 'matrizTemp' armazena o custo mínimo para alcançar o elemento de destino
  return matrizTemp[xFinal - 1][yFinal - 1];
}

int main() {
  int matrizCusto[M][N] =
  {
    { 4, 7, 5, 0, 3, 8,13},
    { 3, 5, 2, 1, 6, 1, 4},
    { 1, 3, 9,11, 9, 2, 3},
    {17, 3, 8, 4, 1, 8,14},
    { 2, 0, 9, 5,11, 0, 4},
    { 6, 4, 0, 2, 1,10, 3},
    {7, 13, 2, 0, 7, 1, 2}
  };

  cout << "\nCaminho minimo: " << custoMinimo(matrizCusto,6,6) << endl;
  cout << "\nCaminho minimo: " << custoMinimo(matrizCusto,4,3) << endl;
  cout << "\nCaminho minimo: " << custoMinimo(matrizCusto,1,2) << endl;

  return 0;
}
