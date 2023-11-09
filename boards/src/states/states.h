#ifndef T_STATES
#define T_STATES

#include "Arduino.h"
#include <map>
#include <vector>
#include <ArduinoJson.h>

// singleton
class T_States {

private:  
  std::vector<String> new_states;

  std::map<String, int>  int_values;
  std::map<String, bool> bool_values;

public:
  typedef std::vector<String> New_States;

  static const String S_DIFUSOR;
  static const String S_PUMP;
  static const String S_LED;
  static const String S_LED_INTEN;

  const int led_int_min = 0;
  const int led_int_max = 5; 
  
  T_States();

  static T_States& get_instance();             // return lvalue reference
  
  T_States(T_States const&)       = delete; // delete copy constructor
  void operator=(T_States const&) = delete; // delete assignment operator

  bool get_bool_state(const String & state_name);
  int  get_int_state (const String & state_name);

  void set_state(const String & state_name, int state_value);
  void set_state(const String & state_name, bool state_value);

  void key_not_found(const String & state_name);

  String get_states_json();

  int get_number_of_states();

  New_States get_new_states();
  void clear_new_states();

  bool is_int_state(const String state_name);
  bool is_bool_state(const String state_name);

  bool check_json_states(JsonObject obj, String& err_msg);
};

#endif