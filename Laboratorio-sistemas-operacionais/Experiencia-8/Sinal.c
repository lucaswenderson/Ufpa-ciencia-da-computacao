#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define NUMTHRDS 2
pthread_t callThd[NUMTHRDS];

typedef struct {
  double saldo;
}Conta;
Conta nconta;

int sinal = 0;

void saquef(double quant, long thread){
 while(sinal==1){}
 sinal = 1;
 nconta.saldo -= quant;
printf("Foi retirado %.2fR$ pela thread %ld // Saldo atual: %.2fR$.\n", quant, thread, nconta.saldo);
 sinal = 0;
}
void depositof(double quant, long thread){
 while(sinal==1){}
 sinal = 1;
 nconta.saldo += quant;
 printf("Foi adicionado %.2fR$, pela thread %ld // Saldo: %.2fR$.\n", quant, thread, nconta.saldo);
 sinal = 0;
}


void *programa(void *arg){
 int i, saque=5, deposito=5;
 double quant;
 long thread = (long)arg;
  while (0<saque || 0<deposito){
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
 void *estado;
  pthread_attr_t attr;
  pthread_attr_init(&attr);
  nconta.saldo = 10000;
  pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);
 for (i=0; i<2; i++)
   pthread_create(&callThd[i], &attr, programa, (void *)i);
 pthread_attr_destroy(&attr);
 for (i=0; i<2; i++)
   pthread_join(callThd[i], &estado);
 printf("\nSaldo final: %.2fR$\n", nconta.saldo);
 pthread_exit(NULL);
}