/** @format */

import { AppRegistry } from 'react-native';
import App from './App';
import { name as appName } from './app.json';

/* const LogLocation = async (data) => {
  console.log(data);
}; */

//AppRegistry.registerHeadlessTask('TestService', () => LogLocation);
AppRegistry.registerComponent(appName, () => App);
