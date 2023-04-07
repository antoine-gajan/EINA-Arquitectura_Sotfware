#include "MeteoInformation.h"

using namespace std;

void MeteoInformation::set_precipitaciones(double prec) {
    if (precipitaciones < 0 || precipitaciones > 100){
        throw("Error : las precipitaciones son un percentaje entre 0 y 100 !");
    }
    precipitaciones = prec;
}

void MeteoInformation::set_viento(double vient) {
    if (vient < 0 || vient > 100){
        throw("Error : el viento es un percentaje entre 0 y 100 !");
    }
    viento = vient;
}

MeteoInformation::MeteoInformation(double temp, double prec, double vien, std::string tiemp, std::string ciu) : temperatura(temp), precipitaciones(prec), viento(vien), tiempo(tiemp), ciudad(ciu), date_time(time(0)) {
    if (precipitaciones < 0 || precipitaciones > 100){
        throw("Error : las precipitaciones son un percentaje entre 0 y 100 !");
    }
    if (viento < 0 || viento > 100){
        throw("Error : el viento es un percentaje entre 0 y 100 !");
    }
}

std::ostream& operator<<(std::ostream &os, const MeteoInformation &meteo) {
    /// Function to display the meteo information
    os << "Ciudad: " << meteo.ciudad << endl;
    os << "Last update : "<< ctime(&meteo.date_time) << endl;
    os << "Temperatura: " << meteo.temperatura << "°C" << endl;
    os << "Precipitaciones: " << meteo.precipitaciones << "%" << endl;
    os << "Viento: " << meteo.viento << "%" << endl;
    os << "Tiempo: " << meteo.tiempo << endl;
    return os;
}

