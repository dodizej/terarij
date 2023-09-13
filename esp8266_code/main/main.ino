#include "wifi.h" 
#include "server.h"
#include "utils.h"
#include "states.h"
#include "components.h"


void setup() {
  Serial.begin(9600);
  Serial.println("");
  
  T_WiFi::start();
  if (! T_WiFi::is_connected()) {
    end_execution();
  }
  T_Server::start();
  delay(2000);
  T_Components::get_instance();
}
 
void loop() {
    T_Server::handle_requests();
    T_Components::get_instance().set_new_states();
    // delay ? 
}
