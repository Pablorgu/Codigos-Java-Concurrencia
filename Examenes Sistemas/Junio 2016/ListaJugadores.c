#include "ListaJugadores.h"
#include <stdio.h>


//crea una lista vac�a (sin ning�n nodo)
void crear(TListaJugadores *lc){
    *lc= NULL;
}

//inserta un nuevo jugador en la lista de jugadores, poniendo 1 en el n�mero de goles marcados.
//Si ya existe a�ade 1 al n�mero de goles marcados.
void insertar(TListaJugadores *lj,unsigned int id){
    TListaJugadores aux=malloc(sizeof(struct Nodo));
    aux->numeroJugador=id;
    if(*lj==NULL){
        *lj=aux;
        aux->numgoles=1;
        aux->sig=NULL;
    }else{
        TListaJugadores it =*lj;
        while (it!=NULL && it->numeroJugador!=id){
            it=it->sig;
        }
        if(it!=NULL && it->numeroJugador==id){
            it->numgoles++;
        } else{
            it=*lj;
            if(aux->numeroJugador<(*lj)->numeroJugador){
                aux->numgoles=1;
                aux->sig=*lj;
                *lj=aux;
            } else{
                while (it->sig!=NULL && aux->numeroJugador > it->numeroJugador){
                    it=it->sig;
                }
                if(it->sig!=NULL) {
                    aux->sig=it->sig;
                }else{
                    aux->sig=NULL;
                }
                it->sig=aux;
                aux->numgoles=1;
            }
        }

        
        
    }
}

//recorre la lista circular escribiendo los identificadores y los goles marcados
void recorrer(TListaJugadores lj){  
    printf("\nLista de jugadores: \n");
    while(lj!=NULL){
        printf("ID: %i, Goles marcados: %i\n", lj->numeroJugador, lj->numgoles);
        lj=lj->sig;
    }
}

//devuelve el n�mero de nodos de la lista
int longitud(TListaJugadores lj){
    int cont=0;
    if(lj!=NULL){
        while(lj!=NULL){
            cont++;
            lj=lj->sig;
        }
    }
    return cont;
}

//Eliminar. Toma un n�mero de goles como par�metro y
//elimina todos los jugadores que hayan marcado menos que ese n�mero de goles
void eliminar(TListaJugadores *lj,unsigned int n){
    TListaJugadores it=*lj;
    TListaJugadores elim;
    while(it!=NULL && it->numgoles<n){
        elim=it;
        *lj=it->sig;
        it=*lj;
        free(elim);
    }
    while(it!=NULL && it->sig!=NULL){
        if(it->sig->numgoles<n){
            elim=it->sig;
            it->sig=elim->sig;
            free(elim);
        }else{
            it=it->sig;
        }
    }
}   


// Devuelve el ID del m�ximo jugador. Si la lista est� vac�a devuelve 0. Si hay m�s de un jugador con el mismo n�mero de goles que el m�ximo devuelve el de mayor ID
// Hay que devolver el identificador, no el n�mero de goles que ha marcado
unsigned int maximo(TListaJugadores lj){
    int maximo=0;
    int id;
    while(lj!=NULL){
        if(lj->numgoles>maximo){ 
            id=lj->numeroJugador;
            maximo=lj->numgoles;
        }
        lj=lj->sig;
    }
    return id;
}

//Destruye la lista y libera la memoria)
void destruir(TListaJugadores *lj){
    TListaJugadores elim;
    while(*lj!=NULL){
        elim=*lj;
        *lj=(*lj)->sig;
        elim->sig=0;
        free(elim);
    }
    *lj=NULL;
}
