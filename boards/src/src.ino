#include "states/states.cpp"
#include "http_server/wifi.cpp" 
#include "http_server/server.cpp"
#include "http_server/api.cpp"
#include "components/components.cpp"
#include "utils/utils.cpp"



void setup() {
  Serial.begin(9600);
  Serial.println("");
  
  T_WiFi::start();
  T_Server::start();
  //T_Components::get_instance();
}
 
void loop() {
    T_Server::handle_requests();
    //T_Components::get_instance().set_new_states();
    // delay(100); ?
}
