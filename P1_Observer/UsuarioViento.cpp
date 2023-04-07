#include "UsuarioViento.h"
using namespace std;

void UsuarioViento::update(const MeteoInformation &meteo) {
    /// Function to update wind
    // Update only if it changed
    if (has_notif == false || meteo.get_viento() != viento || ciudad != meteo.get_ciudad())
    {
        viento = meteo.get_viento();
        date_time = meteo.get_date_time();
        ciudad = meteo.get_ciudad();
        // Now the user has received a notification
        has_notif = true;
    }
}

void UsuarioViento::print(ostream &str) const {
    /// Function to display the user weather information
    str << "User: " << getNombre() << " " << getApellido() << " (" << getEdad() << " years)" << endl;
    if (has_notif){
        str << "City: " << ciudad << "    " << ctime(&date_time) << endl;
        str << "Wind: " << viento << "%" << endl;
    }
    else {
        str << "This user has not yet received notifications...." << endl;
    }
}