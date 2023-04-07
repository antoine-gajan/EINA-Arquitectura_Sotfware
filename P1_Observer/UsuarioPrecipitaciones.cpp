#include "UsuarioPrecipitaciones.h"
using namespace std;

void UsuarioPrecipitaciones::update(const MeteoInformation &meteo) {
    /// Function to update precipitations
    // Update if necessary
    if (has_notif == false || precipitaciones != meteo.get_precipitaciones() || ciudad != meteo.get_ciudad()) {
        precipitaciones = meteo.get_precipitaciones();
        date_time = meteo.get_date_time();
        ciudad = meteo.get_ciudad();
        // Now the user has received a notification
        has_notif = true;
    }
}

void UsuarioPrecipitaciones::print(ostream &str) const {
    /// Function to display the user weather information
    str << "User: " << getNombre() << " " << getApellido() << " (" << getEdad() << " years)" << endl;
    if (has_notif){
        str << "City: " << ciudad << "    " << ctime(&date_time) << endl;
        str << "Precipitations: " << precipitaciones << "%" << endl;
    }
    else {
        str << "This user has not yet received notifications...." << endl;
    }
}
