#include <ctime>
#include <cstdlib>
#include <iostream>

using namespace std;

/** Perez Gutierrez de Velasco Sergio Ignacio
2193044738
Programa 6 Arreglos y Recursividad **/


void Aleat1 (int [], int, int);
void Aleat2 (int [], int, int, int);

int main(){
srand((unsigned)time(NULL));
double fin1;
int num, n;
int Joestar;
int jojo[10000];
int Jotaro=0;
int Joyline=0;

cout << "Por favor ingrese la cantidad de numeros aleatorios que desee:"<< endl;
cin >> Joestar;

//Genera 10 números aleatorios entre 10 y 100
for (int i=0;i<Joestar;i++){
    n=(rand()%90)+10;
    jojo[i]=n;
    cout << n << endl;

}


Aleat1 (jojo ,Jotaro ,Joestar);

Aleat2 (jojo ,Jotaro ,Joestar, Joyline);

cout << "Gracias, que tenga buen dia, Za Warudo. :) " << endl; 

}

void Aleat1(int jojoo[], int Jotaroo, int Jooestar){
	if(Jooestar == Jotaroo)
		cout<< jojoo[Jotaroo]<<endl;
	else{
		cout<< jojoo[Jooestar] << endl;
		Aleat1(jojoo,Jotaroo,Jooestar-1);
	}
}

void Aleat2(int jojoo[], int Jotaroo, int Jooestar, int Jooyline){
	if(Jooestar == Jotaroo)
		cout<< "La sumatoria es:"<< Jooyline << endl;
	else{
		Aleat2(jojoo,Jotaroo+1,Jooestar,Jooyline+jojoo[Jotaroo]);
	}
}
