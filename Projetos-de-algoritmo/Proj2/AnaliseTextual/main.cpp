#include <iostream>
#include "analiseTextual/verifica.hpp"
#include "analiseExecucao/rastreio.hpp"
#include "comprime/compac.hpp"


int main() {
	Registros registro("../auxiliares/base_textos/");  //pega os arquivos de texto
	Verifica verifica(registro,101); 		 //verifica as palavras comuns - 1ª tarefa
	verifica.expDados();					//cria relação com os registros - 2ª tarefa

	verifica.insereTexto("../auxiliares/outros_textos/outro_1.txt"); //testa outros textos  - 3ª Tarefa

	Compac compactador(registro); //Compacta os arquivos - 4ª Tarefa
	compactador.print(); 

	Rastreio rastreio(registro, compactador); // faz buscas no arquivo comprimido - 5ª Tarefa
	rastreio.rastreiaCompac("Lenses"); //faz as buscas em arquivos compactados
	rastreio.rastreiaSemCompac("Lenses"); //faz as buscas nos arquivos nao compactados
}
