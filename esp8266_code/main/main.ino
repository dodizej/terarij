// libraries
// ...

// project files
#include "wifi.h" 
#include "server.h"
#include "utils.h"  

void setup() {
  Serial.begin(9600);
  
  T_WiFi::start();
  if (! T_WiFi::is_connected()) {
    end_execution();
  }
  
  T_Server::start();
}
 
void loop() {
    T_Server::handle_requests();
}
