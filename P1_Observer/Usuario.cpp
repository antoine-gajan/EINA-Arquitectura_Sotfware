#include "Usuario.h"

using namespace std;

Usuario::Usuario(const std::string& nom, const std::string& ape, int eda) : nombre(nom), apellido(ape), edad(eda), has_notif(false){
    if (edad < 0)
    {
        throw("Error : la edad debe ser un entero positivo !");
    }
}

std::ostream &operator<<(ostream &os, const Usuario &usuario) {
    usuario.print(os);
    return os;
}
