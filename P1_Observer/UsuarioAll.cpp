#include "UsuarioAll.h"

using namespace std;

void UsuarioAll::update(const MeteoInformation &meteo) {
    /// Function to update all information
    // Update only if it changed
    if (has_notif == false || meteo.get_temperatura() != temperatura || meteo.get_precipitaciones() != precipitaciones || meteo.get_tiempo() != tiempo || ciudad != meteo.get_ciudad())
    {
        temperatura = meteo.get_temperatura();
        precipitaciones = meteo.get_precipitaciones();
        tiempo = meteo.get_tiempo();
        date_time = meteo.get_date_time();
        ciudad = meteo.get_ciudad();
        viento = meteo.get_viento();
        // Now the user has received a notification
        has_notif = true;
    }

}

void UsuarioAll::print(ostream &str) const {
    /// Function to display the user weather information
    str << "User: " << getNombre() << " " << getApellido() << " (" << getEdad() << " years)" << endl;
    if (has_notif){
        str << "City: " << ciudad << "    "<< ctime(&date_time) << endl;
        str << "Temperature: " << temperatura << "°C" << endl;
        str << "Precipitations: " << precipitaciones << "%" << endl;
        str << "Wind: " << viento << "%" << endl;
        str << "Weather: " << tiempo << endl;
    }
    else {
        str << "This user has not yet received notifications...." << endl;
    }
}