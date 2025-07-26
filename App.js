import React, { useEffect, useState } from 'react';
import { View, Text, StyleSheet, Platform } from 'react-native';

const App = () => {
  const [screen, setScreen] = useState('home');

  useEffect(() => {
    // Simulate that we launched from notification
    // You can later replace this with native module intent-checking
    if (Platform.OS === 'android') {
      // You can modify this condition to use real data from native code
      const openedFromNotification = true; // simulate
      if (openedFromNotification) {
        setScreen('message');
      }
    }
  }, []);

  return (
    <View style={styles.container}>
      {screen === 'message' ? (
        <Text style={styles.text}>📨 Message Screen</Text>
      ) : (
        <Text style={styles.text}>🏠 Home Screen</Text>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#EAF2F8',
  },
  text: {
    fontSize: 24,
    color: '#2E86C1',
  },
});

export default App;
