#include "Arduino.h"
#include "components.h"
#include "states.h"
#include <vector>

// To be called from main
void T_Components::set_new_states() {
    T_States::New_States new_states = T_States::getInstance().get_new_states();

    if (new_states.size() == 0) {
        return;
    }

    // TODO: Set component values (board pin values)

    T_States::getInstance().clear_new_states();
}