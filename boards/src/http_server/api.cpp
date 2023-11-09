#include "Arduino.h"
#include "api.h"
#include "../states/state.h"
#include <ArduinoJson.h>


void T_Api::handle_get_request(
      ESP8266WebServer& server
    , int&              status_code
    , String&           message    )
{
    message = T_States::get_instance().get_states_json();
}

void T_Api::handle_post_request(
      ESP8266WebServer& server
    , int&              status_code
    , String&           message     )
{
  String input = server.arg("plain");
  if (!input) {
    return;
  }

  StaticJsonDocument<200> doc;
  DeserializationError error = deserializeJson(doc, input);
  if (error) {
    status_code = 400; // HTTP BAD REQUEST
    message += "Failed to deserialize json.";
    Serial.print("deserializeJson() failed: ");
    Serial.println(error.c_str());
    return;
  }

  // Atomicity
  //  Check if all of the state names and json values are valid
  //  then update all of the values
  JsonObject obj = doc.as<JsonObject>();
  if (! T_States::get_instance().check_json_states(obj, message)) {
    status_code = 400; // HTTP Bad Request - assume error is in the user request
    return;
  }

  for (JsonPair pair : obj) {
    String state_name = pair.key().c_str();

    if (T_States::get_instance().is_bool_state(state_name)) {
      T_States::get_instance().set_state( state_name, pair.value().as<bool>() );
    } else { // if (is_int_state(state_name))  ->  no need to check, it's an int state
      T_States::get_instance().set_state( state_name, pair.value().as<int>() );
    }
  }
}
