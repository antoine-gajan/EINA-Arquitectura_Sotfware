#ifndef P1_CADENAMETEO_H
#define P1_CADENAMETEO_H

#include <string>
#include <list>
#include "Usuario.h"
#include "MeteoInformation.h"

class CadenaMeteo {
protected:
    std::list<Usuario*> usuarios;
    MeteoInformation info;

public:
    // Constructor y destructor
    CadenaMeteo(std::string ciudad, double temp, double prec, double vient, std::string tiemp);
    virtual ~CadenaMeteo() = default;
    // Gestion de los observadores
    void notify();
    void attach(Usuario* u) {usuarios.push_back(u);};
    void detach (Usuario* u) {usuarios.remove(u);};
    // Getter
    MeteoInformation get_state() {return info;}
    std::list<Usuario*> get_usuarios() {return usuarios;}
    // Setter
    void set_state(MeteoInformation& new_meteo) {info = new_meteo; notify();}
};


#endif //P1_CADENAMETEO_H
