#include <stdio.h>
#include "userList.h"

T_user * createUser(char *name, int uid, char *dir) {
    T_user *user = malloc(sizeof(T_user));
    user->userName_=malloc(strlen(name)+1);
    strcpy(user->userName_, name);
    user->uid_=uid;
    user->homeDirectory_= malloc(strlen(dir)+1);
    strcpy(user->homeDirectory_, dir);
    user->previousUser_=NULL;
    user->nextUser_=NULL;
    return user;
}
T_userList createUserList() {
    T_userList lista;
     lista.head_=NULL;
     lista.numberOfUsers_=NULL;
     lista.numberOfUsers_=0;
    return lista;
}
int addUser(T_userList * list, T_user *user) {
    T_user *aux = list->head_;
    int anadido=0;
    int encontrado=0;
    if(aux!=NULL){
        while(aux!=NULL) {
                if(strcmp(aux->userName_, user->userName_)==0 || aux->uid_ == user->uid_){
                    encontrado=1;
                }
                aux=aux->previousUser_;
        }
    }
    if(encontrado==0){
        aux=list->head_;
        if(list->numberOfUsers_==0){
            //crealista cuando esta vacia
            list->head_=user;
            list->tail_=user;
        }else{
            user->previousUser_=aux;
            list->head_=user;
            aux->nextUser_=user;            
        }
        list->numberOfUsers_++;
    }
    return anadido;
}
int getUid(T_userList list, char *userName) {
    T_user *aux = list.head_;
    int uid=-1;
    while(aux!=NULL) {
        if(strcmp(aux->userName_, userName)==0){
            uid=aux->uid_;
        }
        aux=aux->previousUser_;
    }
    return uid;
}

int deleteUser(T_userList *list, char* userName) {
    int encontrado=-1;
    if(list!=NULL){
        T_user *aux =list->head_;
            while(aux!=NULL && strcmp(aux->userName_,userName)!=0) {
            aux=aux->previousUser_;
        }
        if(aux!=NULL && strcmp(aux->userName_, userName)==0){
                encontrado=0;
        }
        if(encontrado==0){
            T_user *ant;
            if(aux==list->head_){
                ant=aux;
                aux=aux->previousUser_;
                list->head_=aux;
                ant->previousUser_=NULL;
                free(ant);
                aux->nextUser_=NULL;
            }else if(aux==list->tail_) {
                ant=aux;
                aux=aux->nextUser_;
                list->tail_=aux;
                ant->nextUser_=NULL;
                free(aux);
                aux->previousUser_=NULL;
            } else {
                ant=aux->previousUser_;
                ant->nextUser_=aux->nextUser_;
                aux->nextUser_=NULL;
                aux->previousUser_==NULL;
                free(aux);
            }
        }
        list->numberOfUsers_--;
        if(list->numberOfUsers_==0){
            list->head_==NULL;
            list->tail_==NULL;
        }
    }
    return encontrado;
}

void printUserList(T_userList list, int reverse) {
    if(list.numberOfUsers_!=0){
        T_user *aux;
        if(reverse==0) {
            printf("\nLos usuarios son: \n");
            aux=list.head_;
            while(aux!=NULL){
                printf("usuario: %s, uid:%i\n", aux->userName_, aux->uid_);
                aux=aux->previousUser_;
            }
        } else if(reverse==1) {
            printf("\nLos usuarios son: \n");
            aux=list.tail_;
            while(aux!=NULL){
                printf("usuario: %s, uid:%i\n", aux->userName_, aux->uid_);
                aux=aux->nextUser_;
            }
        }
    } else {
        printf("La lista está vacia\n");
    }
}
