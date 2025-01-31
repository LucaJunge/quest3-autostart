#!/bin/bash

# Wait for the device to be detected
while ! adb get-state 2>/dev/null | grep -q "device"; do
    echo "Waiting for Quest 3 to connect..."
    sleep 2
done

echo "Quest 3 detected! Starting screen recording..."

# Start scrcpy with recording enabled
scrcpy --record recording.mp4 --video-bit-rate 20M --max-fps 90 --crop 2064:2208:2064:0 &

# Optional: Keep script running to prevent exit
wait
