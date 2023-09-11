#include "Arduino.h"
#include "server.h"

ESP8266WebServer T_Server::server = ESP8266WebServer(80);

void T_Server::start() {
    server.on("/api", api_main);
 
    server.begin(); //Start the server
    Serial.println("Server listening");
}

void T_Server::handle_requests() {
  server.handleClient();
}

void T_Server::api_main() { 

  if (server.arg("plain")) {
    Serial.println("New request data:");
    Serial.println(server.arg("plain"));
  }

  String message  = "";
  int status_code = 200;

  if (server.method() == HTTP_GET) {
    //T_API::handle_get_request(status_code, message);
  } else if (server.method() == HTTP_POST) {
    //T_API::handle_post_request(status_code, message);
  } else {
    status_code = 405;
    message = "Method Not Allowed";
  }

  Serial.println("Response:");
  Serial.println(status_code);
  Serial.println(message);

  server.send(status_code, "text/plain", message);
}
