#ifndef T_COMPONENTS
#define T_COMPONENTS
#include "Arduino.h"
#include <map>

class T_Components {

  private:
    std::map<String, int> state_pin_map;

  public:
    T_Components();

    static T_Components& get_instance();             // return lvalue reference
    
    T_Components(T_Components const&)       = delete; // delete copy constructor
    void operator=(T_Components const&) = delete; // delete assignment operator

    void set_new_states();
    void set_comp_power(String state_name);
    
};

#endif