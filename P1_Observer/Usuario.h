#ifndef P1_USUARIO_H
#define P1_USUARIO_H

#include <string>
#include <ostream>
#include "MeteoInformation.h"

class Usuario {
protected:
    std::string nombre;
    std::string apellido;
    int edad;
    bool has_notif;
    virtual void print(std::ostream& str) const = 0;
public:
    Usuario(const std::string& nom, const std::string& ape, int eda);
    ~Usuario() = default;
    virtual void update(const MeteoInformation&) = 0;
    const std::string& getNombre() const { return nombre; }
    const std::string& getApellido() const { return apellido; }
    int getEdad() const { return edad; }
    friend std::ostream& operator<<(std::ostream& os, const Usuario& usuario);
};



#endif //P1_USUARIO_H
