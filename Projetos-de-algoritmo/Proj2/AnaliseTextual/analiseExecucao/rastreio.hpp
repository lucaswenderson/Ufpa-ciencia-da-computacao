#ifndef RASTREIO_H
#define RASTREIO_H

#include "../analiseTextual/registros.hpp"
#include "../comprime/compac.hpp"
#include "../comprime/huffman.hpp"
#include <fstream>

#define MAXCHAR         256
#define NUMMAXERROS     10

class Rastreio {
private:
	Compac comprimir;
	Registros registro;

	void shift_and_exato(std::string, std::string);
	void BMHS(std::string, std::string);
	int boyermoore2(std::string, std::string);
	std::string concatenar_textos();

public:
	Rastreio(Registros, Compac);
	std::string textos_concatenados;

	void rastreiaSemCompac(std::string);
	void rastreiaCompac(std::string);
};

inline Rastreio::Rastreio(Registros registro, Compac comprimir) {
	this->comprimir = comprimir;
	this->registro = registro;
	// this->textos_concatenados = concatenar_textos();
}

inline std::string Rastreio::concatenar_textos() {
	std::ifstream arquivo;
	std::string geral;

	for (int i = 0; i < this->registro.tamanho(); i++) {
		std::string s;

		arquivo.open(this->registro.get_caminho(i));

		while (std::getline(arquivo, s)) {
			geral += s;
		}

		arquivo.close();
	}
	return geral;
}

inline void Rastreio::rastreiaSemCompac(std::string chave) {
	
	std::fstream arquivo;
	std::string s, r;

	arquivo.open("../auxiliares/texto1.txt");

	while (std::getline(arquivo, s)) {
		r += s;
	}

	arquivo.close();
	
	BMHS(r, chave);
}

inline void Rastreio::rastreiaCompac(std::string chave) {
	std::string binario_chave;

	for (int i = 0; i < chave.size(); i++) {
		binario_chave += this->comprimir.huffman.codigos[chave[i]];
	}

	BMHS(this->comprimir.binario, binario_chave);
}

inline void Rastreio::BMHS(std::string T, std::string P) {
	long n = T.size();
	long m = P.size();

	long i, j, k, d[MAXCHAR + 1];
	for (j = 0; j <= MAXCHAR; j++) d[j] = m + 1;
	for (j = 1; j <= m; j++) d[P[j-1]] = m - j + 1;
	i = m;
	while (i <= n) {
		k = i; 
		j = m;
		while (T[k-1] == P[j-1] && j > 0) { k--; j--; }
			if (j == 0)
				printf(" Casamento na posicao: %3ld\n", k + 1);
			i += d[T[i]];
    }
}

typedef unsigned char byte;

// Recebe uma palavra a[1..m] com 1 <= m e
// um texto b[1..n]. Devolve o número de
// ocorrências de a em b.

inline int Rastreio::boyermoore2 (std::string a, std::string b) {

	int m = a.size();
	int n = b.size();
  int *jump = (int*) malloc ((m+1) * sizeof (int));
   // usaremos jump[1..m]

   // pré-processamento da palavra a
   int h = m, k = m-1;
   while (h >= 1 && k >= 1) {
      int i = m, j = k; 
      while (i >= h && j >= 1)
         if (a[i] == a[j]) --i, --j;
         else i = m, j = --k;
      jump[h--] = k;
   }
   while (h >= 1)
      jump[h--] = k;

   // busca da palavra a no texto b
   int ocorrs = 0;
   k = m;
   while (k <= n) {
      int i = m, j = k;
      while (i >= 1 && a[i] == b[j]) 
         --i, --j;   
      if (i < 1) ++ocorrs;
      if (i == m) k += 1;
      else k += m - jump[i+1];
   }
   return ocorrs;
}  
 

#endif