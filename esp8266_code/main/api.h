#ifndef T_API
#define T_API
#include "Arduino.h"
#include <ESP8266WebServer.h>

class T_Api {

  public:
  
    static void handle_get_request(
          ESP8266WebServer& server
        , int&              status_code
        , String&           message     );
    
    static void handle_post_request(
          ESP8266WebServer& server
        , int&              status_code
        , String&           message     );

};

#endif