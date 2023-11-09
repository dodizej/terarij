#include "utils.h"
#include "Arduino.h"

void end_execution() {  // todo - better implementation - low power mode?
  while (true) {
    delay(100 * 1000);
  }
}