#include <stdio.h>
#include <stdlib.h>
#include "colaprio.h"

void insertar(int *cp, int id, int prio){
    TColaPrio aux = malloc(sizeof(struct Nodo));
    aux->idProceso=id;
    aux->prioProceso=prio;
    if(*cp == NULL) {
        aux->sig=NULL;
        *cp=aux;
    } else {
        TColaPrio it=*cp;
        while(it->sig!=NULL && it->prioProceso<=prio){
            it=it->sig;
        }
        aux->sig=it->sig;
        it->sig=aux;
    }
}


void Crear_Cola(char *nombre, TColaPrio *cp){
    FILE *archivo = fopen( nombre, "rb");
    if(archivo == NULL) {
        printf("No se ha podido leer el archivo correctamente");
        exit(-1);
    } else {
        *cp=NULL;
        int num, id, prio;
        fread(&num, sizeof(int), 1, archivo);
        for(int i = 0; i< num; i++) {
            fread(id, sizeof(int), 1, archivo);
            fread(prio, sizeof(int), 1, archivo);
            insertar(cp,id,prio);
        }
        fclose(archivo);
    }
}
void Mostrar(TColaPrio cp){
    if(cp!=NULL) {
        while(cp!=NULL) {
            printf("Puesto: %i, Id de proceso: %i, Prio de proceso: %i \n",i,cp->idProceso, cp->prioProceso);
            cp=cp->sig;
        }
    }
}
void Destruir(TColaPrio *cp){
    TColaPrio elim = *cp;
    while((*cp)->sig!=NULL) {
        elim=*cp;
        *cp=(*cp)->sig;
        elim->sig==NULL;
        free(elim);
    }
    *cp=NULL;
}
void Ejecutar_Max_Prio(TColaPrio *cp){
    TColaPrio elim = *cp;
    TColaPrio aux = *cp;
    while(aux->sig!=NULL){
        aux=aux->sig;
    }
   Ejecutar(cp, aux->prioProceso);
}
void Ejecutar(TColaPrio *cp, int prio){
    TColaPrio elim = *cp;
    TColaPrio aux = *cp;
    while(aux->sig!=NULL && aux->sig->prioProceso==prio) {
        elim=aux->sig;
        aux->sig=elim->sig;
        free(elim);
    }
}
