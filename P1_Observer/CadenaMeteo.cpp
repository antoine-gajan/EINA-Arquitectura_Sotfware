#include "CadenaMeteo.h"


CadenaMeteo::CadenaMeteo(std::string ciu, double temp, double prec, double vient, std::string tiemp) : info(MeteoInformation(temp, prec, vient, tiemp, ciu)){

}

void CadenaMeteo::notify() {
    /// Function to notify users of changes
    for (auto usuario : usuarios){
        usuario->update(get_state());
    }
}
