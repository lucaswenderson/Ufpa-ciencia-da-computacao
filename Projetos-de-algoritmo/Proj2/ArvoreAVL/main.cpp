#include <stdio.h>
#include <stdlib.h>

bool aux;
const char antes[] = {"Antes de ajustar balanceamento...\n  "};
const char depois[] = {"Depois de ajustar balanceamento...\n  "};

class Node {
	public:
	int data;
	Node *esq;
	Node *dir;
	int altura;
};

Node *raiz;

Node* novoNode(int dado) {
	Node* node = new Node();
	node -> data = dado;
	node -> esq = NULL;
	node -> dir = NULL;
	node -> altura = 1; 
	return(node);
}

int altura(Node *auxNode) {
  if (auxNode == NULL)
  	return 0;
	else
    return auxNode -> altura;
}

int maior(int x, int y) {
  if  (x > y)
    return x;
  else
    return y;
}

int pegarBalanceamento(Node *auxNode) {
  if (auxNode == NULL)
    return 0;
  else
    return (altura(auxNode -> esq) - altura(auxNode -> dir));
}

Node *valorMinimoNode(Node* auxNode) {

	Node* atual = auxNode;
	while (atual -> esq != NULL)
		atual = atual -> esq;
	return atual;
}

Node *rotacaoEsq(Node *auxNode) {
  
  Node *aux1 = auxNode -> dir;
  Node *aux2 = aux1 -> esq;
  
  aux1 -> esq = auxNode;
  auxNode -> dir = aux2;
  
  auxNode -> altura = maior(altura(auxNode -> esq), altura(auxNode -> dir)) + 1;
  aux1 -> altura = maior(altura(aux1 -> esq), altura(aux1 -> dir)) + 1;

  return aux1;
} 

Node *rotacaoDir(Node *auxNode )  {

  Node *aux1 = auxNode -> esq;
  Node *aux2 = aux1 -> dir;

  aux1 -> dir = auxNode;
  auxNode -> esq = aux2;
    
  auxNode -> altura = maior(altura(auxNode -> esq), altura(auxNode -> dir)) + 1;
  aux1 -> altura = maior(altura(aux1 -> esq), altura(aux1 -> dir)) + 1;

  return aux1;
}

void imprimir(Node *auxNode) {

  printf(" ( %d ", auxNode -> data);

  if (auxNode -> esq != NULL)
    imprimir(auxNode -> esq);
  else {
    putchar_unlocked(' ');
    putchar_unlocked('(');
    putchar_unlocked(')');
    putchar_unlocked(' ');
  }

  if (auxNode -> dir != NULL)
    imprimir(auxNode -> dir);
  else {
    putchar_unlocked(' ');
    putchar_unlocked('(');
    putchar_unlocked(')');
    putchar_unlocked(' ');
  }

  putchar_unlocked(')');
  putchar_unlocked(' ');
}

bool percorrer(const char* str) {
  
  for(int i=0; str[i]; ++i)
    putchar_unlocked(str[i]);
  imprimir(raiz);
  putchar_unlocked('\n');
  return true;
}

Node* inserir(int dado, Node *auxNode) {
            
  if (auxNode == NULL) {
    return(novoNode(dado));
  }
  
  if (dado < auxNode -> data) {
    auxNode -> esq = inserir(dado, auxNode -> esq);
  }
  else if (dado > auxNode -> data) {
    auxNode->dir = inserir(dado, auxNode -> dir);
  }
  else {
    return auxNode;
  }
  
  auxNode -> altura = 1 + maior(altura(auxNode -> esq),altura(auxNode -> dir));
    
  int balanceamento = pegarBalanceamento(auxNode);

    
  if (balanceamento > 1 && dado < auxNode -> esq -> data) {
    aux = percorrer(antes);
    return rotacaoDir(auxNode);
  }
  
  if (balanceamento < -1 && dado > auxNode -> dir -> data) {
    aux = percorrer(antes);
    return rotacaoEsq(auxNode);
  }
  
  if (balanceamento > 1 && dado > auxNode -> esq -> data) {
    aux = percorrer(antes);
    auxNode -> esq = rotacaoEsq(auxNode -> esq);
    return rotacaoDir(auxNode);
  }
    
  if (balanceamento < -1 && dado < auxNode -> dir -> data) {
      aux = percorrer(antes);

      auxNode -> dir = rotacaoDir(auxNode -> dir);
      return rotacaoEsq(auxNode);
  }

  else {
    return auxNode;          
  }
}

Node* remover(int dado, Node* auxNode) {
	
	if (auxNode == NULL)
		return auxNode;

	if ( dado < auxNode -> data )
		auxNode -> esq = remover(dado, auxNode -> esq);

	else if( dado > auxNode -> data )
		auxNode -> dir = remover(dado, auxNode -> dir);

	else {
		
		if( (auxNode -> esq == NULL) ||	(auxNode -> dir == NULL) ) {
      
      Node *temporario = auxNode -> esq ? auxNode -> esq : auxNode -> dir;

			if (temporario == NULL)
			{
				temporario = auxNode;
				auxNode = NULL;
			}
			else 
			  *auxNode = *temporario; 
			free(temporario);
		}
		else {

			Node* temporario = valorMinimoNode(auxNode -> dir);

			auxNode -> data = temporario -> data;

			auxNode -> dir = remover(temporario -> data, auxNode -> dir);
		}
	}

	if (auxNode == NULL)
  	return auxNode;

	auxNode -> altura = 1 + maior(altura(auxNode -> esq), altura(auxNode -> dir));

	int balance = pegarBalanceamento(auxNode);

	if (balance > 1 && pegarBalanceamento(auxNode -> esq) >= 0) {
    aux = percorrer(antes);
		return rotacaoDir(auxNode);
  }

	if (balance > 1 && pegarBalanceamento(auxNode->esq) < 0) {
    aux = percorrer(antes);
    auxNode -> esq = rotacaoEsq(auxNode -> esq);
		return rotacaoDir(auxNode);
	}

	if (balance < -1 &&	pegarBalanceamento(auxNode -> dir) <= 0){
    aux = percorrer(antes);
		return rotacaoEsq(auxNode);
  }

	if (balance < -1 &&	pegarBalanceamento(auxNode -> dir) > 0) {
    aux = percorrer(antes);
		auxNode->dir = rotacaoDir(auxNode -> dir);
		return rotacaoEsq(auxNode);
	}
	return auxNode;
}

int main(){

  raiz = NULL;
  aux = false;
  int x, flag = 1, flag2 = 0;

  printf("------Atividade de Arvore AVL------\n");

  while( flag != 0) {
    
    printf("Menu - Insira a opção desejada:");
    printf("\n1 - Criar: ");
    printf("\n2 - Inserir: ");
    printf("\n3 - Remover: ");
    printf("\n4 - Imprimir: ");
    printf("\n0 - Encerrar: \n");
    printf("Insira a opção desejada: ");
    scanf("%d", &flag);
    switch(flag){

      case 0:
        flag = 0;
        break;
      case 1:
        if(flag2 > 0){
          printf("Arvore já existente, tente outra opção ou reinicie o programa.\n");
          break;
        }
        printf("\nInsira um valor raiz para criar a arvore: ");
        scanf("%d", &x);
        raiz = inserir(x,raiz);
        percorrer("Arvore gerada com sucesso...\n");
        aux = false;
        flag2 += 1;
        break;
      case 2:
        if(flag2 == 0){
          printf("Não existe arvore, gere uma primeiro\n");
          break;
        }
        printf("\nInsira um valor para o novo nodo: ");
        scanf("%d", &x);
        raiz = inserir(x,raiz);
        if(aux)
          percorrer(depois);
        else
          percorrer("Continuou AVL...\n  ");
        aux = false;
        break;
      case 3:
        if(flag2 == 0){
          printf("Não existe arvore, gere uma primeiro\n");
          break;
        }
        printf("\nInsira um valor para ser removido da arvore: ");
        scanf("%d", &x);
        raiz = remover(x,raiz);
        if(aux)
          percorrer(depois);
        else
          percorrer("Continuou AVL...\n  ");
        aux = false;
        break;
      case 4:
        if(flag2 == 0){
          printf("Não existe arvore, gere uma primeiro\n");
          break;
        }
        percorrer("Imprimindo Arvore...\n");
        aux = false;
        break;
    }
  }
  return 0;
}