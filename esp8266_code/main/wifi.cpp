#include "Arduino.h"
#include "wifi.h"

const unsigned int T_WiFi::timeout       = 10 * 1000;  // 10 sec
const unsigned int T_WiFi::wait_interval = 500;        // 500 ms

bool T_WiFi::connected = false;

void T_WiFi::start() {
    WiFi.begin(ssid, password);
 
    Serial.print("Waiting to connect...");

    unsigned int wait_time = 0;
    do {
        connected = WiFi.status() == WL_CONNECTED;
        if (connected) break;

        delay(wait_interval);
        wait_time += wait_interval;
        Serial.print(".");

    } while (wait_time < timeout);
    Serial.println("");

    if (!connected) {
      Serial.print("Failed to connect to the Wi-Fi network. SSID: ");
      Serial.println(ssid);
    }
    else {
      Serial.println("Connected to the Wi-Fi network.");
      Serial.print("IP address: ");
      Serial.println(WiFi.localIP());
    }
}

bool T_WiFi::is_connected() {
  return connected;
}
