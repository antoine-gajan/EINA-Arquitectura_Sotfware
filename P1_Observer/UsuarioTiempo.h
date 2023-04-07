#ifndef P1_USUARIOTIEMPO_H
#define P1_USUARIOTIEMPO_H


#include <string>
#include "Usuario.h"

class UsuarioTiempo : public Usuario {
private:
    std::string tiempo;
    time_t date_time;
    std::string ciudad;
    void print(std::ostream& str) const;

public:
    UsuarioTiempo(const std::string& nom, const std::string& ape, int eda) : Usuario(nom, ape, eda){};
    void update(const MeteoInformation &meteo);
};


#endif //P1_USUARIOTIEMPO_H
