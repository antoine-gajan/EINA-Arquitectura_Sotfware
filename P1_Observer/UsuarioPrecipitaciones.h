#ifndef P1_USUARIOPRECIPITACIONES_H
#define P1_USUARIOPRECIPITACIONES_H


#include "Usuario.h"

class UsuarioPrecipitaciones : public Usuario{
private:
    double precipitaciones;
    std::string ciudad;
    time_t date_time;
    void print(std::ostream& str) const;

public:
    UsuarioPrecipitaciones(const std::string& nom, const std::string& ape, int eda) : Usuario(nom, ape, eda){};
    void update(const MeteoInformation& meteo);
};


#endif //P1_USUARIOPRECIPITACIONES_H
