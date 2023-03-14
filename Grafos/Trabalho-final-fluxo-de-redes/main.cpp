/*****************************************************************************
trabalho final - Disciplina de Grafos
ano: 2021.4
Aluna: Hádria Farias - 201904940015 / Lucas Wenderson - 201904940009
******************************************************************************/

#include<bits/stdc++.h>
using namespace std; 

#define qtdV 7  // quantidade de de vértices

//checa se há um caminho entre s e t e preenche parent[] para guardar o caminho
bool bfs(int grafoResidual[qtdV][qtdV], int s, int t, int parent[]){

// marca todos os vértices como não visitados 
    bool visitado[qtdV]; 
    memset(visitado, 0, sizeof(visitado)); 
  
// Cria a fila, enfileira o vértice de origem e marca como visitado
    queue <int> fila; 
    fila.push(s); 
    visitado[s] = true; 
    parent[s] = -1; 
  
// Loop BFS padrão
    while (!fila.empty()) { 
        int u = fila.front(); 
        fila.pop(); 
  
        for (int v=0; v<qtdV; v++) { 
            if (visitado[v]==false && grafoResidual[u][v] > 0){ 
                fila.push(v); 
                parent[v] = u; 
                visitado[v] = true; 
            } 
        } 
    } 
  
//Se encontrarmos uma conexão com o nó coletor, retorna true
    return (visitado[t] == true); 
} 
  
// Retorna o fluxo máximo de s para t no gráfico fornecido 
int fordFulkerson(int grafo[qtdV][qtdV], int s, int t) { 
    int u, v; 
    int grafoResidual[qtdV][qtdV];  

    for (u = 0; u < qtdV; u++) 
        for (v = 0; v < qtdV; v++) 
             grafoResidual[u][v] = grafo[u][v]; 
  
    int parent[qtdV];  // armazena o caminho
  
    int flowMax = 0;  // Não há fluxo inicialmente
  
// Aumente o fluxo enquanto há o caminho da fonte ao sumidouro
    while (bfs(grafoResidual, s, t, parent)) {
        int caminhoFluxo = INT_MAX; 

        for (v=t; v!=s; v=parent[v]){ 
            u = parent[v]; 
            caminhoFluxo = min(caminhoFluxo, grafoResidual[u][v]); 
        } 

// att as capacidades residuais das arestas e arestas reversas pelo caminho
        for (v=t; v != s; v=parent[v]){ 
            u = parent[v]; 
            grafoResidual[u][v] -= caminhoFluxo; 
            grafoResidual[v][u] += caminhoFluxo; 
        } 
  
       // Adiciona o fluxo do caminho ao fluxo geral
        flowMax += caminhoFluxo; 
    } 
  
    // Retorna o fluxo geral
    return flowMax; 
} 

int main(){
  cout<<"------------------------------------------------------"<<endl;
  cout<<"TRABALHO FINAL - DISCIPLINA DE GRAFOS\nALUNOS: Hádria Farias e Lucas Wenderson"<<endl;
  cout<<"------------------------------------------------------"<<endl;

//criação do grafo para o exemplo    
    int grafo[qtdV][qtdV]
        = { { 0, 10, 22, 0, 5, 0, 13 }, 
            { 0, 4, 8, 0, 14, 0, 0 },  
            { 0, 0, 9, 0, 0, 22, 6 },
            { 0, 0, 0, 7, 0, 1, 0 }, 
            { 2, 0, 7, 12, 0, 0, 0 },  
            { 0, 0, 0, 0, 0, 0, 17 } 
          };
 
    cout << "FLUXO MÁXIMO POSSIVEL: "
//executa o fordFulkerson
         << fordFulkerson(grafo, 0, 6);

    return 0;
}