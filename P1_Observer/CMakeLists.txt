cmake_minimum_required(VERSION 3.24)
project(P1)

set(CMAKE_CXX_STANDARD 17)

add_executable(P1 main.cpp CadenaMeteo.cpp CadenaMeteo.h Usuario.cpp Usuario.h UsuarioTemperatura.cpp UsuarioTemperatura.h UsuarioPrecipitaciones.cpp UsuarioPrecipitaciones.h MeteoInformation.cpp MeteoInformation.h UsuarioViento.cpp UsuarioViento.h UsuarioTiempo.cpp UsuarioTiempo.h UsuarioAll.cpp UsuarioAll.h utils.cpp utils.h)
