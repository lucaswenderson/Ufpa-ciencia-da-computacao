#ifndef REGISTROS_H
#define REGISTROS_H

#include <filesystem>
#include <algorithm>

namespace fs = std::filesystem;

class Registros{
private:
	int n;
	fs::path diretorio;
	std::vector<fs::path> caminhos_arquivos;
	void preencher_caminhos_arquivos();

public:
	Registros(std::string diretorio) {
		this->n = 0;
		this->diretorio = diretorio;
		preencher_caminhos_arquivos();
	}

	Registros() {}

	fs::path get_caminho(int);
	bool buscar(fs::path);
	int tamanho();
};

inline void Registros::preencher_caminhos_arquivos() {
	for (auto &p : fs::directory_iterator(this->diretorio)) {
		if (fs::is_regular_file(p.status())) {
			this->caminhos_arquivos.push_back(p.path());
			this->n++;
		}
	}
}

inline fs::path Registros::get_caminho(int i) {
	return this->caminhos_arquivos[i];
}

inline bool Registros::buscar(fs::path arquivo) {
	if (std::find(this->caminhos_arquivos.begin(), this->caminhos_arquivos.end(), arquivo) != this->caminhos_arquivos.end()) {
		return true;
	}
	return false;
}

inline int Registros::tamanho() {
	return this->n;
}

#endif