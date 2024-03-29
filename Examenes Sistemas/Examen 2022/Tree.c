#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "Tree.h"

// Inicializa un árbol a vacío.
// 0.25 pts.
void inicializarArbol(Tree *ptrTree){
    *ptrTree = NULL;
}

// Asumiendo que el árbol está ordenado (Binary Search Tree),
// se inserta un nuevo nodo ordenado por nombre con los datos
// pasados como parámetros
// 1.75 pts.
void insertarComisaria(Tree *ptrTree, char *name, double lat, double lon){
    *ptrTree=malloc(sizeof(struct Node));
    (*ptrTree)->name=malloc(strlen(name)+1);
    if(*ptrTree==NULL){
        
    }
}

// Muestra el árbol en orden, es decir, recorrido infijo.
// 1.0 pt.
void mostrarArbol(Tree t){
    ;
}

// Libera toda la memoria y deja el árbol vacío.
// 1.25 pts.
void destruirArbol(Tree *ptrTree){
    ;
}

// Devuelve el nombre de la comisaría más cercana dada una latitud y longitud.
// Si el árbol está vacío, se devuelve NULL.
// 2.0 pt.
char *localizarComisariaCercana(Tree t, double lat, double lon){
    ;
}

// Carga el fichero de texto que tiene la siguiente estructura:
// nombre comisaria 1; latitude1; longitude1;
// nombre comisaria 2; latitude2; longitude2;
// …
// y crea un árbol con un nodo por cada línea en ptrTree.
//
// 1.75 pts.
void cargarComisarias(char *filename, Tree *ptrTree){
    ;
}

// Guarda el arbol ordenador en un fichero binario.
// Cada nodo será almacenado en el fichero con la siguiente estructura:
// - Un entero con la longitude del campo name.
// - Los carácteres del campo name.
// - Un double con la latitud.
// - Un double con la longitud.
//
// 2.0 pts.
void guardarBinario(char *filename, Tree tree){
    ;
}

