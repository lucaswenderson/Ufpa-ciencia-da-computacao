#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <assert.h>
#include <ctype.h>

struct patriciaNode {
	char *prefixo; 
  char *flag;
	struct patriciaNode *nodo[26];
};

struct patriciaNode *raiz = NULL;

void inserir(char *palavra, struct patriciaNode **auxNode) {
	int i, j;

	if(*auxNode == NULL) {

		(*auxNode) = calloc(1, sizeof(struct patriciaNode));
		(*auxNode) -> prefixo = strdup(palavra);
		(*auxNode) -> flag = calloc(strlen(palavra) + 1, sizeof(char));
		(*auxNode) -> flag[strlen(palavra)] = 1;

	}

	for (i = 0; i < strlen((*auxNode) -> prefixo); i++)
		if (palavra[i] != (*auxNode) -> prefixo[i])
			break;

	if (i < strlen((*auxNode) -> prefixo)) {

		if (palavra[i] == '\0')
			(*auxNode) -> flag[i] = 1;
		else {

			struct patriciaNode *pai = calloc(1, sizeof(struct patriciaNode));

			pai -> prefixo = strndup((*auxNode) -> prefixo, i);
			pai -> flag = calloc(i + 1, sizeof(char));

			for (j = 0; j <= i; j++)
				pai -> flag[j] = (*auxNode) -> flag[j];

			inserir(palavra + i + 1, &pai -> nodo[palavra[i] - 'a']);

			pai -> nodo[(*auxNode) -> prefixo[i] - 'a'] = *auxNode;
			j = (strlen((*auxNode) -> prefixo) - i) * sizeof(char); 

			memmove((*auxNode) -> prefixo, (*auxNode) -> prefixo + i + 1, j);
			(*auxNode) -> prefixo = realloc((*auxNode) -> prefixo, j);

			memmove((*auxNode) -> flag, (*auxNode) -> flag + i + 1, j);
			(*auxNode) -> flag = realloc((*auxNode) -> flag, j);

			*auxNode = pai;
		}
	}
  else if (palavra[i] == '\0') {
		(*auxNode) -> flag[i] = 1;
	} 
  else {

		for (j = 0; j < 26; j++)
			if ((*auxNode)->nodo[j] != NULL)
				break;

		if (j < 26)
			inserir(palavra + i + 1, &(*auxNode)->nodo[palavra[i] - 'a']);
		else {
      
			(*auxNode) -> flag = realloc((*auxNode) -> flag, strlen(palavra) + 1);
			for (j = strlen((*auxNode) -> prefixo) + 1; j < strlen(palavra); j++)
				(*auxNode) -> flag[j] = 0;

			(*auxNode) -> flag[j] = 1;
			free((*auxNode) -> prefixo);

			(*auxNode) -> prefixo = strdup(palavra);
		}
	}
}

void imprimir(struct patriciaNode **auxNode, int m) {
  int i, count =0;
  char temp;

  if ((*auxNode) == NULL) {
    printf("[]");
    return;
  }

  int tamanhoPrefixo = strlen((*auxNode) -> prefixo);

  if (m == 1)
    printf("[");

  for(i = 0; i < tamanhoPrefixo; ++i) {
      printf("%c", ((*auxNode) -> prefixo[i]));
  }

  for (i = 0; i < 26; i++) {
    if ((*auxNode) -> nodo[i] != NULL) {
      printf("[");
      if ((*auxNode) -> nodo[i] -> flag[0] == 1) {
        printf("%c", i + 65); 
      }
      else if((strlen((*auxNode) -> nodo[i] -> prefixo) == 0 && (*auxNode) -> nodo[i] -> flag[0] == 1)) {
        printf("%c", i + 97);
      }
		  else {
        printf("%c", i + 97);
      }             
      imprimir(&((*auxNode) -> nodo[i]), 0);
    }
  }  
  printf("]");
}


int main(int argc , char *argv[]) {

	struct patriciaNode *nodeObj = NULL;
	char palavra[255];
  int cont;

  printf("------Atividade de Arvore Patricia------\n");
	
	while( cont != 0 ){ 

    printf("\nMenu - Insira a opção desejada:");
    printf("\n1 - Inserir: ");
    printf("\n2 - Imprimir: ");
    printf("\n0 - Encerrar: \n");
    printf("Insira a opção desejada: ");
    scanf("%d", &cont);
		
    switch(cont) {
      case 0:
        cont = 0;
        break;
      case 1:
        printf("Insira a palavra desejada: ");
        scanf("%s", palavra);
	 	    inserir(palavra, &nodeObj);
        printf("Palavra inserida com sucesso. \n");
        break;
      case 2:
        printf("\n");
	      imprimir( &nodeObj, 1);
        printf("\n");
        break;      
    }		
	}	
	return 0;
}
