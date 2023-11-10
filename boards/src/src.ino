
/*
  Include all of the .cpp files in the .ino file.
     Otherwise the compiler does not compile
     the files leading to unknown references.
   Include with header files elsewhere as per the norm.
*/

#include "states/states.cpp"
#include "http_server/wifi.cpp" 
#include "http_server/server.cpp"
#include "http_server/api.cpp"
#include "components/components.cpp"
#include "utils/utils.cpp"

#define B_ESP32   32
#define B_ESP8266 8266

#define BOARD B_ESP8266

// TODO: 
// #if BOARD == check

void setup() {
  Serial.begin(9600);
  Serial.println("");
  
  T_WiFi::start();
  T_Server::start();
  T_Components::get_instance();
}
  
void loop() {
    T_Server::handle_requests();
    T_Components::get_instance().set_new_states();
    // delay ?
}
