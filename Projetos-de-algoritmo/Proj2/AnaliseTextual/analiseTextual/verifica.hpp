#ifndef VERIFICA_H
#define VERIFICA_H

#include <cstdlib>
#include <string>
#include <list>
#include <regex>
#include <fstream>
#include <iostream>
#include <unordered_map>
#include <utility>
#include <vector>
#include "registros.hpp"
#include "termoVazio.hpp"
#include "mapaFreqPalavras.hpp"

class Verifica {
private:
	Registros registro;						// pega os caminhos da base de textos
	int maxPalavras;						// definição do maximo de palavras
	TermoVazio termo_irrelevante;			// verifica termos vazios
	mapaFreqPalavras mapaGeral;				// mapa de frequencia geral, com todas as palavras
	std::vector<mapaFreqPalavras> mapaIndividual;	// mapa de frequencia individual, agp para cada texto
	std::vector<std::pair<std::string, int>> ranking;

	mapaFreqPalavras processar(fs::path, mapaFreqPalavras);
	void analisar_cada_texto();
	void analisar_todos_textos();
	void escrever_linha(std::fstream&, mapaFreqPalavras);

public:
	Verifica(Registros registro, int maxPalavras) {
		this->registro = registro;
		this->maxPalavras = maxPalavras;
		analisar_cada_texto();
		analisar_todos_textos();
		this->ranking = this->mapaGeral.rankear();
	}

	void print(int i);
	void print_geral();
	void expDados();
	void insereTexto(fs::path);
};

// --------------------------- MÉTODOS PRIVADOS ---------------------------

inline mapaFreqPalavras Verifica::processar(fs::path caminho, mapaFreqPalavras dicionario) {

    std::ifstream arquivo;
    std::string s;

    arquivo.open(caminho);

	// para cada linha no arquivo
    while (std::getline(arquivo, s)) {
    	// aplica regex para quebrar palavras por espaços
		std::regex r(R"([a-zA-Z_]+(?:['_-][a-zA-Z_]+)*)");
		std::sregex_iterator it_re;

		// iterando sobre cada padrão da regex
		for (it_re = std::sregex_iterator(s.begin(), s.end(), r); it_re != std::sregex_iterator(); it_re++) {
  			std::smatch match;
			std::string palavra;

			// extrai o match da regex, cast para string e troca letras para minúsculas
			match = *it_re;
			palavra = match.str();
			std::for_each(palavra.begin(), palavra.end(), [](char &c) {
				c = ::tolower(c);
			});

			// se palavra não for stop word, insira no dicionário
			if (!termo_irrelevante.isTermo0(palavra))
				dicionario.inserir(palavra);
		}
    }
	arquivo.close();

	return dicionario;
}

inline void Verifica::analisar_cada_texto() {

	for (int i = 0; i < 30; i++) {
		mapaFreqPalavras d(this->registro.get_caminho(i));

		d = processar(this->registro.get_caminho(i), d);
		this->mapaIndividual.push_back(d);
    }
}

inline void Verifica::analisar_todos_textos() {

	for (int i = 0; i < 30; i++) {
        this->mapaGeral = processar(this->registro.get_caminho(i), this->mapaGeral);
    }
}

// --------------------------- MÉTODOS PÚBLICOS ---------------------------

inline void Verifica::print(int i) {
	for (auto i: this->mapaIndividual[i].get_mapa()) {
		std::cout << i.first << ": " << i.second << '\n';
	}
}

inline void Verifica::print_geral() {
	for (auto i: this->mapaGeral.get_mapa()) {
		std::cout << i.first << ": " << i.second << '\n';
	}
}

inline void Verifica::expDados() {
	std::fstream fpalavras, fcontagem;
	int n = 0;

	std::remove("../respostaTarefas/palavrasFrequentes_Geral.txt"); //palavras frequentes em todos os textos
	std::remove("../respostaTarefas/palavrasFrequentes_porTexto.csv"); //palavras frequentes em cada texto, estão agrupadas

	fpalavras.open("../respostaTarefas/palavrasFrequentes_Geral.txt",  std::ios::out | std::ios::app);
	fcontagem.open("../respostaTarefas/palavrasFrequentes_porTexto.csv", std::ios::out | std::ios::app);

	fcontagem << "ARQUIVO, ";
	std::vector<std::pair<std::string, int>>::const_iterator it;
	for (it = this->ranking.begin(); it != this->ranking.end(); it++) {
		fpalavras << it->first;
		fcontagem << it->first;
		if (n < this->maxPalavras) {
			fpalavras << '\n';
			fcontagem << ", ";
		}
		else break;
		n++;
	}
	fpalavras.close();
	fcontagem << '\n';

	for (int i = 0; i < 30; i++) {
		escrever_linha(fcontagem, this->mapaIndividual[i]);
	}

	fcontagem.close();
}

inline void Verifica::insereTexto(fs::path caminho) {
	mapaFreqPalavras d(caminho);
	std::fstream fcontagem;

	fcontagem.open("../respostaTarefas/palavrasFrequentes_porTexto.csv", std::ios::out | std::ios::app);

	d = processar(caminho, d);

	escrever_linha(fcontagem, d);

	fcontagem.close();
}

inline void Verifica::escrever_linha(std::fstream &fcontagem, mapaFreqPalavras d) {
	int n = 0;
	fcontagem << d.get_caminho().filename() << ", ";

	std::vector<std::pair<std::string, int>>::const_iterator it;
	for (it = this->ranking.begin(); it != this->ranking.end(); it++) {
		fcontagem << d.get_mapa()[it->first];
		if (n < this->maxPalavras)
			fcontagem << ", ";
		else break;
		n++;
	}
	fcontagem << '\n';
}

#endif
