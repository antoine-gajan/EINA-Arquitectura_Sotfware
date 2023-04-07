#include <iostream>
#include "CadenaMeteo.h"
#include "UsuarioPrecipitaciones.h"
#include "UsuarioTemperatura.h"
#include "UsuarioTiempo.h"
#include "UsuarioAll.h"
#include "UsuarioViento.h"
#include "utils.h"

using namespace std;

int main() {
    // List of users and meteo stations
    list<Usuario*> users;
    list<CadenaMeteo*> stations;

    int choice = 0;
    // While not exit
    while (choice != 8){
        // Main menu
        printf("\n\n===== METEOROLOGICAL APPLICATION =====\n");
        choice = 0;
        while (choice < 1 || choice > 8) {
            cout << "Main menu : " << endl;
            cout << "1. Add a new user" << endl;
            cout << "2. Add a new meteo station" << endl;
            cout << "3. Link a meteo station to a user so that he receive notifications" << endl;
            cout << "4. Unlink a meteo station from a user" << endl;
            cout << "5. Update weather information for a meteo station" << endl;
            cout << "6. Show weather notifications for a given user" << endl;
            cout << "7. Show weather information for a given meteo station" << endl;
            cout << "8. Exit" << endl;
            cout << "\nYour choice: " << endl;
            cin >> choice;
        }
        switch (choice) {
            case 1: {
                // Add a new user
                cout << "===== ADD A NEW USER =====" << endl;
                // Declare variables
                string name, surname, city;
                int age;
                // Ask for user information
                cout << "Name: " << endl;
                cin >> name;
                cout << "Surname: " << endl;
                cin >> surname;
                cout << "Age: " << endl;
                cin >> age;

                int choice2 = 0;
                // Menu with different catagories
                while (choice2 < 1 || choice2 > 5) {
                    cout << "What are you interested in : " << endl;
                    cout << "1. Temperature" << endl;
                    cout << "2. Precipitations" << endl;
                    cout << "3. Wind" << endl;
                    cout << "4. Weather" << endl;
                    cout << "5. All" << endl;
                    cout << "\nYour choice: " << endl;
                    cin >> choice2;
                }

                switch (choice2) {
                    case 1: {
                        // Temperature
                        UsuarioTemperatura *user_temp = new UsuarioTemperatura(name, surname, age);
                        cout << "User successfully added !" << endl;
                        users.push_back(user_temp);
                        break;
                    }
                    case 2: {
                        // Precipitations
                        UsuarioPrecipitaciones *user_prec = new UsuarioPrecipitaciones(name, surname, age);
                        cout << "User successfully added !" << endl;
                        users.push_back(user_prec);
                        break;
                    }
                    case 3: {
                        // Wind
                        UsuarioViento *user_wind = new UsuarioViento(name, surname, age);
                        cout << "User successfully added !" << endl;
                        users.push_back(user_wind);
                        break;
                    }
                    case 4: {
                        // Weather
                        UsuarioTiempo *user_time = new UsuarioTiempo(name, surname, age);
                        cout << "User successfully added !" << endl;
                        users.push_back(user_time);
                        break;
                    }
                    case 5: {
                        // All
                        UsuarioAll *user_all = new UsuarioAll(name, surname, age);
                        cout << "User successfully added !" << endl;
                        users.push_back(user_all);
                        break;
                    }
                    default: {
                        cout << "Invalid option !" << endl;
                        break;
                    }
                }
                break;
            }
            case 2: {
                // Add a new weather point
                cout << "===== ADD A NEW WEATHER POINT =====" << endl;
                // Declare variables
                string city_name, weather;
                double temp, precip, wind;
                // Ask for weather information
                cout << "City: " << endl;
                cin >> city_name;
                cout << "Temperature (en °C): " << endl;
                cin >> temp;
                cout << "Precipitations (en %): " << endl;
                cin >> precip;
                cout << "Wind (en %): " << endl;
                cin >> wind;
                cout << "Weather (sun/cloud/...): " << endl;
                cin >> weather;
                // Create a new weather point
                CadenaMeteo *station = new CadenaMeteo(city_name, temp, precip, wind, weather);
                cout << "Weather point successfully added !" << endl;
                stations.push_back(station);
                break;
            }
            case 3: {
                // Link a meteo station to a user so that he receive notifications
                cout << "===== LINK A METEO STATION TO A USER =====" << endl;
                if (users.empty() || stations.empty()) {
                    cout << "No users or weather points found !" << endl;
                    break;
                }
                // Select user
                cout << "Select a user : " << endl;
                int i = 1;
                for (Usuario *user : users) {
                    cout << i << ". " << user->getNombre() << " " << user->getApellido() << endl;
                    i++;
                }
                int choice = 0;
                while (choice < 1 || choice > users.size()) {
                    cout << "\nYour choice: " << endl;
                    cin >> choice;
                }
                // Get user selected
                Usuario *user_selected = nullptr;
                i = 1;
                for (auto user : users){
                    if (i == choice){
                        user_selected = user;
                        break;
                    }
                    i++;
                }

                // Select weather point if possible
                if (stations.size() - get_user_nb_cadenas_linked(user_selected, stations) > 0) {
                    cout << "Select a weather point : " << endl;
                    int j = 1;
                    for (CadenaMeteo *station : stations) {
                        if (!is_linked(user_selected, station)){
                            cout << j << ". " << station->get_state().get_ciudad() << endl;
                            j++;
                        }
                    }
                    choice = 0;
                    while (choice < 1 || choice > j) {
                        cout << "\nYour choice: " << endl;
                        cin >> choice;
                    }
                    // Get weather point selected
                    CadenaMeteo *station_selected = nullptr;
                    j = 1;
                    for (auto station : stations){
                        if (!is_linked(user_selected, station)){
                            if (j == choice){
                                station_selected = station;
                                break;
                            }
                            j++;
                        }
                    }
                    // Link the weather point to the user
                    station_selected->attach(user_selected);
                }
                else {
                    cout << "No weather points available !" << endl;
                    break;
                }
                break;
            }
            case 4: {
                // Unlink a meteo station from a user
                cout << "===== UNLINK A METEO STATION FROM A USER =====" << endl;
                if (users.empty() || stations.empty()) {
                    cout << "No users or weather points found !" << endl;
                    break;
                }
                // Select user
                cout << "Select a user : " << endl;
                int i = 1;
                for (Usuario *user : users) {
                    cout << i << ". " << user->getNombre() << " " << user->getApellido() << endl;
                    i++;
                }
                int choice = 0;
                while (choice < 1 || choice > users.size()) {
                    cout << "\nYour choice: " << endl;
                    cin >> choice;
                }
                // Get the user selected
                i = 1;
                Usuario* user_selected = nullptr;
                for (auto user : users){
                    if (i == choice){
                        user_selected = user;
                        break;
                    }
                    else {
                        i++;
                    }
                }
                // Select weather point if exists
                if (get_user_nb_cadenas_linked(user_selected, stations) > 0){
                    cout << "Weather points linked to this user : " << endl;
                    int j = 1;
                    for (CadenaMeteo *station : stations) {
                        if (is_linked(user_selected, station)){
                            cout << j << ". " << station->get_state().get_ciudad() << endl;
                            j++;
                        }
                    }
                    choice = 0;
                    while (choice < 1 || choice > j) {
                        cout << "\nYour choice: " << endl;
                        cin >> choice;
                    }
                    // Get the weather point selected
                    j = 1;
                    CadenaMeteo* station_selected = nullptr;
                    for (auto station : stations){
                        if (j == choice){
                            station_selected = station;
                            break;
                        }
                        else {
                            j++;
                        }
                    }
                    // Unlink weather point from user
                    station_selected->detach(user_selected);
                    cout << user_selected->getNombre() << " " << user_selected->getApellido() << " is now unsubscribed from the " << station_selected->get_state().get_ciudad() << " meteo station." << endl;
                }
                else {
                    cout << "This user isn't subscribed to any weather point !" << endl;
                }
                break;
            }
            case 5: {
                // Update weather information
                cout << "===== UPDATE WEATHER INFORMATION =====" << endl;
                // Verify if a meteo station exists
                if (stations.empty()) {
                    cout << "No weather point exists for the moment !" << endl;
                    break;
                }
                // Declare variables
                double temp, precip, wind;
                string weather;
                // Select a meteo station
                cout << "Select a meteo station : " << endl;
                int i = 1;
                // Display all meteo stations
                for (CadenaMeteo *station: stations) {
                    cout << i << ". " << station->get_state().get_ciudad() << endl;
                    i++;
                }
                // Ask user choice
                int choice3 = 0;
                while (choice3 < 1 || choice3 > stations.size()) {
                    cout << "\nYour choice: " << endl;
                    cin >> choice3;
                }
                cout << "Temperature (en °C): " << endl;
                cin >> temp;
                cout << "Precipitations (en %): " << endl;
                cin >> precip;
                cout << "Wind (en %): " << endl;
                cin >> wind;
                cout << "Weather (sun/cloud/...): " << endl;
                cin >> weather;
                // Update weather information
                i = 1;
                for (CadenaMeteo *station: stations) {
                    // If it's the selected meteo station
                    if (choice3 == i) {
                        MeteoInformation meteo = MeteoInformation(temp, precip, wind, weather,
                                                                  station->get_state().get_ciudad());
                        station->set_state(meteo);
                        cout << "Weather information successfully updated !" << endl;
                        // Display a recap of the weather information
                        cout << meteo;
                    }
                    i++;
                }
                break;
            }
            case 6:
            {
                // Show weather information for a given user
                cout << "===== SHOW WEATHER INFORMATION FOR A GIVEN USER =====" << endl;
                // Verify if a user exists
                if (users.empty()) {
                    cout << "No user exists for the moment !" << endl;
                    break;
                }
                // Select a user
                cout << "Select a user : " << endl;
                int i = 1;
                // Display all users
                for (Usuario *user: users) {
                    cout << i << ". " << user->getNombre() << " " << user->getApellido() << endl;
                    i++;
                }
                // Ask user choice
                int choice4 = 0;
                while (choice4 < 1 || choice4 > users.size()) {
                    cout << "\nYour choice: " << endl;
                    cin >> choice4;
                }
                // Display weather information for the selected user
                i = 1;
                for (Usuario *user: users) {
                    if (choice4 == i) {
                        cout << *user << endl;
                    }
                    i++;
                }
                break;
            }
            case 7: {
                // Show weather information for a given meteo station
                cout << "===== SHOW WEATHER INFORMATION FOR A GIVEN METEO STATION =====" << endl;
                // Verify if a meteo station exists
                if (stations.empty()) {
                    cout << "No weather point exists for the moment !" << endl;
                    break;
                }
                // Select a meteo station
                cout << "Select a meteo station : " << endl;
                int i = 1;
                // Display all meteo stations
                for (CadenaMeteo *station: stations) {
                    cout << i << ". " << station->get_state().get_ciudad() << endl;
                    i++;
                }
                // Ask user choice
                int choice3 = 0;
                while (choice3 < 1 || choice3 > stations.size()) {
                    cout << "\nYour choice: " << endl;
                    cin >> choice3;
                }
                // Display weather information for the selected meteo station
                i = 1;
                for (CadenaMeteo *station: stations) {
                    if (choice3 == i) {
                        cout << station->get_state() << endl;
                    }
                    i++;
                }
                break;
            }
            case 8:
                // Delete every users and meteo stations
                for (auto station : stations) {
                    delete station;
                }
                for (auto user : users){
                    delete user;
                }
        }
    }
}
