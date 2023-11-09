#ifndef T_SERVER_H
#define T_SERVER_H
#include "Arduino.h"
#include <ESP8266WebServer.h>

class T_Server {
  private:
    static ESP8266WebServer server;

  public:
    static void start();
    static void handle_requests();

    static void handle_get_request();
    static void handle_post_requests();

    static void api_main();
};

#endif