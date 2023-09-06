#include "Arduino.h"
#include "server.h"

ESP8266WebServer T_Server::server = ESP8266WebServer(80);

void T_Server::start() {
    server.on("/api", api_main); //Associate the handler function to the path
 
    server.begin(); //Start the server
    Serial.println("Server listening");
}

void T_Server::handle_requests() {
  server.handleClient();
}

void T_Server::api_main() { // Handler for the body path

  if (! server.hasArg("plain")) {
    server.send(200, "text/plain", "Body not received");
    return;
  }

  String message = "Body received:\n";
        message += server.arg("plain");
        message += "\n";

  server.send(200, "text/plain", message);
  Serial.println(message);
}