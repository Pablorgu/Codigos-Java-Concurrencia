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
    if(lroundrobin!=NULL) {
       LProc aux=lroundrobin;
        do{
            lroundrobin = (lroundrobin)->sig;
            printf("Se ha agotado el quantum del proceso %i", lroundrobin->id);
        }while(lroundrobin->sig!= aux->sig);
    } else {
        perror("La lista esta vacia y eso me jode una barbaridad");
    }
}


void EliminarProceso(int id, LProc *lista){
    LProc aux = *lista;
    if(aux!=NULL) {
        aux=aux->sig;
        LProc elim;
            do{
                if(aux->sig->id==id){
                    aux->sig=elim;
                    if(elim==lista){
                        *lista=aux;
                    }
                    aux->sig=elim->sig;
                    elim->sig=NULL;
                    free(elim);
                }
                aux=aux->sig;
            }while(aux->sig!= (*lista)->sig);
    } else {
        printf("La lista esta vacia y eso me jode una barbaridad");
        exit(-1);
    }
}

void EscribirFichero (char * nomf, LProc *lista){
    FILE *f =fopen(nomf, "w");
    LProc elim = *lista;
    int nump=0;
    do{
        nump++;
        elim=elim->sig;
    }while(elim->sig!=(*lista)->sig);
    fprintf(f, "%i",nump);
    while((*lista)->sig!=NULL) {
        elim=(*lista)->sig;
        (*lista)->sig=elim->sig;
        elim->sig==NULL;
        fprintf(f, elim);
        free(elim);
    }
    fclose(f);
}

