#include <stdio.h>
#include "MProcesos.h"


void Crear(LProc *lroundrobin){
    *lroundrobin = NULL;  
}

void AnadirProceso(LProc *lroundrobin, int idproc){
    LProc aux = malloc(sizeof(struct Nodo));
    aux->id=idproc;
    if(*lroundrobin == NULL) {
        *lroundrobin=aux;
        aux->sig=aux;
    } else {
        aux->sig=(*lroundrobin)->sig;
        (*lroundrobin)->sig=aux;
        *lroundrobin=aux;
    }
}

void EjecutarProcesos(LProc lroundrobin){
    if(lista!=NULL){
    LProc aux = lista->sig;
        do{
            printf("\nQuantum de proceso %i realizado", aux->id);
            aux=aux->sig;
        }while (aux!=lista->sig);
    } else {
        printf("La lista de procesos está vacia");
    }
}


void EliminarProceso(int id, LProc *lista){
    LProc aux = *lista;
    LProc Ejec;
    int ultimo=0;
    if(*lista!=NULL){
        do{
            aux=aux->sig;
        } while(aux->sig->id!=id);
        if(aux->sig==aux) ultimo = 1;
        Ejec=aux->sig;
        aux->sig=Ejec->sig;
        if(Ejec==*lista) *lista=aux;
        free(Ejec);
        if(ultimo==1) *lista=NULL;
    }
}

void EscribirFichero (char * nomf, LProc *lista){
    FILE *f =fopen(nomf, "w");
    LProc aux = *lista;
    if(*lista!=NULL){
    int nump=0;
    do{
        nump++;
        aux=aux->sig;
    }while(aux!=*lista);
    fprintf(f, "%i ",nump);
    for(int i = 0; i<nump; i++){
        fprintf(f, " %i ",aux->id);
        EliminarProceso((*lista)->id, &(*lista));

    }
    fclose(f);
    *lista=NULL;
    }else{
        printf("Lista vacia");
    }
}