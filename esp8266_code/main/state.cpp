#include "state.h"
#include "Arduino.h"

// NOT USED

template<typename X>
X T_State<X>::get_state() {
  return state;
}

template<typename X>
void T_State<X>::set_state(X & new_state) {
  state = new_state;
}
