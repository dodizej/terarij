#ifndef T_WIFI_H
#define T_WIFI_H
#include "Arduino.h"
#include <ESP8266WiFi.h>

class T_WiFi {
  private:
    // values in miliseconds
    static const unsigned int timeout;
    static const unsigned int wait_interval;

    static const char* ssid;
    static const char* password;

    static bool connected;

  public:
    static void start();
    static bool is_connected();

};

#endif