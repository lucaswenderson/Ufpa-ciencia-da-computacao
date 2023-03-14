#include<bits/stdc++.h>
using namespace std;
#include <iostream> 


int main(){

	// inicia o contador aleatório
	srand( time(NULL ));
  int c[] = {1, 10, 20, 30, 40, 50, 60, 70, 90, 100};
  int ni = 0;
  int arrSize = sizeof(c)/ sizeof(c[0]);
  long tempTotal = 0;
  cout <<"n\tTempo total(ns)"<<endl;
  
  while (ni < arrSize) {
    auto tempInicial = chrono::steady_clock::now();
    

    auto tempFinal = chrono::steady_clock::now();
    tempTotal = chrono::duration_cast<chrono::nanoseconds>(tempFinal-tempInicial).count();
    cout<<c[ni]<<"\t"<<tempTotal<<endl;
    ni++;        
  }
  return 0;
}

