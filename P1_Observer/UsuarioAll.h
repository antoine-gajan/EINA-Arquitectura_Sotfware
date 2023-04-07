#ifndef P1_USUARIOALL_H
#define P1_USUARIOALL_H


#include <string>
#include <ostream>
#include "Usuario.h"

class UsuarioAll : public Usuario{
private:
    double precipitaciones;
    double viento;
    double temperatura;
    std::string tiempo;
    std::string ciudad;
    time_t date_time;
    void print(std::ostream& str) const;
public:
    UsuarioAll(const std::string& nom, const std::string& ape, int eda) : Usuario(nom, ape, eda){};
    void update(const MeteoInformation& meteo);
};


#endif //P1_USUARIOALL_H
