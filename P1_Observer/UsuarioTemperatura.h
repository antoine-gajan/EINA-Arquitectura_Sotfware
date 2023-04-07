#ifndef P1_USUARIOTEMPERATURA_H
#define P1_USUARIOTEMPERATURA_H

#include "Usuario.h"

class UsuarioTemperatura : public Usuario {
private:
    double temperatura;
    time_t date_time;
    std::string ciudad;
    void print(std::ostream& str) const;

public:
    UsuarioTemperatura(const std::string& nom, const std::string& ape, int eda) : Usuario(nom, ape, eda){};
    void update(const MeteoInformation& meteo);
};


#endif //P1_USUARIOTEMPERATURA_H
