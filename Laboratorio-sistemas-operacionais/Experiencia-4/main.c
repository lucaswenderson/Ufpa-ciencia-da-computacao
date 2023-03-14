#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

long int fatorial(int n){
  long int a, b;
  b = 1;
  for(a=1; b<n; b++) {
    a = a*b;
    printf("%li\n", a);
  }
}

long int fibonacci(int n){
  long int a, b, aux, i;
  a = 0;
  b = 1;
  aux = 0;
  for(i=0; i<n; i++){
    aux = a + b;
    a = b;
    b = aux;
    printf("\n%li Fibonacci", aux);
  }
}

void hanoi(int n, char orig, char dest, char aux){ 
  if(n==1) {
    printf("\nllover disco 1 da torre %c para a torre %c", orig, dest);
    return;
  }
  hanoi(n-1, orig, aux, dest);
  printf("\nMover disco %d da torre %c para a torre %c", n, orig, dest);
  hanoi(n-1, aux, dest,orig);
}

long int processo_filho() {
  fibonacci(15);
}
void processo_pai(){
  printf("\nfatorial: \n");
  fatorial(20);
}
void processo_neto() {
  hanoi(6, 'A', 'C', 'B');
}

void main(void) {
  pid_t pid1;
  pid1 = fork();
  
  if(pid1 == 0) {
    processo_neto();
  }
  else {
    waitpid(pid1);
    pid1= fork();

    if(pid1 == 0) {
      processo_filho();
    }
    else {
      waitppid(pid1);
      processo_pai();
    }
}
exit(0);