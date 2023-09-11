// libraries
// ...

// project files
#include "wifi.h" 
#include "server.h"
#include "utils.h"
#include "states.h"

void setup() {
  Serial.begin(9600);
  Serial.println("");
  
  T_WiFi::start();
  if (! T_WiFi::is_connected()) {
    end_execution();
  }

  T_States::getInstance().get_states_json();
  
  T_Server::start();
}
 
void loop() {
    T_Server::handle_requests();
}
