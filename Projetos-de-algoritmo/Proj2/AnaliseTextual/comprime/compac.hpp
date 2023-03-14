#ifndef COMPAC_H
#define COMPAC_H

#include "../analiseTextual/registros.hpp"
#include "huffman.hpp"
#include <vector>
#include <sstream>
#include <bitset>

class Compac {
private:
	Registros registro;
	std::vector<unsigned char> chars;
	std::unordered_map<unsigned char, unsigned int> mapa_freq;

	void preprocessar();
	void comprime();

public:
	Huffman huffman;
	std::string binario;
	Compac() {}
	Compac(Registros);
	void print();
};

inline Compac::Compac(Registros registro) {
	this->registro = registro;
	preprocessar();

	this->huffman = Huffman(this->mapa_freq);
	comprime();
}

inline void Compac::preprocessar() {

	for (int i = 0; i < this->registro.tamanho(); i++) {
		std::fstream arquivo;
		std::string s;

		arquivo.open(this->registro.get_caminho(i));

		while (std::getline(arquivo, s)) {
			std::unordered_map<unsigned char, unsigned int>::const_iterator it;

			for (int i = 0; i < s.size(); i++){

				this->chars.push_back(s[i]);

				it = this->mapa_freq.find(s[i]);
				if (it == this->mapa_freq.end())
					this->mapa_freq.insert(std::make_pair(s[i], 1));
				else
					this->mapa_freq[s[i]]++;
			}
		}
		arquivo.close();
	}
}


inline void Compac::comprime() {
	std::fstream arquivo;

	std::stringstream sstream;

	for (int i = 0; i < this->chars.size(); i++) {
		this->binario += this->huffman.codigos[chars[i]];
	}

	sstream = std::stringstream(this->binario);

	std::remove("../respostaTarefas/base_textos.cpt");

	arquivo.open("../respostaTarefas/base_textos.cpt", std::ios::out | std::ios::app);

	while(sstream.good()) {
		std::bitset<8> bits;
		unsigned char c;

		sstream >> bits;
		c = char(bits.to_ulong());

		arquivo << c;
	}

	arquivo.close();
}

inline void Compac::print() {
	for (auto i : this->huffman.codigos) {
		std::cout << i.first << " : " << i.second << '\n';
	}
}

#endif
