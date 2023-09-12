#include "Arduino.h"
#include "states.h"


T_States::T_States() {

  new_states = std::vector<String>();

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

void T_States::set_state(const String & state_name, int state_value) {
  if (int_values.find(state_name) == int_values.end()) {
    key_not_found(state_name);
    return;
  }
  int_values[state_name] = state_value;
  new_states.push_back(state_name);
}

void T_States::set_state(const String & state_name, bool state_value) {
  if (bool_values.find(state_name) == bool_values.end()) {
    key_not_found(state_name);
    return;
  }
  bool_values[state_name] = state_value;
  new_states.push_back(state_name);
}

String T_States::get_states_json() {
  String result;
  StaticJsonDocument<200> doc;
  // be careful with the doc size 
  // even the official assistant was wrong
  //   arduinojson.org/v6/assistant
  // TODO: dinamically determine size

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
  Serial.println(result); // TODO: remove after testing

  return result;
}

int T_States::get_number_of_states() {
  return int_values.size() + bool_values.size();
}

T_States::New_States T_States::get_new_states() {
  return new_states;
}

void T_States::clear_new_states() {
  new_states.clear();
}

bool T_States::is_int_state(const String state_name) {
  return int_values.find(state_name) != int_values.end();
}

bool T_States::is_bool_state(const String state_name) {
  return bool_values.find(state_name) != bool_values.end();
}

bool T_States::check_json_states(JsonObject obj, String& err_msg) {
  for (JsonPair pair : obj) {
    String state_name = pair.key().c_str();

    if (is_int_state(state_name)) {
        if (! pair.value().is<int>()) {
            err_msg += "INVALID_DATA_TYPE"; // ERROR: INVALID_DATA_TYPE
            return false;
        } 
    } else if (is_bool_state(state_name)) {
        if (! pair.value().is<bool>()) {

            err_msg += "INVALID_DATA_TYPE"; // ERROR: INVALID_DATA_TYPE
            return false;
        }
    } else {
        err_msg += "STATE_NAME_DOES_NOT_EXIST"; // ERROR: STATE_NAME_DOES_NOT_EXIST
        return false;
    }
  }
  return true;
}
