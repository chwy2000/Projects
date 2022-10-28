#include <iostream>
#include <stdlib.h>
#include <String>
/*
Perez Gutierrez de Velasco Sergio Ignacio 
2193044738 
Tarea 2 Programa con Estructuras
c++ 
*/
using namespace std;

/*Estructura*/

struct Persona {  

int menor;
int edad;
char Curp1[18];

}; 

/*limite de personas a ingresar*/

int main() {
	
	int joestar;
	cout<<"Por favor ingrese el numero de personas a registrar menor a 10"<<endl;
	cin>>joestar;

	
	if(joestar>10){
		cout<<"Por favor ingrese un numero menor a 10"<<endl;
		cin>>joestar;
		
	}
	
	if(joestar<=0){
		cout<<"Por favor ingrese un numero valido de personas"<<endl;
		cin>>joestar;
		
	}
	
	/*Arreglo de personas a ser introducidas*/
	
	Persona Uno[10];
	
	for(int i = 1; i <= joestar; i++ ){
		cout<<"Indique la edad de la persona "<<i<<"."<<endl;
		cin>>Uno[i].edad;
		
		cout<<"Indique el curp de la persona "<<i<<"."<<endl;
		cin>>Uno[i].Curp1;
		
		}

	/*Datos impresos*/
	
	cout<<"\n\n*****Datos introducidos*****\n\n"<<endl;
	

	for( int i=1;i<=joestar;i++){
		cout<<"Persona "<<i<<"."<<endl;
		cout<<"Edad "<<Uno[i].edad<<"."<<endl;
		cout<<"Curp "<<Uno[i].Curp1<<"."<<endl;
	}

int suma;
	for( int i=1;i <=joestar;i++){
		suma = suma + Uno[i].edad;	
	}
	
	int promedio = suma / joestar;
	cout<<"El promedio de las edades es "<<promedio<<"."<<endl;
	
	int numero=99;
	for(int i=1; i<=joestar; i++){
		int nuevo = Uno[i].edad;
		;
		if(numero>nuevo){
			numero=nuevo;
		}
	}
	
	cout<<"\nLa edad menor es "<< numero<<"."<<endl;
	
	
	cout<<"\n\nGracias por utilizar este programa, buen dia. :)"<<endl;
	
	
	return 0;
}

	
