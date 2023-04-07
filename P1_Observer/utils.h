#ifndef P1TEST_UTILS_H
#define P1TEST_UTILS_H

#include <string>
#include <list>
#include "Usuario.h"
#include "MeteoInformation.h"
#include "CadenaMeteo.h"

bool is_linked(Usuario* u, CadenaMeteo* c);
int get_nb_usuarios_linked(CadenaMeteo* c);
int get_user_nb_cadenas_linked(Usuario* u, std::list<CadenaMeteo*> cadenas);

#endif //P1TEST_UTILS_H
