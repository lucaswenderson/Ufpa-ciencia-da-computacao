#include <fstream>
#include "../algoritmoSolucao/trie.hpp"

class TermoVazio {
private:
	Trie trie;
	
	void preencher_stop_words(std::string);
	
public:
	TermoVazio() {
		preencher_stop_words("../auxiliares/termos_vazios.txt");
	}
	bool isTermo0(std::string);
};

inline void TermoVazio::preencher_stop_words(std::string caminho) {
	std::ifstream arquivo;
	std::string s;

	arquivo.open(caminho);

	while (std::getline(arquivo, s)) {
		this->trie.inserir(s);
	}
}

inline bool TermoVazio::isTermo0(std::string s) {
	return this->trie.buscar(s);
}