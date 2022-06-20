/*
 * Principal.c
 *
 *  Created on: 14/6/2016
 *      Author: esc
 */

#include "ListaJugadores.h"
#include <stdio.h>

//Lee el fichero y lo introduce en la lista
void cargarFichero (char * nombreFich, TListaJugadores *lj){
	FILE *f= fopen("goles.bin", "rb");
	if(f==NULL){
		perror("Fichero erroneo");
		exit(-1);
	}
	int cont[3];
	while(fread(&cont, sizeof(unsigned int), 3, f) == 3) {
		insertar(&(*lj), cont[1]);
	}
	fclose(f);
}


int main(){

	TListaJugadores lj;
	crear(&lj);
    unsigned int num_goles;
	cargarFichero ("goles.bin",&lj);
	// insertar(&lj, 2);
	// insertar(&lj, 1);
	// insertar(&lj, 3);
	// insertar(&lj, 2);
	printf("Hay un total de %d jugadores\n",longitud(lj));
	fflush(stdout);

	recorrer(lj);
	fflush(stdout);
	printf("Introduce un n�mero de goles: \n");
	fflush(stdout);
	scanf("%d",&num_goles);


	eliminar(&lj,num_goles);
	printf("--------------------------------------\n");
	recorrer(lj);
	printf("Hay un total de %d jugadores\n",longitud(lj));
	fflush(stdout);

	printf ("El jugador que m�s goles ha marcado es el que tiene ID: %d",maximo(lj));
	fflush(stdout);
	destruir (&lj);

	return 0;
}
