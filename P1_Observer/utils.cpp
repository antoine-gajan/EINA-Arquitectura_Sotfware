#include "utils.h"

bool is_linked(Usuario* u, CadenaMeteo* c){
    /// Check if a user is linked to a cadena meteo
    for (auto usuario : c->get_usuarios()){
        if (usuario == u){
            return true;
        }
    }
    return false;
}

int get_nb_usuarios_linked(CadenaMeteo* c){
    /// Get the number of users linked to a cadena meteo
    return c->get_usuarios().size();
}

int get_user_nb_cadenas_linked(Usuario* u, std::list<CadenaMeteo*> cadenas) {
    /// Get the number of cadenas linked to a user
    int count = 0;
    for (auto cadena: cadenas) {
        if (is_linked(u, cadena)) {
            count++;
        }
    }
    return count;
}