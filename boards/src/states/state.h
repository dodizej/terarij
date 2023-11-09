#ifndef T_STATE
#define T_STATE
#include "Arduino.h"

// NOT USED

template<typename X>
class T_State {
  private:
    X state;

  public:
    T_State(X state) : state(state) {}
    ~T_State() { Serial.println("State destructed."); }
    X get_state();
    void set_state(X & new_state);
};


#endif