#include "UsuarioTemperatura.h"
using namespace std;

void UsuarioTemperatura::update(const MeteoInformation& meteo) {
    /// Function to update temperature
    // Update only if it changed
    if (has_notif == false || meteo.get_temperatura() != temperatura || ciudad != meteo.get_ciudad())
    {
        temperatura = meteo.get_temperatura();
        date_time = meteo.get_date_time();
        ciudad = meteo.get_ciudad();
        // Now the user has received a notification
        has_notif = true;
    }
}

void UsuarioTemperatura::print(ostream &str) const {
    /// Function to display the user weather information
    str << "User: " << getNombre() << " " << getApellido() << " (" << getEdad() << " years)" << endl;
    if (has_notif){
        str << "City: " << ciudad << "    " << ctime(&date_time) << endl;
        str << "Temperature: " << temperatura << "°C" << endl;
    }
    else {
        str << "This user has not yet received notifications...." << endl;
    }
}