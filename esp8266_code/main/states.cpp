#include "Arduino.h"
#include "states.h"
#include <ArduinoJson.h>


T_States::T_States() {

  int_values  = std::map<String, int>();
  bool_values = std::map<String, bool>();

  int_values[S_LED_INTEN] = 0;

  bool_values[S_DIFUSOR]  = false;
  bool_values[S_PUMP]     = false;
  bool_values[S_LED]      = false;
};

T_States& T_States::getInstance() {
  static T_States instance; // Guaranteed to be destroyed.
                            // Instantiated on first use.
  return instance;
}

void T_States::key_not_found(const String & state_name) {
  Serial.println("T_States::key_not_found: state_name = " + state_name);
}

bool T_States::get_bool_state(const String & state_name) {
  if (bool_values.find(state_name) == bool_values.end()) {
      key_not_found(state_name);
      return false;
  }
  return bool_values[state_name]; 
}

int T_States::get_int_state(const String & state_name) {
  if (int_values.find(state_name) == int_values.end()) {
    key_not_found(state_name);
    return 0;
  }
  return int_values[state_name];
}

void T_States::set_bool_state(const String & state_name, bool state_value) {
  if (bool_values.find(state_name) == bool_values.end()) {
    key_not_found(state_name);
    return;
  }
  bool_values[state_name] = state_value;
}

void T_States::set_int_state(const String & state_name, int state_value) {
  if (int_values.find(state_name) == int_values.end()) {
    key_not_found(state_name);
    return;
  }
  int_values[state_name] = state_value;
}

String T_States::get_states_json() {
  String result;
  StaticJsonDocument<200> doc;
  // be careful with the doc size even the official assistant was wrong
  //   arduinojson.org/v6/assistant

  for ( std::map<String, int>::iterator it = int_values.begin()
      ; it != int_values.end()
      ; ++it) 
    {
      doc[it->first] = it->second;
    }
  for ( std::map<String, bool>::iterator it = bool_values.begin()
    ; it != bool_values.end()
    ; ++it) 
  {
    doc[it->first] = it->second;
  }
  serializeJsonPretty(doc, result);
  Serial.println(result);

  return result;
}

int T_States::get_number_of_states() {
  return int_values.size() + bool_values.size();
}
