# simulateur-de-credit
Android App that calculates the amount of money to pay after a loan given certain settings using company-local SOAP API.

## About
This project was made under an internship in popular Tunisan Bank and is in French. Until the moment, it only works when your phone in connected to the local network in the company but all the UI elements are up and running and display fake data by default.

##Technology
This project is for Android, so it is written in Java and XML. It uses a SOAP API set in a local server as a backend. So it just displays fake data.
I used the [Ksoap2 libraries](http://simpligility.github.io/ksoap2-android/getting-started) and this UI element : [SeekArc](https://github.com/neild001/SeekArc).

## Usage
1- Clone the Repository.<br/>
2- Open `build.gradle` using [Android Studio](https://developer.android.com/studio/index.html).

#### For any unexpected bug : 
Both dependency projects are in `/Modules_and_Jars`.
if a certain bug keeps gradle from properly building the project you start a new project **with the same name** include both dependency projects as modules and copy my code from `/app/src/main/` and copy it to the new project.
