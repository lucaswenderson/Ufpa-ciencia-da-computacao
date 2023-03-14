#include <iostream> 
#include <limits.h> 
#include <string.h> 
#include <queue> 
using namespace std; 
  
// Número de vértices em determinado gráfico
#define V 6 
  
// Retorna verdadeiro se houver um caminho 
//da fonte 's' para afundar 't' gráfico residual. 
//Também preenche parent [] para armazenar o caminho*/
bool bfs(int rGraph[V][V], int s, int t, int parent[]){ 
    // Crie uma matriz visitada e marque 
	//todos os vértices como não visitados 
    bool visited[V]; 
    memset(visited, 0, sizeof(visited)); 
  
    // Crie uma fila, enfileire o vértice 
    // de origem e marque o vértice de origem
    // foi visitado 
    queue <int> q; 
    q.push(s); 
    visited[s] = true; 
    parent[s] = -1; 
  
    // Loop BFS padrão
    while (!q.empty()) 
    { 
        int u = q.front(); 
        q.pop(); 
  
        for (int v=0; v<V; v++) 
        { 
            if (visited[v]==false && rGraph[u][v] > 0) 
            { 
                q.push(v); 
                parent[v] = u; 
                visited[v] = true; 
            } 
        } 
    } 
  
    // Se alcançarmos o dissipador no BFS 
    //começando da origem, então retorne
    // verdadeiro, senão falso
    return (visited[t] == true); 
} 
  
// Retorna o fluxo máximo de s para t no gráfico fornecido 
int fordFulkerson(int graph[V][V], int s, int t) 
{ 
    int u, v; 
  
    // Crie um gráfico residual e 
    //preencha o gráfico residual com
    //capacidades dadas no gráfico original 
    //como capacidades residuais
    // no gráfico residual
    int rGraph[V][V]; // Gráfico residual onde rGraph [i] [j] indica
                     // capacidade residual da borda de i a j (se houver 
                     // é uma vantagem. Se rGraph [i] [j] é 0, então não há)  
    for (u = 0; u < V; u++) 
        for (v = 0; v < V; v++) 
             rGraph[u][v] = graph[u][v]; 
  
    int parent[V];  // Esta matriz é preenchida por BFS e para armazenar o caminho
  
    int max_flow = 0;  // Não há fluxo inicialmente
  
    // Aumente o fluxo enquanto há o caminho da fonte ao sumidouro
    while (bfs(rGraph, s, t, parent)) 
    { 
        // Encontre a capacidade residual mínima das bordas ao longo do
        // caminho preenchido pelo BFS. Ou podemos dizer encontre o fluxo máximo
        // através do caminho encontrado.
        int path_flow = INT_MAX; 
        for (v=t; v!=s; v=parent[v]) 
        { 
            u = parent[v]; 
            path_flow = min(path_flow, rGraph[u][v]); 
        } 

	// atualiza as capacidades residuais das arestas e arestas reversas
        // pelo caminho
        for (v=t; v != s; v=parent[v]) 
        { 
            u = parent[v]; 
            rGraph[u][v] -= path_flow; 
            rGraph[v][u] += path_flow; 
        } 
  
        // Adicione o fluxo do caminho ao fluxo geral
        max_flow += path_flow; 
    } 
  
    // Retorne o fluxo geral
    return max_flow; 
} 