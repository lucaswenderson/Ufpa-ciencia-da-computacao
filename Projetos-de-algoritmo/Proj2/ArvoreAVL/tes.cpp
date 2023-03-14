#include <iostream> 
using namespace std;

int josephus(int n, int k) {
  if(n == 1)
    return 1;
  else
    return (josephus(n-1, k) + k - 1) % n+1;
}
 
int main() {
  int tcase, n, k , i;

  while(scanf("%d",&tcase) == 1) {
    for(i = 1; i <= tcase; i ++) {
      cin >> n >> k;
      int temp = josephus(n, k);
      printf("Case %d: %d\n", i, temp);
    }
  } 
  return 0;
}