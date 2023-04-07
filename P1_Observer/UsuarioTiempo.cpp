#include "UsuarioTiempo.h"
using namespace std;

void UsuarioTiempo::update(const MeteoInformation &meteo) {
    /// Function to update tiempo
    // Update only if it changed
    if (has_notif == false || meteo.get_tiempo() != tiempo || ciudad != meteo.get_ciudad())
    {
        tiempo = meteo.get_tiempo();
        date_time = meteo.get_date_time();
        ciudad = meteo.get_ciudad();
        // Now the user has received a notification
        has_notif = true;
    }
}

void UsuarioTiempo::print(ostream &str) const {
    /// Function to display the user weather information
    str << "User: " << getNombre() << " " << getApellido() << " (" << getEdad() << " years)" << endl;
    if (has_notif){
        str << "City: " << ciudad << "    " << ctime(&date_time) << endl;
        str << "Weather: " << tiempo << endl;
    }
    else {
        str << "This user has not yet received notifications...." << endl;
    }
}
