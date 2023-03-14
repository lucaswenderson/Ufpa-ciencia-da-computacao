#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define NUMTHRDS 2
pthread_t callThd[NUMTHRDS];
pthread_mutex_t mutex;

typedef struct {
	double saldo;
}Conta;
Conta nconta;


void saquef(double quant, long thread){
  pthread_mutex_lock (&mutex);
  nconta.saldo -= quant;
  printf("Foi retirado %.2fR$ pela thread %ld // Saldo atual: %.2fR$.\n", quant, thread, nconta.saldo);
  pthread_mutex_unlock (&mutex);
}

void depositof(double quant, long thread){
  pthread_mutex_lock (&mutex);
  nconta.saldo += quant;
  printf("Foi adicionado %.2fR$, pela thread %ld // Saldo: %.2fR$.\n", quant, thread, nconta.saldo);
  pthread_mutex_unlock (&mutex);
}

void *operacao(void *arg){
  int i, saque=5, deposito=5;
  double quant;
  long thread = (long)arg;
	while (saque>0 || deposito>0){
    quant = rand()%401+100;
    if (rand()%2==0){
      if (saque>0){
        saquef(quant, thread);
        saque--;
      }else{
        depositof(quant, thread);
        deposito--;
      }
    }else{
      if (deposito>0){
        depositof(quant, thread);
        deposito--;
      }else{
        saquef(quant, thread);
        saque--;
      }
    }
    sleep(rand()%5);
  }
  pthread_exit((void*) 0);
}

int main(void) {
	long i;
  void *status;
  pthread_attr_t attr;
  nconta.saldo = 10000;
  pthread_mutex_init(&mutex, NULL);
	pthread_attr_init(&attr);
	pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);
  for (i=0; i<2; i++)
    pthread_create(&callThd[i], &attr, operacao, (void *)i);
  pthread_attr_destroy(&attr);
  for (i=0; i<2; i++)
    pthread_join(callThd[i], &status);
  printf("\nSaldo final %.2fR$\n", nconta.saldo);
  pthread_mutex_destroy(&mutex);
  pthread_exit(NULL);
}