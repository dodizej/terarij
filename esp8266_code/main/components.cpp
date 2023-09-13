#include "Arduino.h"
#include "components.h"
#include "states.h"
#include <vector>

T_Components& T_Components::get_instance() {
  static T_Components instance; // Guaranteed to be destroyed.
                                // Instantiated on first use.
  return instance;
}

T_Components::T_Components() {
  state_pin_map = std::map<String, int>();

  state_pin_map[T_States::S_DIFUSOR]   = 12; // D6
  state_pin_map[T_States::S_PUMP]      = 13; // D7
  state_pin_map[T_States::S_LED]       = 14; // D5
  state_pin_map[T_States::S_LED_INTEN] = 15; // D8

  
  // set bool values
  for (std::map<String, int>::iterator it = state_pin_map.begin();
       it != state_pin_map.end();
       ++it)
  {
    Serial.println(it->first);
    Serial.println(it->second);
    Serial.println(OUTPUT);
    pinMode(it->second, OUTPUT);
    // set_comp_power(it->first);
  }
}

void T_Components::set_comp_power(String state_name) {
    if (T_States::get_instance().get_bool_state(state_name)) {
        digitalWrite(state_pin_map[state_name], HIGH);
    } else {
        digitalWrite(state_pin_map[state_name], LOW);
    }
}

// To be called from main
void T_Components::set_new_states() {
    T_States::New_States new_states = T_States::get_instance().get_new_states();

    if (new_states.size() == 0) {
        return;
    }

    for (T_States::New_States::iterator it = new_states.begin();
         it != new_states.end();
         ++it) {
        if ((*it).equals(T_States::S_LED_INTEN)) { 
            // special case - pulse pin for led strip intensity
        } else {
            set_comp_power(*it);
        }
    }

    T_States::get_instance().clear_new_states();
}
