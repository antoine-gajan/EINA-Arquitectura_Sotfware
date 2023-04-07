#ifndef P1_USUARIOVIENTO_H
#define P1_USUARIOVIENTO_H

#include "Usuario.h"

class UsuarioViento : public Usuario {
private:
    double viento;
    time_t date_time;
    std::string ciudad;
    void print(std::ostream& str) const;

public:
    UsuarioViento(const std::string& nom, const std::string& ape, int eda) : Usuario(nom, ape, eda){};
    void update(const MeteoInformation& meteo);
};


#endif //P1_USUARIOVIENTO_H
