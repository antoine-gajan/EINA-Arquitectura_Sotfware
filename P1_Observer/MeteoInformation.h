#ifndef P1_METEOINFORMATION_H
#define P1_METEOINFORMATION_H

#include <string>
#include <ostream>

class MeteoInformation {
private:
    double temperatura;
    double precipitaciones; // Percentaje (entre 0 y 100)
    double viento; // Percentaje (entre 0 y 100)
    std::string tiempo; // Percentaje (entre 0 y 100)
    time_t date_time; // Date y hora de ultima modificacion
    std::string ciudad;

public:
    // Constructors
    MeteoInformation(double temp, double prec, double vien, std::string tiemp, std::string ciu);
    ~MeteoInformation() = default;

    // Getters
    double get_temperatura() const {return temperatura;}
    double get_precipitaciones() const {return precipitaciones;}
    double get_viento() const {return viento;}
    time_t get_date_time() const {return date_time;}
    const std::string& get_tiempo() const {return tiempo;}
    const std::string& get_ciudad() const {return ciudad;}

    // Setters
    void set_temperatura(double temp) {temperatura = temp;}
    void set_precipitaciones(double prec);
    void set_viento(double vient);
    void set_date_time()  {date_time = time(0);}
    void set_tiempo(const std::string& tiemp) {tiempo = tiemp;}

    // Display
    friend std::ostream& operator<<(std::ostream& os, const MeteoInformation& meteo);
};


#endif //P1_METEOINFORMATION_H
