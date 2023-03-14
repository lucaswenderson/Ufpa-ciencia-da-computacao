#include<bits/stdc++.h>
using namespace std;

class Hash {
  public:
    int tamanho;
    list<int> *lista;
    
    Hash(int tamanho) {
      this -> tamanho = tamanho;
      lista = new list<int>[tamanho];
    }

    int funcaoHash(int valor) {
      return (valor % tamanho);
    }

    void inserir(int valor) {
      int index = funcaoHash(valor);      

      lista[index].push_back(valor);

      if(lista[index].size() > 1) {
        cout << "Houve colisão na inserção do elemento: " << valor << endl; 
      }
    }

    void remover(int valor) {
      int index = funcaoHash(valor);
      list <int> :: iterator i;

      for (i = lista[index].begin(); i != lista[index].end(); i++) {
        if (*i == valor)
        break;
      }

      if (i != lista[index].end())
        lista[index].erase(i);
    }

    void imprimir() {
      for (int i = 0; i < tamanho; i++) {
        cout << i;
        for (auto x : lista[i])
        cout << " --> " << x;
        cout << endl;
      }
    }
};


int main() {
  int flag = 1, tamanho, valor;

  cout << "------Atividade de Tabela Hash------\n";
  cout << "Insira o tamanho da tabela hash: ";
  cin >> tamanho;
  Hash tabelaHash(tamanho);
  cout << "Tabela criada com sucesso. \n";
  tabelaHash.imprimir();
	
	while( flag != 0 ) { 

    cout << "\nMenu - Insira a opção desejada:";
    cout << "\n1 - Inserir: ";
    cout << "\n2 - Remover: ";
    cout << "\n3 - Imprimir: ";
    cout << "\n0 - Encerrar: \n";
    cout << "Insira a opção desejada: ";
    cin >> flag;
		
    switch(flag) {
      case 0: {
        flag = 0;
        break;
      }
      case 1: {
        cout << "Insira o valor que deseja inserir: ";
        cin >> valor;
        tabelaHash.inserir(valor);
        tabelaHash.imprimir();
        break;
      }
      case 2: {
        cout << "Insira o valor que deseja remover: ";
        cin >> valor;
        tabelaHash.remover(valor);
        tabelaHash.imprimir();
        break;
      }
      case 3: {
        tabelaHash.imprimir();
        break;
      }
    }		
	}	
  return 0;
}
