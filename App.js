import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
  NativeModules,
  DeviceEventEmitter,
  ToastAndroid,
} from 'react-native';

const { BlockButton } = NativeModules;

export default class App extends Component {
  componentDidMount() {
    this.initialize();
  }
  initialize = async () => {
    try {
      await BlockButton.start();
      DeviceEventEmitter.addListener('buttonDesliga', () =>
        this.toastMe('Desligou a tela'),
      );
      DeviceEventEmitter.addListener('buttonLiga', () =>
        this.toastMe('Ligou a tela'),
      );
    } catch (e) {
      this.toastMe('erro ao iniciar no React');
    }
  };

  toastMe = (msg) => {
    ToastAndroid.show(msg, ToastAndroid.LONG);
  };

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>Welcome to React Native!</Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
